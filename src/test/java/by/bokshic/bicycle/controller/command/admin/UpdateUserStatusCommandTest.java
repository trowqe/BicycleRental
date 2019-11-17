package by.bokshic.bicycle.controller.command.admin;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserStatusCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private UserService service;
	
		
	@InjectMocks
	private UpdateUserStatusCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(UpdateUserStatusCommand.USER_ID_PARAM)).thenReturn("1");
		when(request.getParameter(UpdateUserStatusCommand.STATUS_PARAM)).thenReturn(UpdateUserStatusCommand.BLOCK_STATUS);
		
	}
	
	@Test
	public void shouldReturnForwardResponseToUsersCommandAndSuccessMessage() throws CommandException, ServiceException {
		doNothing().when(service).updateStatus(anyLong(), anyShort());
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.USERSTATUSCHANGED,
				CommandMessageTypeEnum.SUCCESS);
		CommandResponse expected = new RedirectResponse(CommandResponse.USERS_COMMAND, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenServiceThrowServiceException() throws ServiceException, CommandException {
		doThrow(new ServiceException()).when(service).updateStatus(anyLong(), anyShort());
		command.execute(request);
	}
}
