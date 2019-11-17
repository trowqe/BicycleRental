package by.bokshic.bicycle.controller.command.admin;

import static org.mockito.ArgumentMatchers.anyLong;
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
import by.bokshic.bicycle.service.impl.BicycleService;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBicycleCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private BicycleService service;
	
		
	@InjectMocks
	private DeleteBicycleCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(DeleteBicycleCommand.BICYCLE_ID_PARAM)).thenReturn("1");
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclesCommandAndSuccessMessage() throws CommandException, ServiceException {
		doNothing().when(service).delete(anyLong());
		CommandMessage message = new CommandMessage(LANGUAGE, MessageManager.BICYCLEDELETED,
				CommandMessageTypeEnum.SUCCESS);
		CommandResponse expected = new RedirectResponse(CommandResponse.BICYCLES_COMMAND, message);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		doThrow(new ServiceException()).when(service).delete(anyLong());
		command.execute(request);
	}
}
