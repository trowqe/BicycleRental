package by.bokshic.bicycle.controller;

import javax.servlet.http.HttpServletRequest;

import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.controller.command.CommandEnum;
import by.bokshic.bicycle.controller.command.common.EmptyCommand;
import by.bokshic.bicycle.controller.exception.CommandException;

public class ActionFactory {
	private static final String COMMAND_PARAM = "command";

	public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter(COMMAND_PARAM);

		if (action == null || action.isEmpty()) {
			return current;
		}

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCommand();
		} catch (IllegalArgumentException e) {
			throw new CommandException(e);
		}

		return current;
	}

}
