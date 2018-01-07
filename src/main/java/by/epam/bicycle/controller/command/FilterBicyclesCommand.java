package by.epam.bicycle.controller.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.dao.impl.RentalPointDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;

public class FilterBicyclesCommand implements ActionCommand {
	private static final String PARAM_NAME_RENTAL_POINT = "rentalpoint";
	private static final String PARAM_NAME_BICYCLE_TYPE = "bicycletype";
	private static final String PARAM_NAME_FIRM = "firm";
	private static final String PARAM_NAME_MODEL = "model";

	private List<RentalPoint> getAllRentalPoints() throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		RentalPointDAO dao = new RentalPointDAO(connection);
		try {
			List<RentalPoint> rentalPoints = dao.findAll();
			return rentalPoints;
		} catch (DAOException e) {
			throw new CommandException("Cannot get rental points", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

	private List<BicycleType> getAllBicycleTypes() throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleTypeDAO dao = new BicycleTypeDAO(connection);
		try {
			List<BicycleType> bicycleTypes = dao.findAll();
			return bicycleTypes;
		} catch (DAOException e) {
			throw new CommandException("Cannot get bicycle types", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

	private List<Bicycle> getActiveBicyclesByFilter(long rentalPointId, long bicycleTypeId, String firm, String model)
			throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			List<Bicycle> bicycles = dao.findActiveBicycleByFilter(rentalPointId, bicycleTypeId, firm, model);
			return bicycles;
		} catch (DAOException e) {
			throw new CommandException("Cannot get bicycles", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

	public String execute(HttpServletRequest request) {
		List<RentalPoint> rentalPoints;
		try {
			rentalPoints = getAllRentalPoints();

			request.setAttribute("rentalPoints", rentalPoints);

			List<BicycleType> bicycleTypes = getAllBicycleTypes();
			request.setAttribute("bicycleTypes", bicycleTypes);

			long rentalPointID = -1;
			if ((request.getParameter(PARAM_NAME_RENTAL_POINT) != null)
					&& (!request.getParameter(PARAM_NAME_RENTAL_POINT).isEmpty())) {
				rentalPointID = Long.parseLong(request.getParameter(PARAM_NAME_RENTAL_POINT));
			}

			long bicycleTypeID = -1;
			if ((request.getParameter(PARAM_NAME_BICYCLE_TYPE) != null)
					&& (!request.getParameter(PARAM_NAME_BICYCLE_TYPE).isEmpty())) {
				bicycleTypeID = Long.parseLong(request.getParameter(PARAM_NAME_BICYCLE_TYPE));
			}

			String firm = "";
			String firmParam = request.getParameter(PARAM_NAME_FIRM);
			if (firmParam != null) {
				firm = firmParam;
			}
			
			String model = "";
			String modelParam = request.getParameter(PARAM_NAME_MODEL);
			if (modelParam != null) {
				model = modelParam;
			}

			List<Bicycle> bicycles = getActiveBicyclesByFilter(rentalPointID, bicycleTypeID, firm, model);
			request.setAttribute("bicycles", bicycles);

			request.setAttribute("firm", firm);
			request.setAttribute("model", model);
			request.setAttribute("rentalpoint", rentalPointID);
			request.setAttribute("bicycletype", bicycleTypeID);
		} catch (CommandException e) {

		}
		return ConfigurationManager.getProperty("path.page.bicycles");
	}

}
