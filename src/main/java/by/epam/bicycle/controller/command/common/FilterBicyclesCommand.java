package by.epam.bicycle.controller.command.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentalPointService;

public class FilterBicyclesCommand implements ActionCommand {
	public static final String PARAM_NAME_RENTAL_POINT = "rentalpoint";
	public static final String PARAM_NAME_BICYCLE_TYPE = "bicycletype";
	public static final String PARAM_NAME_FIRM = "firm";
	public static final String PARAM_NAME_MODEL = "model";
	public static final String BICYCLES_ATTRIBUTE = "bicycles";
	public static final String FIRM_ATTRIBUTE = "firm";
	public static final String MODEL_ATTRIBUTE = "model";
	public static final String RENTALPOINT_ATTRIBUTE = "rentalpoint";
	public static final String BICYCLETYPE_ATTRIBUTE = "bicycletype";
	public static final String RENTALPOINTS_ATTRIBUTE = "rentalPoints";
	public static final String BICYCLETYPES_ATTRIBUTE = "bicycleTypes";
	
	private BicycleService bicycleService;
	private RentalPointService rentalPointService;
	private BicycleTypeService bicycleTypeService;
	
	
	public FilterBicyclesCommand(BicycleService bicycleService, RentalPointService rentalPointService, BicycleTypeService bicycleTypeService) {
		this.rentalPointService = rentalPointService;
		this.bicycleTypeService = bicycleTypeService;
		this.bicycleService = bicycleService;
	}

	private void loadFilter(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		rentalPointService.setLanguage(language);
		List<RentalPoint> rentalPoints = rentalPointService.findAll();
		request.setAttribute(RENTALPOINTS_ATTRIBUTE, rentalPoints);
		
		bicycleTypeService.setLanguage(language);
		List<BicycleType> bicycleTypes = bicycleTypeService.findAll();
		request.setAttribute(BICYCLETYPES_ATTRIBUTE, bicycleTypes);
	}
	
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		try {	
			
			loadFilter(request);
			
			long rentalPointID = -1;
			String rentalPointParam = request.getParameter(PARAM_NAME_RENTAL_POINT);
			if (!((rentalPointParam == null) || (rentalPointParam).isEmpty())) {
				rentalPointID = Long.parseLong(rentalPointParam);
			}

			long bicycleTypeID = -1;
			if ((request.getParameter(PARAM_NAME_BICYCLE_TYPE) != null)
					&& (!request.getParameter(PARAM_NAME_BICYCLE_TYPE).isEmpty())) {
				bicycleTypeID = Long.parseLong(request.getParameter(PARAM_NAME_BICYCLE_TYPE));
			}
			
			String firm = "";
			String firmParam = request.getParameter(PARAM_NAME_FIRM);
			if (firmParam != null) {
				firm = firmParam;
			}
			
			String model = "";
			String modelParam = request.getParameter(PARAM_NAME_MODEL);
			if (modelParam != null) {
				model = modelParam;
			}
			
			bicycleService.setLanguage(language);
			List<Bicycle> bicycles = bicycleService.getActiveBicyclesByFilter(rentalPointID, bicycleTypeID, firm, model);
			
			request.setAttribute(BICYCLES_ATTRIBUTE, bicycles);
			request.setAttribute(FIRM_ATTRIBUTE, firm);
			request.setAttribute(MODEL_ATTRIBUTE, model);
			request.setAttribute(RENTALPOINT_ATTRIBUTE, rentalPointID);
			request.setAttribute(BICYCLETYPE_ATTRIBUTE, bicycleTypeID);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}		
		User user = (User) session.getAttribute(SessionAttributes.USER);
		
		String page = CommandResponse.BICYCLES_PAGE;
		if (user.getRole().isAdmin()) {
			page = CommandResponse.ADMINBICYCLES_PAGE;
		}
		
		session.setAttribute(SessionAttributes.PAGE, SessionAttributes.BICYCLES_PAGE);				
		return new ForwardResponse(page);
		
	}

}
