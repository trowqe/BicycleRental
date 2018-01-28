package by.epam.bicycle.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;

public class RentsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute("language");
		User user = (User) session.getAttribute("user");
		long userId = user.getId();
			
		RentService service = new RentService(language);
		List<Rent> rents;
		try {
			rents = service.getRentsByUserId(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute("rents", rents);

		return ConfigurationManager.getProperty("path.page.rents");
	}

}
