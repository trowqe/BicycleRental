package by.epam.bicycle.controller.command.user;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;

@RunWith(MockitoJUnitRunner.class)
public class RentsCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RentService service;
	
	@Mock
	private User user;
	
	@InjectMocks
	private RentsCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(session.getAttribute(SessionAttributes.USER)).thenReturn(user);
	}
	
	@Test
	public void shouldReturnForwardResponseToPrepareOrderPage() throws CommandException, ServiceException {
		when(service.getRentsByUserId(anyLong())).thenReturn(null);
		CommandResponse expected = new ForwardResponse(CommandResponse.RENTS_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		
	@Test(expected = CommandException.class)
	public void shouldThrowCommandExceptionWhenServiceThrowServiceException() throws ServiceException, CommandException {
		when(service.getRentsByUserId(anyLong())).thenThrow(new ServiceException());
		command.execute(request);
	}
}