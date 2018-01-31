package by.epam.bicycle.controller.command.user;

import java.math.BigDecimal;
import java.util.Date;

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
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.service.impl.UserService;

public class CloseRentCommand implements ActionCommand {
	public static final String RENT_ID_PARAM = "rentid";
	public static final String AMOUNT_PARAM = "amount";
	
	private RentService rentService;
	private UserService userService;
	
	public CloseRentCommand(RentService rentService, UserService userService) {
		this.rentService = rentService;
		this.userService = userService;
	}

	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		rentService.setLanguage(language);
		userService.setLanguage(language);
		
		try {	
			User user = (User) session.getAttribute(SessionAttributes.USER);
			
			long rentId = Long.parseLong(request.getParameter(RENT_ID_PARAM));
			Rent rent = rentService.findEntityById(rentId);
			rent.setFinishDateTime(new Date());
			
			BigDecimal amount = new BigDecimal(request.getParameter(AMOUNT_PARAM));
			rent.setAmount(amount);
			
			rentService.updateById(rentId, rent);
			
			BigDecimal currentBalance = user.getBalance();
			if (currentBalance == null) {
				currentBalance = new BigDecimal(0);
			}
		
			BigDecimal newBalance = currentBalance.subtract(amount); 
			
			user.setBalance(newBalance);
			userService.updateBalance(user.getId(), newBalance);			
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		CommandMessage message = new CommandMessage(language, MessageManager.RENTCLOSED, CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.RENTS_COMMAND, message);
	}

}
