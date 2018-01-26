package by.epam.bicycle.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.RentalPointService;

public class CreateOrderCommand implements ActionCommand {
	private static final String ORDER_BICYCLE_ID_PARAM = "orderbikeid";
	private static final String ORDER_TARIFF_ID_PARAM = "tariff";

	private static Logger logger = LogManager.getLogger(CreateOrderCommand.class);
	
	public String execute(HttpServletRequest request) {
		User user =  (User) request.getSession().getAttribute("user");
		long userId = user.getId();
		long bicycleId = Long.parseLong(request.getParameter(ORDER_BICYCLE_ID_PARAM));
		long tariffId = Long.parseLong(request.getParameter(ORDER_TARIFF_ID_PARAM));
		logger.debug("userId = " + userId);
		logger.debug("bicycleId = " + bicycleId);
		logger.debug("tariffId = " + tariffId);
		try {
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
			
			RentService rentService = new RentService();
			rentService.createNewRent(userId, bicycleId, tariffId);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);	
		}		
		return ConfigurationManager.getProperty("path.page.bicycles");
	}

}
