package by.epam.bicycle.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.controller.CommandException;

public interface ActionCommand {
	String execute(HttpServletRequest request) throws CommandException;
}
