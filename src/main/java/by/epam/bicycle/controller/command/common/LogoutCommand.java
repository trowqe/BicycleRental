package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.command.ActionCommand;

public class LogoutCommand implements ActionCommand {
	private static Logger logger = LogManager.getLogger(LogoutCommand.class);
	
	public String execute(HttpServletRequest request) {
		logger.debug("logout | start");
		String page = ConfigurationManager.getProperty("path.page.index");
		request.getSession().invalidate();
		return page;
	}

}
