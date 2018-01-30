package by.epam.bicycle.controller.command.user;

import java.math.BigDecimal;
import java.util.Date;

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
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.UserService;

public class CloseRentCommand implements ActionCommand {
	private static final String RENT_ID_PARAM = "rentid";
	private static final String AMOUNT_PARAM = "amount";
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		long rentId = Long.parseLong(request.getParameter(RENT_ID_PARAM));
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		
		try {	
			User user = (User) session.getAttribute(SessionAttributes.USER);
			
			//TO DO!! SEPARATE SERVICE
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
			
/*			RentService service = new RentService(language);
			List<Rent> rents = service.getRentsByUserId(user.getId());
			request.setAttribute("rents", rents);*/
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		CommandMessage message = new CommandMessage(language, MessageManager.RENTCLOSED, CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.RENTS_COMMAND, message);
	}

}
