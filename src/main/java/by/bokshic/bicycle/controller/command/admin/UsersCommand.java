package by.bokshic.bicycle.controller.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.UserService;

public class UsersCommand implements ActionCommand {
	private final static String USERS_ATTRIBUTE = "users";
	
	private UserService userService;
	
	public UsersCommand(UserService userService) {
		this.userService = userService;
	}

	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		try {
			userService.setLanguage(language);
			List<User> users = userService.findAllUsers();
	
			request.setAttribute(USERS_ATTRIBUTE, users);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		session.setAttribute(SessionAttributes.PAGE, SessionAttributes.USERS_PAGE);
		return new ForwardResponse(CommandResponse.USERS_PAGE);
	}

}
