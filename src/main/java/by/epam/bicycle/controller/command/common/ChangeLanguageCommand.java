package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;

public class ChangeLanguageCommand implements ActionCommand {
	
	@Override
	public CommandResponse execute(HttpServletRequest request) {
		
		String language = request.getParameter(SessionAttributes.LANGUAGE);
			
		if (!(language == null) && !(language.isEmpty())) {
			HttpSession session = request.getSession(true);
			session.setAttribute(SessionAttributes.LANGUAGE, language);
		}

		return new RedirectResponse(CommandResponse.INDEX_PAGE);
	}

}
