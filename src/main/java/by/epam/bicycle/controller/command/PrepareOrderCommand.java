package by.epam.bicycle.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.BicycleService;
import by.epam.bicycle.service.impl.TariffService;

public class PrepareOrderCommand implements ActionCommand {
	private static final String BICYCLE_ID_PARAM = "bicycleid";
	private static Logger logger = LogManager.getLogger(PrepareOrderCommand.class);
		
	public String execute(HttpServletRequest request) {
		long bicycleId = Long.parseLong(request.getParameter(BICYCLE_ID_PARAM));
		try {
			BicycleService bicycleService = new BicycleService();
			Bicycle bicycle = bicycleService.findEntityById(bicycleId);
			Long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			request.setAttribute("orderRentPointId", rentPointId);
			request.setAttribute("orderBicycleId", bicycle.getId());
			request.setAttribute("orderRentPoint", orderRentPoint);
			request.setAttribute("orderBicycleModel", orderBicycleModel);
			
			Long bicycleTypeId = bicycle.getModel().getBicycleType().getId();
			TariffService tariffService = new TariffService();
			List<Tariff> tariffs = tariffService.getTarriffListByBicycleTypeId(bicycleTypeId);
			request.setAttribute("tariffs", tariffs);
			
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
		}
		
		
		return ConfigurationManager.getProperty("path.page.prepareorder");
	}
	

}
