package by.epam.bicycle.controller.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

public class UsersCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(UsersCommand.class);
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute("language");
		
		try {
			UserService userService = new UserService(language);
			List<User> users = userService.findAllUsers();
			logger.debug("users = " + users);
			request.setAttribute("users", users);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty("path.page.users");
	}

}
