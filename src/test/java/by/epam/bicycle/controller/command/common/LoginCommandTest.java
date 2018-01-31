package by.epam.bicycle.controller.command.common;

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

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

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
