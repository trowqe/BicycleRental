package by.bokshic.bicycle.controller.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandMessage;
import by.bokshic.bicycle.controller.response.CommandMessageTypeEnum;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import by.bokshic.bicycle.config.MessageManager;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.BicycleService;

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
