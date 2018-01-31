package by.epam.bicycle.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandResponse;

public interface ActionCommand {
	CommandResponse execute(HttpServletRequest request) throws CommandException;
}
