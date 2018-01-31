package by.epam.bicycle.controller.command.user;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.mockito.Mockito.any;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CloseRentCommandTest {
	private static final String LANGUAGE = "ru";
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RentService rentService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private User user;
	
	@Mock
	private Rent rent;
	
	@InjectMocks
	private CloseRentCommand command;
	
	@Before
	public void setup() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(session.getAttribute(SessionAttributes.USER)).thenReturn(user);
				
		when(request.getParameter(CloseRentCommand.RENT_ID_PARAM)).thenReturn("1");
		when(request.getParameter(CloseRentCommand.AMOUNT_PARAM)).thenReturn("0.00");
		when(user.getBalance()).thenReturn(new BigDecimal(0.00));
	}
	
	@Test
	public void shouldReturnForwardResponseToRentsCommandAndSuccessMessage() throws CommandException, ServiceException {
		when(rentService.findEntityById(anyLong())).thenReturn(rent);
		doNothing().when(rentService).updateById(anyLong(), any(Rent.class));
		doNothing().when(userService).updateBalance(anyLong(), any(BigDecimal.class));
				
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.RENTCLOSED, CommandMessageTypeEnum.SUCCESS);
		CommandResponse expected = new RedirectResponse(CommandResponse.RENTS_COMMAND, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldThrowCommandExceptionWhenUserServiceThrowServiceException() throws ServiceException, CommandException {
		when(rentService.findEntityById(anyLong())).thenReturn(rent);
		doNothing().when(rentService).updateById(anyLong(), any(Rent.class));
		doThrow(new ServiceException()).when(userService).updateBalance(anyLong(), any(BigDecimal.class));
		command.execute(request);
	}
}
