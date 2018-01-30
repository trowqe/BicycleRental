package by.epam.bicycle.controller.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentalPointService;

public class DeleteBicycleCommand implements ActionCommand {
	private final static String BICYCLE_ID_PARAM = "bicycleid";
	
	private void preloadBicyclePage(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute("language");
		
		RentalPointService rentalPointService = new RentalPointService(language);
		List<RentalPoint> rentalPoints = rentalPointService.findAll();
		request.setAttribute("rentalPoints", rentalPoints);
		
		BicycleTypeService bicycleTypeService = new BicycleTypeService(language);
		List<BicycleType> bicycleTypes = bicycleTypeService.findAll();
		request.setAttribute("bicycleTypes", bicycleTypes);
		
		BicycleService bicycleService  = new BicycleService(language);
		List<Bicycle> bicycles = bicycleService.getActiveBicyclesByFilter(-1, -1, "", "");
		request.setAttribute("bicycles", bicycles);
	}
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String bicycleIdParam = request.getParameter(BICYCLE_ID_PARAM);
		long bicycleId = Long.parseLong(bicycleIdParam);
		
		BicycleService service = new BicycleService();
		try {
			service.delete(bicycleId);
			preloadBicyclePage(request);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return ConfigurationManager.getProperty("path.page.adminbicycles");
	}

}
