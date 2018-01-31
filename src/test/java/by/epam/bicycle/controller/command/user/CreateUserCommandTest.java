package by.epam.bicycle.controller.command.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private UserService service;
		
	@InjectMocks
	private CreateUserCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession(true)).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(anyString())).thenReturn(new String());
	}
	
	@Test
	public void shouldReturnRedirectResponseToIndexPageAndSuccessMessage() throws CommandException, ServiceException {
		when(request.getParameter(CreateUserCommand.PASSWORD_PARAM)).thenReturn("test");
		when(request.getParameter(CreateUserCommand.REPEATPASSWORD_PARAM)).thenReturn("test");
		when(service.isLoginUnique(anyString())).thenReturn(true);
		
		doNothing().when(service).create(any(User.class));
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.USERCREATED,
				CommandMessageTypeEnum.SUCCESS);
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnRedirectResponseToIndexPageAndLoginNotUniqueMessage() throws ServiceException, CommandException {
		when(request.getParameter(CreateUserCommand.PASSWORD_PARAM)).thenReturn("test");
		when(request.getParameter(CreateUserCommand.REPEATPASSWORD_PARAM)).thenReturn("test");
		when(service.isLoginUnique(anyString())).thenReturn(false);
		
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.LOGINNOTUNIQUE,
				CommandMessageTypeEnum.WRONG);
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		CommandResponse actual = command.execute(request);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnRedirectResponseToIndexPageAndPasswordsNotSameMessage() throws ServiceException, CommandException {
		when(request.getParameter(CreateUserCommand.PASSWORD_PARAM)).thenReturn("test");
		when(request.getParameter(CreateUserCommand.REPEATPASSWORD_PARAM)).thenReturn("test1");
		
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.PASSWORDSNOTSAME,
				CommandMessageTypeEnum.WRONG);
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		CommandResponse actual = command.execute(request);
		
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		when(request.getParameter(CreateUserCommand.PASSWORD_PARAM)).thenReturn("test");
		when(request.getParameter(CreateUserCommand.REPEATPASSWORD_PARAM)).thenReturn("test");
		when(service.isLoginUnique(anyString())).thenReturn(true);
		
		doThrow(new ServiceException()).when(service).create(any(User.class));
		command.execute(request);
	}
}
