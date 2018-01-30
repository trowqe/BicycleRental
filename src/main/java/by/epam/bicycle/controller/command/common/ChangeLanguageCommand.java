package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.command.ActionCommand;

public class ChangeLanguageCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request) {
		
		String language = request.getParameter("language");
			
		if (!(language == null) && !(language.isEmpty())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("language", language);
		}

		return ConfigurationManager.getProperty("path.page.index");
	}

}
