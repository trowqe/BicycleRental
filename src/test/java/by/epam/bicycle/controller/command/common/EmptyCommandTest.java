package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmptyCommandTest {
	@Mock
	private HttpServletRequest request;
	
	@InjectMocks
	private EmptyCommand command;
	
	@Test 
	public void shouldReturnForwardReponseToIndexPage() {
		CommandResponse expected = new ForwardResponse(CommandResponse.INDEX_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
}
