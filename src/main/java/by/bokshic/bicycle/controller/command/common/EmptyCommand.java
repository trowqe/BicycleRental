package by.bokshic.bicycle.controller.command.common;

import javax.servlet.http.HttpServletRequest;

import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.controller.command.ActionCommand;

public class EmptyCommand implements ActionCommand {

	public CommandResponse execute(HttpServletRequest request) {
		return new ForwardResponse(CommandResponse.INDEX_PAGE);
	}

}
