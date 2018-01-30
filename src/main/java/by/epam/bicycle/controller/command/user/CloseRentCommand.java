package by.epam.bicycle.controller.command.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.UserService;

public class CloseRentCommand implements ActionCommand {
	private static final String RENT_ID_PARAM = "rentid";
	private static final String AMOUNT_PARAM = "amount";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long rentId = Long.parseLong(request.getParameter(RENT_ID_PARAM));
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute("language");
			User user = (User) session.getAttribute("user");
			
			RentService rentService = new RentService(language);
			Rent rent = rentService.findEntityById(rentId);
			rent.setFinishDateTime(new Date());
			BigDecimal amount = new BigDecimal(request.getParameter(AMOUNT_PARAM));
			rent.setAmount(amount);
			
			rentService.updateById(rentId, rent);
			
			UserService userService = new UserService(language);
			BigDecimal newBalance = user.getBalance().subtract(amount); 
			user.setBalance(newBalance);
			userService.updateBalance(user.getId(), newBalance);			
			
			RentService service = new RentService(language);
			List<Rent> rents = service.getRentsByUserId(user.getId());
			request.setAttribute("rents", rents);
			
		} catch (ServiceException e) {
			throw new CommandException();
		}
		
		return ConfigurationManager.getProperty("path.page.rents");
	}

}
