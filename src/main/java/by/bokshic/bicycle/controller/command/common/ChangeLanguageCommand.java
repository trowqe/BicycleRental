package by.bokshic.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;

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
