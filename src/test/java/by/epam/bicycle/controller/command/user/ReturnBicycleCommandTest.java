package by.epam.bicycle.controller.command.user;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

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
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;

@RunWith(MockitoJUnitRunner.class)
public class ReturnBicycleCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RentService service;
	
	@Mock
	private Rent rent;
	
	@Mock
	private Bicycle bicycle;
	
	@Mock
	private RentalPoint point;
	
	@Mock
	private BicycleModel model;
	
	@Mock
	private Tariff tariff;
	
		
	@InjectMocks
	private ReturnBicycleCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(ReturnBicycleCommand.RENT_ID_PARAM)).thenReturn("1");
		when(rent.getBicycle()).thenReturn(bicycle);
		when(bicycle.getPoint()).thenReturn(point);
		when(bicycle.getModel()).thenReturn(model);
		when(rent.getTariff()).thenReturn(tariff);
		when(rent.getPlanFinishDateTime()).thenReturn(new Date());
		when(tariff.getPrice()).thenReturn(new BigDecimal(0.00));
		when(tariff.getRentalTime()).thenReturn((float) 1);
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclesCommandAndSuccessMessage() throws CommandException, ServiceException {
		when(service.findEntityById(anyLong())).thenReturn(rent);
		CommandResponse expected = new ForwardResponse(CommandResponse.RETURNBICYCLE_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		when(service.findEntityById(anyLong())).thenThrow(new ServiceException());
		command.execute(request);
	}
}
