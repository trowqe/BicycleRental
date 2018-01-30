package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;

public class LogoutCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(LogoutCommand.class);
	
	public CommandResponse execute(HttpServletRequest request) {
		logger.debug("logout | start");
		request.getSession().invalidate();
		return new RedirectResponse(CommandResponse.INDEX_PAGE);
	}

}
