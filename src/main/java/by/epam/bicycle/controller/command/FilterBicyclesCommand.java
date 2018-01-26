package by.epam.bicycle.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentalPointService;

public class FilterBicyclesCommand implements ActionCommand {
	private static final String PARAM_NAME_RENTAL_POINT = "rentalpoint";
	private static final String PARAM_NAME_BICYCLE_TYPE = "bicycletype";
	private static final String PARAM_NAME_FIRM = "firm";
	private static final String PARAM_NAME_MODEL = "model";

	public String execute(HttpServletRequest request) {
		List<RentalPoint> rentalPoints;
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute("language");
			
			RentalPointService rentalPointService = new RentalPointService(language);
			rentalPoints = rentalPointService.findAll();
			request.setAttribute("rentalPoints", rentalPoints);
			
			BicycleTypeService bicycleTypeService = new BicycleTypeService(language);
			List<BicycleType> bicycleTypes = bicycleTypeService.findAll();
			request.setAttribute("bicycleTypes", bicycleTypes);

			long rentalPointID = -1;
			if ((request.getParameter(PARAM_NAME_RENTAL_POINT) != null)
					&& (!request.getParameter(PARAM_NAME_RENTAL_POINT).isEmpty())) {
				rentalPointID = Long.parseLong(request.getParameter(PARAM_NAME_RENTAL_POINT));
			}

			long bicycleTypeID = -1;
			if ((request.getParameter(PARAM_NAME_BICYCLE_TYPE) != null)
					&& (!request.getParameter(PARAM_NAME_BICYCLE_TYPE).isEmpty())) {
				bicycleTypeID = Long.parseLong(request.getParameter(PARAM_NAME_BICYCLE_TYPE));
			}
			//TO DO!!!
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
			
			BicycleService bicycleService = new BicycleService(language);
			List<Bicycle> bicycles = bicycleService.getActiveBicyclesByFilter(rentalPointID, bicycleTypeID, firm, model);
			request.setAttribute("bicycles", bicycles);

			request.setAttribute("firm", firm);
			request.setAttribute("model", model);
			request.setAttribute("rentalpoint", rentalPointID);
			request.setAttribute("bicycletype", bicycleTypeID);
		} catch (ServiceException e) {

		}
		return ConfigurationManager.getProperty("path.page.bicycles");
	}

}
