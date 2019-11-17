package by.bokshic.bicycle.controller.command.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.RentService;

public class RentsCommand implements ActionCommand {
	private final static String RENTS_ATTRIBUTE = "rents";
	
	private RentService service;
	
	public RentsCommand(RentService service) {
		this.service = service;
	}
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();	
		
		User user = (User) session.getAttribute(SessionAttributes.USER);
		long userId = user.getId();
		
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		service.setLanguage(language);
		
		try {
			List<Rent> rents = service.getRentsByUserId(userId);
			request.setAttribute(RENTS_ATTRIBUTE, rents);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		session.setAttribute(SessionAttributes.PAGE, SessionAttributes.RENTS_PAGE);
		return new ForwardResponse(CommandResponse.RENTS_PAGE);
	}

}
