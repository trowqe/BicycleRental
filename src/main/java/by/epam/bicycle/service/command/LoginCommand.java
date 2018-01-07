package by.epam.bicycle.service.command;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.dao.impl.RentalPointDAO;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.CommandException;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	private static Logger logger = LogManager.getLogger(LoginCommand.class);

	private String getHashMD5(String str) {
		try {
			byte[] bytesOfMessage = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < thedigest.length; ++i) {
				sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot get bytes", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Cannot get MD5 hash", e);
		}
		return "";
	}

	private User getUserByLoginAndPassword(String login, String password) throws CommandException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		UserDAO dao = new UserDAO(connection);
		try {
			logger.debug("password = " + password);
			password = getHashMD5(password);
			logger.debug("password = " + password);
			User user = dao.findUserByLoginAndPassword(login, password);
			return user;
		} catch (DAOException e) {
			throw new CommandException("Cannot get user", e);
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

	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);

		User user = null;

		try {
			user = getUserByLoginAndPassword(login, pass);

			if (user != null) {
				logger.debug("user = " + user.getId() + " | " + user.getName());

				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);

				Role userRole = user.getRole();
				logger.debug("userRole = " + userRole.getName());
				if (userRole.isUser()) {
					page = ConfigurationManager.getProperty("path.page.bicycles");
					loadBicyclesPage(request);
				} else {
					page = ConfigurationManager.getProperty("path.page.main_admin");
				}
			}
		} catch (CommandException e) {
			request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}

		return page;

	}

}
