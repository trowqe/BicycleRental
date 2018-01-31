package by.epam.bicycle.controller;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.command.CommandEnum;
import by.epam.bicycle.controller.command.common.EmptyCommand;
import by.epam.bicycle.controller.exception.CommandException;

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
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			throw new CommandException(e);
		}
		return current;
	}
	
}
