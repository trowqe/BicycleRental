package by.epam.bicycle.controller.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.dao.impl.RentDAO;
import by.epam.bicycle.dao.impl.RentalPointDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;

public class CreateOrderCommand implements ActionCommand {
	private static final String ORDER_BICYCLE_ID_PARAM = "orderbikeid";
	private static final String ORDER_TARIFF_ID_PARAM = "tariff";

	private static Logger logger = LogManager.getLogger(CreateOrderCommand.class);
	
	public String execute(HttpServletRequest request) {
		User user =  (User) request.getSession().getAttribute("user");
		long userId = user.getId();
		long bicycleId = Long.parseLong(request.getParameter(ORDER_BICYCLE_ID_PARAM));
		long tariffId = Long.parseLong(request.getParameter(ORDER_TARIFF_ID_PARAM));
		logger.debug("userId = " + userId);
		logger.debug("bicycleId = " + bicycleId);
		logger.debug("tariffId = " + tariffId);
		try {
			loadBicyclesPage(request);
			createNewRent(userId, bicycleId, tariffId);
		} catch (CommandException e) {
			logger.error(e.getMessage(), e);		
		}		
		return ConfigurationManager.getProperty("path.page.bicycles");
	}

	private void createNewRent(long userId, long bicycleId, long tariffId) throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		RentDAO dao = new RentDAO(connection);
		
		User user = new User(userId);
		Bicycle bicycle = new Bicycle(bicycleId);
		Tariff tariff = new Tariff(tariffId);
		
		Rent rent = new Rent(user, bicycle, tariff);
		
		try {
			dao.create(rent);
		} catch (DAOException e) {
			throw new CommandException("Cannot create rent", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
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

	private List<Bicycle> getActiveBicyclesByFilter(long rentalPointId, long bicycleTypeId, String firm, String model) throws CommandException {
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
	
	public  void loadBicyclesPage(HttpServletRequest request) throws CommandException {
		List<RentalPoint> rentalPoints = getAllRentalPoints();
		request.setAttribute("rentalPoints", rentalPoints);

		List<BicycleType> bicycleTypes = getAllBicycleTypes();
		request.setAttribute("bicycleTypes", bicycleTypes);

		List<Bicycle> bicycles = getActiveBicyclesByFilter(-1, -1, "", "");
		request.setAttribute("bicycles", bicycles);
	}


}
