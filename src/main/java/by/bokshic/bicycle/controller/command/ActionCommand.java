package by.bokshic.bicycle.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;

public interface ActionCommand {
	CommandResponse execute(HttpServletRequest request) throws CommandException;
}
