package by.epam.bicycle.controller.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.dao.impl.TariffDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Tariff;

public class PrepareOrderCommand implements ActionCommand {
	private static final String BICYCLE_ID_PARAM = "bicycleid";
	
	private Bicycle getBicycleById(long bicycleId) throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			Bicycle bicycle = dao.findEntityById(bicycleId);
			return bicycle;
		} catch (DAOException e) {
			throw new CommandException("Cannot get bicycle", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
	private List<Tariff> getTarriffListByBicycleTypeId(long bicycleTypeId) throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		TariffDAO dao = new TariffDAO(connection);
		try {
			List<Tariff> tariffs = dao.findTariffsByBicycleTypeId(bicycleTypeId);
			return tariffs;
		} catch (DAOException e) {
			throw new CommandException("Cannot get tariffs", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
	public String execute(HttpServletRequest request) {
		long bicycleId = Long.parseLong(request.getParameter(BICYCLE_ID_PARAM));
		try {
			Bicycle bicycle = getBicycleById(bicycleId);
			Long rentPointId = bicycle.getPoint().getId();
			String orderRentPoint = bicycle.getPoint().getAddress();
			String orderBicycleModel =  bicycle.getModel().getFirm() + " " + bicycle.getModel().getModel();
			request.setAttribute("orderRentPointId", rentPointId);
			request.setAttribute("orderBicycleId", bicycle.getId());
			request.setAttribute("orderRentPoint", orderRentPoint);
			request.setAttribute("orderBicycleModel", orderBicycleModel);
			
			Long bicycleTypeId = bicycle.getModel().getBicycleType().getId();
			List<Tariff> tariffs = getTarriffListByBicycleTypeId(bicycleTypeId);
			request.setAttribute("tariffs", tariffs);
			
		} catch (CommandException e) {
			//add
		}
		
		
		return ConfigurationManager.getProperty("path.page.prepareorder");
	}
	

}
