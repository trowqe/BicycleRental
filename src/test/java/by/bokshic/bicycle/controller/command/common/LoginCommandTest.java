package by.bokshic.bicycle.controller.command.common;

import static org.mockito.ArgumentMatchers.anyString;
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

import by.bokshic.bicycle.config.MessageManager;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandMessage;
import by.bokshic.bicycle.controller.response.CommandMessageTypeEnum;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import by.bokshic.bicycle.entity.Role;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
	private final static String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private UserService service;
	
	@Mock
	private User user;
	
	@Mock
	private Role role;
		
	@InjectMocks
	private LoginCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession(true)).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(anyString())).thenReturn(new String());	
		when(user.getRole()).thenReturn(role);
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclesPage() throws CommandException, ServiceException {
		when(service.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(user);
		when(user.isBlocked()).thenReturn(false);
		when(role.isUser()).thenReturn(true);
		CommandResponse expected = new RedirectResponse(CommandResponse.BICYCLES_COMMAND);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnForwardResponseToUsersPage() throws CommandException, ServiceException {
		when(service.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(user);
		when(user.isBlocked()).thenReturn(false);
		when(role.isUser()).thenReturn(false);
		CommandResponse expected = new RedirectResponse(CommandResponse.USERS_COMMAND);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnForwardResponseToIndexPageWithUserBlockedError() throws CommandException, ServiceException {
		when(service.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(user);
		when(user.isBlocked()).thenReturn(true);
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.USERBLOCKED,
				CommandMessageTypeEnum.WRONG);
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnForwardResponseToIndexPageWitLoginError() throws CommandException, ServiceException {
		when(service.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(null);
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.LOGINERROR,
				CommandMessageTypeEnum.WRONG);
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	
	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		when(service.getUserByLoginAndPassword(anyString(), anyString())).thenThrow(new ServiceException());
		command.execute(request);
	}
}
