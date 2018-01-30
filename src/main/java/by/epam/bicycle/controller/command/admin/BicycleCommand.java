package by.epam.bicycle.controller.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleModelService;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.RentalPointService;

public class BicycleCommand implements ActionCommand {
	private final static String BICYCLE_ID_PARAM = "bicycleid";
	private final static String BICYCLE_ATTRIBUTE = "bicycle";
	private final static String ADD_COMMAND_ATTRIBUTE = "addbicycle";
	private final static String UPDATE_COMMAND_ATTRIBUTE = "updatebicycle";
	private final static String COMMAND_ATTRIBUTE = "command";
	private final static String RENTALPOINTS_ATTRIBUTE = "rentalPoints";
	private final static String BICYCLEMODELS_ATTRIBUTE = "bicycleModels";
	private final static String LANGUAGE_ATTRIBUTE = "language";
	private final static String BICYCLE_PAGE = "path.page.bicycle";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LANGUAGE_ATTRIBUTE);
		
		String bicycleIdParam = request.getParameter(BICYCLE_ID_PARAM);
		
		try {
			if (bicycleIdParam == null || bicycleIdParam.isEmpty()) {
				request.setAttribute(COMMAND_ATTRIBUTE, ADD_COMMAND_ATTRIBUTE);
			} else {
				request.setAttribute(COMMAND_ATTRIBUTE, UPDATE_COMMAND_ATTRIBUTE);
				long bicycleId = Long.parseLong(bicycleIdParam);
				BicycleService bicycleService = new BicycleService(language);
				Bicycle bicycle = bicycleService.findEntityById(bicycleId);
				request.setAttribute(BICYCLE_ATTRIBUTE, bicycle);
			}	
		
			RentalPointService rentalPointService = new RentalPointService(language);
			List<RentalPoint> rentalPoints  = rentalPointService.findAll();
			request.setAttribute(RENTALPOINTS_ATTRIBUTE, rentalPoints);
			
			BicycleModelService bicycleModelService = new BicycleModelService(language);
			List<BicycleModel> bicycleModels = bicycleModelService.findAll();
			request.setAttribute(BICYCLEMODELS_ATTRIBUTE, bicycleModels);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}			
		
		return ConfigurationManager.getProperty(BICYCLE_PAGE);
	}

}
