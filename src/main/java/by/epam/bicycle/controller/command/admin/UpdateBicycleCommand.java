package by.epam.bicycle.controller.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;

public class UpdateBicycleCommand implements ActionCommand {
	private final static String RENTAL_POINT_ID_PARAM = "rentalpoint";
	private final static String BICYCLE_MODEL_ID_PARAM = "bicyclemodel";
	private final static String BICYCLE_ID_PARAM = "bicycleid";

	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(false);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		String rentalPointIdParam = request.getParameter(RENTAL_POINT_ID_PARAM);
		String bicycleModelIdParam = request.getParameter(BICYCLE_MODEL_ID_PARAM);
		String bicycleIdParam = request.getParameter(BICYCLE_ID_PARAM);
		
		long rentalPointId = Long.parseLong(rentalPointIdParam);
		long bicycleModelId = Long.parseLong(bicycleModelIdParam);
		long bicycleId = Long.parseLong(bicycleIdParam);
		
		BicycleService service = new BicycleService();
		try {
			service.updateByPointAndModelId(bicycleId, rentalPointId, bicycleModelId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		CommandMessage message = new CommandMessage(language, MessageManager.BICYCLEUPDATED,
				CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.BICYCLES_COMMAND, message);
	}

}
