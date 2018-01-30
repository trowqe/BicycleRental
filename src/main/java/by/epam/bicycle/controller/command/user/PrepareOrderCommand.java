package by.epam.bicycle.controller.command.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.ForwardResponse;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.TariffService;

public class PrepareOrderCommand implements ActionCommand  {
	private static final String BICYCLE_ID_PARAM = "bicycleid";
	private static final String RENTPOINTID_ATTRIBUTE = "orderRentPointId";
	private static final String BICYCLEID_ATTRIBUTE = "orderBicycleId";
	private static final String RENTPOINT_ATTRIBUTE = "orderRentPoint";
	private static final String BICYCLEMODEL_ATTRIBUTE = "orderBicycleModel";
	private static final String TARIFFS_ATTRIBUTE = "tariffs";
	
	
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		long bicycleId = Long.parseLong(request.getParameter(BICYCLE_ID_PARAM));
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
			
			BicycleService bicycleService = new BicycleService(language);
			Bicycle bicycle = bicycleService.findEntityById(bicycleId);
			Long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			
			request.setAttribute(RENTPOINTID_ATTRIBUTE, rentPointId);
			request.setAttribute(BICYCLEID_ATTRIBUTE, bicycle.getId());
			request.setAttribute(RENTPOINT_ATTRIBUTE, orderRentPoint);
			request.setAttribute(BICYCLEMODEL_ATTRIBUTE, orderBicycleModel);
			
			Long bicycleTypeId = bicycle.getModel().getBicycleType().getId();
			TariffService tariffService = new TariffService(language);
			List<Tariff> tariffs = tariffService.getTarriffListByBicycleTypeId(bicycleTypeId);
			request.setAttribute(TARIFFS_ATTRIBUTE, tariffs);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return new ForwardResponse(CommandResponse.PREPAREORDER_PAGE);
	}
	

}
