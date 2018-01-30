package by.epam.bicycle.controller.command.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.TariffService;

public class PrepareOrderCommand implements ActionCommand  {
	private static final String BICYCLE_ID_PARAM = "bicycleid";
		
	public String execute(HttpServletRequest request) throws CommandException {
		long bicycleId = Long.parseLong(request.getParameter(BICYCLE_ID_PARAM));
		try {
			HttpSession session = request.getSession(true);
			String language = (String) session.getAttribute("language");
			
			BicycleService bicycleService = new BicycleService(language);
			Bicycle bicycle = bicycleService.findEntityById(bicycleId);
			Long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			
			request.setAttribute("orderRentPointId", rentPointId);
			request.setAttribute("orderBicycleId", bicycle.getId());
			request.setAttribute("orderRentPoint", orderRentPoint);
			request.setAttribute("orderBicycleModel", orderBicycleModel);
			
			Long bicycleTypeId = bicycle.getModel().getBicycleType().getId();
			TariffService tariffService = new TariffService(language);
			List<Tariff> tariffs = tariffService.getTarriffListByBicycleTypeId(bicycleTypeId);
			request.setAttribute("tariffs", tariffs);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return ConfigurationManager.getProperty("path.page.prepareorder");
	}
	

}
