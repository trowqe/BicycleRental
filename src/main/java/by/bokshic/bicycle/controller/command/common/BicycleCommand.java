package by.bokshic.bicycle.controller.command.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.BicycleModelService;
import by.bokshic.bicycle.service.impl.BicycleService;
import by.bokshic.bicycle.service.impl.RentalPointService;

public class BicycleCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(BicycleCommand.class);
	
	public final static String BICYCLE_ID_PARAM = "bicycleid";
	public final static String BICYCLE_ATTRIBUTE = "bicycle";
	public final static String ADD_COMMAND_ATTRIBUTE = "addbicycle";
	public final static String UPDATE_COMMAND_ATTRIBUTE = "updatebicycle";
	public final static String COMMAND_ATTRIBUTE = "command";
	public final static String RENTALPOINTS_ATTRIBUTE = "rentalPoints";
	public final static String BICYCLEMODELS_ATTRIBUTE = "bicycleModels";
	
	private RentalPointService rentalPointService;
	private BicycleService bicycleService;
	private BicycleModelService bicycleModelService;
	

	public BicycleCommand(BicycleService bicycleService, RentalPointService rentalPointService, 
			BicycleModelService bicycleModelService) {
		this.rentalPointService = rentalPointService;
		this.bicycleService = bicycleService;
		this.bicycleModelService = bicycleModelService;
	}

	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		String bicycleIdParam = request.getParameter(BICYCLE_ID_PARAM);
		logger.debug("bicycleIdParam = " + bicycleIdParam);
		try {
			if (bicycleIdParam == null || bicycleIdParam.isEmpty()) {
				request.setAttribute(COMMAND_ATTRIBUTE, ADD_COMMAND_ATTRIBUTE);
			} else {
				logger.debug("UPDATE_COMMAND_ATTRIBUTE = " + UPDATE_COMMAND_ATTRIBUTE);
				request.setAttribute(COMMAND_ATTRIBUTE, UPDATE_COMMAND_ATTRIBUTE);
				long bicycleId = Long.parseLong(bicycleIdParam);
				bicycleService.setLanguage(language);
				Bicycle bicycle = bicycleService.findEntityById(bicycleId);
				request.setAttribute(BICYCLE_ATTRIBUTE, bicycle);
			}	
		
			rentalPointService.setLanguage(language);
			List<RentalPoint> rentalPoints  = rentalPointService.findAll();
			request.setAttribute(RENTALPOINTS_ATTRIBUTE, rentalPoints);
			
			bicycleModelService.setLanguage(language);
			List<BicycleModel> bicycleModels = bicycleModelService.findAll();
			request.setAttribute(BICYCLEMODELS_ATTRIBUTE, bicycleModels);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}			
		
		return new ForwardResponse(CommandResponse.BICYCLE_PAGE);
	}

}
