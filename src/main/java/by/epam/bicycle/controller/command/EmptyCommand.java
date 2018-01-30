package by.epam.bicycle.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;

public class EmptyCommand implements ActionCommand {

	public CommandResponse execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		CommandResponse response = new ForwardResponse(page);
		return response;
	}

}
