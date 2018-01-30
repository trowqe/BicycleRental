package by.epam.bicycle.controller.command.user;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.RentService;
import by.epam.bicycle.utils.TariffUtils;

public class ReturnBicycleCommand implements ActionCommand {
	private static final String RENT_ID_PARAM = "rentid";
	private static final String RENT_ID_ATTRIBUTE = "rentid";
	private static final String RENTPOINTID_ATTRIBUTE = "orderRentPointId";
	private static final String BICYCLEID_ATTRIBUTE = "orderBicycleId";
	private static final String RENTPOINT_ATTRIBUTE = "orderRentPoint";
	private static final String BICYCLEMODEL_ATTRIBUTE = "orderBicycleModel";
	private static final String MODELID_ATTRIBUTE = "orderModelId";
	private static final String CREATEDATETIME_ATTRIBUTE = "createdatetime";
	private static final String TARIFF_ATTRIBUTE = "tariff";
	private static final String AMOUNT_ATTRIBUTE = "amount";
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		
		long rentId = Long.parseLong(request.getParameter(RENT_ID_PARAM));
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
			
			RentService rentService = new RentService(language);
			Rent rent = rentService.findEntityById(rentId);
			Bicycle bicycle = rent.getBicycle();
			long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			long modelId = bicycle.getModel().getId();
			Tariff tariff = rent.getTariff();
					
			request.setAttribute(RENT_ID_ATTRIBUTE, rent.getId());
			request.setAttribute(RENTPOINTID_ATTRIBUTE, rentPointId);
			request.setAttribute(BICYCLEID_ATTRIBUTE, bicycle.getId());
			request.setAttribute(RENTPOINT_ATTRIBUTE, orderRentPoint);
			request.setAttribute(BICYCLEMODEL_ATTRIBUTE, orderBicycleModel);
			request.setAttribute(MODELID_ATTRIBUTE, modelId);
			request.setAttribute(CREATEDATETIME_ATTRIBUTE, rent.getCreateDateTime());
			request.setAttribute(TARIFF_ATTRIBUTE, tariff);
			
			BigDecimal amount = TariffUtils.getAmountOfRent(rent);
			request.setAttribute(AMOUNT_ATTRIBUTE, amount);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return new ForwardResponse(CommandResponse.RETURNBICYCLE_PAGE);
	}

}
