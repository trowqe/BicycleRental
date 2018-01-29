package by.epam.bicycle.controller.command.admin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

public class UpdateUserStatusCommand implements ActionCommand {
	private final static String USER_ID_PARAM = "userid";
	private final static String STATUS_PARAM = "status";
	private final static String BLOCK_STATUS = "block";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long userId = Long.parseLong(request.getParameter(USER_ID_PARAM));
		String status = request.getParameter(STATUS_PARAM);
		
		try {					
			UserService userService = new UserService();
			short newStatus = Short.parseShort(ConfigurationManager.getProperty("user_status.active"));
			if (status.equals(BLOCK_STATUS)) {
				newStatus = Short.parseShort(ConfigurationManager.getProperty("user_status.blocked"));
			}
			userService.updateStatus(userId, newStatus);
			
			List<User> users = userService.findAllUsers();
			request.setAttribute("users", users);
		} catch (ServiceException e) {
			throw new CommandException();
		}
		
		return ConfigurationManager.getProperty("path.page.users");
	}

}
