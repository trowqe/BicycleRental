package by.bokshic.bicycle.controller.command.admin;

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

import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UsersCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private UserService service;
	
	@InjectMocks
	private UsersCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
	}
	
	@Test
	public void shouldReturnForwardResponseToUsersPage() throws CommandException, ServiceException {
		when(service.findAllUsers()).thenReturn(null);
		CommandResponse expected = new ForwardResponse(CommandResponse.USERS_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldThrowCommandExceptionWhenServiceThrowServiceException() throws ServiceException, CommandException {
		when(service.findAllUsers()).thenThrow(new ServiceException());
		command.execute(request);
	}
}
