package by.bokshic.bicycle.controller.command.common;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.BicycleModelService;
import by.bokshic.bicycle.service.impl.BicycleService;
import by.bokshic.bicycle.service.impl.RentalPointService;

@RunWith(MockitoJUnitRunner.class)
public class BicycleCommandTest{
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private BicycleService bicycleService;
	
	@Mock
	private RentalPointService rentalPointService;
	
	@Mock
	private BicycleModelService bicycleModelService;
		
	@InjectMocks
	private BicycleCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(rentalPointService.findAll()).thenReturn(new ArrayList<RentalPoint>());
		when(bicycleModelService.findAll()).thenReturn(new ArrayList<BicycleModel>());
		when(bicycleService.findEntityById(anyLong())).thenReturn(new Bicycle());
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn("ru");
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclePageAndUpdateBicycleCommand() throws CommandException, ServiceException {
		when(request.getParameter(BicycleCommand.BICYCLE_ID_PARAM)).thenReturn("1");
				
		CommandResponse expected = new ForwardResponse(CommandResponse.BICYCLE_PAGE);
		CommandResponse actual = command.execute(request);
		verify(bicycleService, atLeastOnce()).findEntityById(anyLong());
		Assert.assertEquals(expected, actual);
	}
	
	
	@Test
	public void shouldReturnForwardResponseToBicyclePageAndAddBicycleCommand() throws CommandException, ServiceException {
		when(request.getParameter(BicycleCommand.BICYCLE_ID_PARAM)).thenReturn(null);
				
		CommandResponse expected = new ForwardResponse(CommandResponse.BICYCLE_PAGE);
		CommandResponse actual = command.execute(request);
		verify(bicycleService, never()).findEntityById(anyLong());
		Assert.assertEquals(expected, actual);
	}
	

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		when(request.getParameter(BicycleCommand.BICYCLE_ID_PARAM)).thenReturn("1");
		when(rentalPointService.findAll()).thenThrow(new ServiceException());
		command.execute(request);
	}
	
}
