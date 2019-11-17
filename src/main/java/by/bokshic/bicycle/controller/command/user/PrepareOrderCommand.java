package by.bokshic.bicycle.controller.command.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.ForwardResponse;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.Tariff;
import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.service.impl.BicycleService;
import by.bokshic.bicycle.service.impl.TariffService;

public class PrepareOrderCommand implements ActionCommand  {
	public static final String BICYCLE_ID_PARAM = "bicycleid";
	private static final String RENTPOINTID_ATTRIBUTE = "orderRentPointId";
	private static final String BICYCLEID_ATTRIBUTE = "orderBicycleId";
	private static final String RENTPOINT_ATTRIBUTE = "orderRentPoint";
	private static final String BICYCLEMODEL_ATTRIBUTE = "orderBicycleModel";
	private static final String TARIFFS_ATTRIBUTE = "tariffs";
	
	private BicycleService bicycleService;
	private TariffService tariffService;
	
	public PrepareOrderCommand(BicycleService bicycleService, TariffService tariffService) {
		this.bicycleService = bicycleService;
		this.tariffService = tariffService;
	}

	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		long bicycleId = Long.parseLong(request.getParameter(BICYCLE_ID_PARAM));
		try {
			HttpSession session = request.getSession();
			String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
			
			bicycleService.setLanguage(language);
			Bicycle bicycle = bicycleService.findEntityById(bicycleId);
			Long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			
			request.setAttribute(RENTPOINTID_ATTRIBUTE, rentPointId);
			request.setAttribute(BICYCLEID_ATTRIBUTE, bicycle.getId());
			request.setAttribute(RENTPOINT_ATTRIBUTE, orderRentPoint);
			request.setAttribute(BICYCLEMODEL_ATTRIBUTE, orderBicycleModel);
			
			long bicycleTypeId = bicycle.getModel().getBicycleType().getId();
			tariffService.setLanguage(language);
			List<Tariff> tariffs = tariffService.getTarriffListByBicycleTypeId(bicycleTypeId);
			request.setAttribute(TARIFFS_ATTRIBUTE, tariffs);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return new ForwardResponse(CommandResponse.PREPAREORDER_PAGE);
	}
	

}
