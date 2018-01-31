package by.epam.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;

public class LogoutCommand implements ActionCommand {
	
	public CommandResponse execute(HttpServletRequest request) {
		request.getSession().invalidate();
		return new RedirectResponse(CommandResponse.INDEX_PAGE);
	}

}
