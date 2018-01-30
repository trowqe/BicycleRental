package by.epam.bicycle.controller.command.user;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.utils.TariffUtils;

public class ReturnBicycleCommand implements ActionCommand {
	private static final String RENT_ID_PARAM = "rentid";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long rentId = Long.parseLong(request.getParameter(RENT_ID_PARAM));
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute("language");
			
			RentService rentService = new RentService(language);
			Rent rent = rentService.findEntityById(rentId);
			Bicycle bicycle = rent.getBicycle();
			long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			long modelId = bicycle.getModel().getId();
			Tariff tariff = rent.getTariff();
					
			request.setAttribute("rentid", rent.getId());
			request.setAttribute("orderRentPointId", rentPointId);
			request.setAttribute("orderBicycleId", bicycle.getId());
			request.setAttribute("orderRentPoint", orderRentPoint);
			request.setAttribute("orderBicycleModel", orderBicycleModel);
			request.setAttribute("orderModelId", modelId);
			request.setAttribute("createdatetime", rent.getCreateDateTime());
			request.setAttribute("tariff", tariff);
			
			BigDecimal amount = TariffUtils.getAmountOfRent(rent);
			request.setAttribute("amount", amount);
			
		} catch (ServiceException e) {
			throw new CommandException();
		}
		
		return ConfigurationManager.getProperty("path.page.returnbicycle");
	}

}
