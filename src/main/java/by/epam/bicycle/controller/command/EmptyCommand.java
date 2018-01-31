package by.epam.bicycle.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;

public class EmptyCommand implements ActionCommand {

	public CommandResponse execute(HttpServletRequest request) {
		return new ForwardResponse(CommandResponse.INDEX_PAGE);
	}

}
