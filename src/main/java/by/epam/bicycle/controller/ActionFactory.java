package by.epam.bicycle.controller;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.command.CommandEnum;
import by.epam.bicycle.controller.command.EmptyCommand;

public class ActionFactory {
	
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		
		if (action == null || action.isEmpty()) {
			return current;
		}
		
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			//request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
	
}
