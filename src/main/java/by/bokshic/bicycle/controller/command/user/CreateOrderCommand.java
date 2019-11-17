package by.bokshic.bicycle.controller.command.user;

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
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandMessageTypeEnum;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.entity.Tariff;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.RentService;
import by.bokshic.bicycle.controller.response.CommandMessage;


public class CreateOrderCommand implements ActionCommand {
	private static final String ORDER_BICYCLE_ID_PARAM = "orderbikeid";
	private static final String ORDER_TARIFF_ID_PARAM = "tariff";

	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
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
