package by.bokshic.bicycle.controller.command.user;

import static org.mockito.ArgumentMatchers.anyLong;
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
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.BicycleService;
import by.bokshic.bicycle.service.impl.TariffService;

@RunWith(MockitoJUnitRunner.class)
public class PrepareOrderCommandTest {
	private static final String LANGUAGE = "ru";
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private BicycleService bicycleService;
	
	@Mock
	private TariffService tariffService;
	
	@Mock
	private Bicycle bicycle;
	
	@Mock
	private RentalPoint point;
	
	@Mock
	private BicycleModel model;
	
	@Mock
	private BicycleType type;
	
	@InjectMocks
	private PrepareOrderCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn(LANGUAGE);
		when(request.getParameter(PrepareOrderCommand.BICYCLE_ID_PARAM)).thenReturn("1");
		when(bicycleService.findEntityById(anyLong())).thenReturn(bicycle);
		when(bicycle.getPoint()).thenReturn(point);
		when(bicycle.getModel()).thenReturn(model);
		when(model.getBicycleType()).thenReturn(type);
	}
	
	@Test
	public void shouldReturnForwardResponseToPrepareOrderPage() throws CommandException, ServiceException {
		when(tariffService.getTarriffListByBicycleTypeId(anyLong())).thenReturn(null);
		CommandResponse expected = new ForwardResponse(CommandResponse.PREPAREORDER_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
		
	@Test(expected = CommandException.class)
	public void shouldThrowCommandExceptionWhenServiceThrowServiceException() throws ServiceException, CommandException {
		when(tariffService.getTarriffListByBicycleTypeId(anyLong())).thenThrow(new ServiceException());
		command.execute(request);
	}
}
