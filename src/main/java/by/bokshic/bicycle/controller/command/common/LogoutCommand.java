package by.bokshic.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import by.bokshic.bicycle.controller.command.ActionCommand;

public class LogoutCommand implements ActionCommand {
	
	public CommandResponse execute(HttpServletRequest request) {
		request.getSession().invalidate();
		return new RedirectResponse(CommandResponse.INDEX_PAGE);
	}

}
