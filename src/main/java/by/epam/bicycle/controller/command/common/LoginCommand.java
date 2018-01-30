package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

public class LoginCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(LoginCommand.class);

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);

		User user = null;

		UserService service = new UserService();
		
		try {
			user = service.getUserByLoginAndPassword(login, pass);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);

		if (user == null) {
			CommandMessage message = new CommandMessage(language, MessageManager.LOGINERROR,
					CommandMessageTypeEnum.WRONG);
			return new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		}

		logger.debug("user = " + user.getId() + " | " + user.getName());

		if (user.isBlocked()) {
			CommandMessage message = new CommandMessage(language, MessageManager.USERBLOCKED,
					CommandMessageTypeEnum.WRONG);
			return new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		}

		session.setAttribute(SessionAttributes.USER, user);
		Role userRole = user.getRole();
		logger.debug("userRole = " + userRole.getName());

		if (userRole.isUser()) {
			page = CommandResponse.BICYCLES_COMMAND;
			session.setAttribute(SessionAttributes.PAGE, SessionAttributes.BICYCLES_PAGE);
		} else {
			page = CommandResponse.USERS_COMMAND;
			session.setAttribute(SessionAttributes.PAGE, SessionAttributes.USERS_PAGE);
		}
		
		return new RedirectResponse(page);
	}

}
