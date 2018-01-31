package by.epam.bicycle.controller.command.common;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentalPointService;

@RunWith(MockitoJUnitRunner.class)
public class FilterBicyclesCommandTest {
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private BicycleService bicycleService;
	
	@Mock
	private RentalPointService rentalPointService;
	
	@Mock
	private BicycleTypeService bicycleTypeService;
	
	@Mock
	private User user;
	
	@Mock
	private Role role;
		
	@InjectMocks
	private FilterBicyclesCommand command;
	
	@Before
	public void setup() throws ServiceException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionAttributes.LANGUAGE)).thenReturn("ru");
		when(session.getAttribute(SessionAttributes.USER)).thenReturn(user);
		when(user.getRole()).thenReturn(role);
		when(request.getParameter(anyString())).thenReturn(null);
	
		when(rentalPointService.findAll()).thenReturn(new ArrayList<RentalPoint>());
		when(bicycleTypeService.findAll()).thenReturn(new ArrayList<BicycleType>());
		when(bicycleService.getActiveBicyclesByFilter(anyLong(), anyLong(), anyString(),  anyString())).thenReturn(new ArrayList<Bicycle>());	
	}
	
	@Test
	public void shouldReturnForwardResponseToAdminBicyclesPage() throws CommandException, ServiceException {
		when(role.isAdmin()).thenReturn(true);
		CommandResponse expected = new ForwardResponse(CommandResponse.ADMINBICYCLES_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnForwardResponseToBicyclesPage() throws CommandException, ServiceException {
		when(role.isAdmin()).thenReturn(false);
		CommandResponse expected = new ForwardResponse(CommandResponse.BICYCLES_PAGE);
		CommandResponse actual = command.execute(request);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = CommandException.class)
	public void shouldReturnThrowCommandExceptionWhenRentalPointServiceThrowServiceException() throws ServiceException, CommandException {
		when(rentalPointService.findAll()).thenThrow(new ServiceException());
		command.execute(request);
	}
}
