package by.epam.bicycle.controller.command.common;

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

import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.service.ServiceException;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {
	@Mock
	private HttpServletRequest request;
		
	@Mock
	private HttpSession session;
	
	@InjectMocks
	private LogoutCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
	}
	
	@Test 
	public void shouldReturnForwardReponseToIndexPage() {
		CommandResponse expected = new RedirectResponse(CommandResponse.INDEX_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
}
