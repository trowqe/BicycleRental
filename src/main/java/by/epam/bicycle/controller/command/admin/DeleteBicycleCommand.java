package by.epam.bicycle.controller.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;

public class DeleteBicycleCommand implements ActionCommand {
	public final static String BICYCLE_ID_PARAM = "bicycleid";
	private BicycleService service;

	public DeleteBicycleCommand(BicycleService service) {
		this.service = service;
	}

	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);

		String bicycleIdParam = request.getParameter(BICYCLE_ID_PARAM);
		long bicycleId = Long.parseLong(bicycleIdParam);

		try {
			service.delete(bicycleId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		CommandMessage message = new CommandMessage(language, MessageManager.BICYCLEDELETED,
				CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.BICYCLES_COMMAND, message);
	}

}
