package by.epam.bicycle.controller.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.controller.response.CommandMessage;


public class CreateOrderCommand implements ActionCommand {
	private static final String ORDER_BICYCLE_ID_PARAM = "orderbikeid";
	private static final String ORDER_TARIFF_ID_PARAM = "tariff";

	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(false);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);	
		User user =  (User) request.getSession().getAttribute(SessionAttributes.USER);
		
		long bicycleId = Long.parseLong(request.getParameter(ORDER_BICYCLE_ID_PARAM));
		long tariffId = Long.parseLong(request.getParameter(ORDER_TARIFF_ID_PARAM));
		
		Bicycle bicycle = new Bicycle(bicycleId);
		Tariff tariff = new Tariff(tariffId);
		Rent rent = new Rent(user, bicycle, tariff);
		
		try {
			RentService rentService = new RentService();
			rentService.create(rent);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}	
	
		CommandMessage message = new CommandMessage(language, MessageManager.ORDERCREATED, CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.BICYCLES_COMMAND, message);
	}

}
