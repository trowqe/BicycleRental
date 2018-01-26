package by.epam.bicycle.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.BicycleTypeService;
import by.epam.bicycle.service.impl.RentalPointService;
import by.epam.bicycle.service.impl.UserService;

public class LoginCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(LoginCommand.class);
	
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);

		User user = null;

		try {
			UserService service = new UserService();
			user = service.getUserByLoginAndPassword(login, pass);

			if (user != null) {
				logger.debug("user = " + user.getId() + " | " + user.getName());

				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				String language = (String) session.getAttribute("language");

				Role userRole = user.getRole();
				logger.debug("userRole = " + userRole.getName());
				if (userRole.isUser()) {
					page = ConfigurationManager.getProperty("path.page.bicycles");
					RentalPointService rentalPointService = new RentalPointService(language);
					List<RentalPoint> rentalPoints = rentalPointService.findAll();
					request.setAttribute("rentalPoints", rentalPoints);
					
					BicycleTypeService bicycleTypeService = new BicycleTypeService(language);
					List<BicycleType> bicycleTypes = bicycleTypeService.findAll();
					request.setAttribute("bicycleTypes", bicycleTypes);
					
					BicycleService bicycleService  = new BicycleService(language);
					List<Bicycle> bicycles = bicycleService.getActiveBicyclesByFilter(-1, -1, "", "");
					request.setAttribute("bicycles", bicycles);
				} else {
					page = ConfigurationManager.getProperty("path.page.main_admin");
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;

	}

}
