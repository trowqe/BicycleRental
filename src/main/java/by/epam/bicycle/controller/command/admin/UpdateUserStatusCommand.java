package by.epam.bicycle.controller.command.admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;

public class UpdateUserStatusCommand implements ActionCommand {
	private final static String USER_ID_PARAM = "userid";
	private final static String STATUS_PARAM = "status";
	private final static String BLOCK_STATUS = "block";
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(false);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		long userId = Long.parseLong(request.getParameter(USER_ID_PARAM));
		String status = request.getParameter(STATUS_PARAM);
		
		try {					
			UserService userService = new UserService();
			short newStatus = Short.parseShort(ConfigurationManager.getProperty(ConfigurationManager.USER_STATUS_ACTIVE));
			if (status.equals(BLOCK_STATUS)) {
				newStatus = Short.parseShort(ConfigurationManager.getProperty(ConfigurationManager.USER_STATUS_BLOCKED));
			}
			userService.updateStatus(userId, newStatus);
		
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		CommandMessage message = new CommandMessage(language, MessageManager.USERSTATUSCHANGED, CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.USERS_COMMAND, message);
	}

}
