package by.epam.bicycle.controller.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

public class UsersCommand implements ActionCommand {
	private final static String USERS_ATTRIBUTE = "users";
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		try {
			UserService userService = new UserService(language);
			List<User> users = userService.findAllUsers();
	
			request.setAttribute(USERS_ATTRIBUTE, users);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		session.setAttribute(SessionAttributes.PAGE, SessionAttributes.USERS_PAGE);
		return new ForwardResponse(CommandResponse.USERS_PAGE);
	}

}
