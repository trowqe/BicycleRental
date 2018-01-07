package by.epam.bicycle.service.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}

}
