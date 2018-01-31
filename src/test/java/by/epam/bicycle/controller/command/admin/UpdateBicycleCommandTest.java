package by.epam.bicycle.controller.command.admin;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

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
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;

@RunWith(MockitoJUnitRunner.class)
public class UpdateBicycleCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private BicycleService service;
	
		
	@InjectMocks
	private UpdateBicycleCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(UpdateBicycleCommand.RENTAL_POINT_ID_PARAM)).thenReturn("1");
		when(request.getParameter(UpdateBicycleCommand.BICYCLE_MODEL_ID_PARAM)).thenReturn("1");
		when(request.getParameter(UpdateBicycleCommand.BICYCLE_ID_PARAM)).thenReturn("1");
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclesCommandAndSuccessMessage() throws CommandException, ServiceException {
		doNothing().when(service).updateByPointAndModelId(anyLong(), anyLong(), anyLong());
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.BICYCLEUPDATED,
				CommandMessageTypeEnum.SUCCESS);
		CommandResponse expected = new RedirectResponse(CommandResponse.BICYCLES_COMMAND, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		doThrow(new ServiceException()).when(service).updateByPointAndModelId(anyLong(), anyLong(), anyLong());
		command.execute(request);
	}
}
