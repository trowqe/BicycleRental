package by.epam.bicycle.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.dao.pool.ConnectionPool;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.utils.HashUtils;

public class UserService extends AbstractService<User>{
	
	public UserService() {
		super(User.class);
	}
	
	public UserService(String language) {
		super(User.class, language);
	}

	public User getUserByLoginAndPassword(String login, String password) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		User user = null;
		try {
			password = HashUtils.getHashMD5(password);
			user = dao.findUserByLoginAndPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return user;
	}
	
	public boolean isLoginUnique(String login) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		boolean result = false;
		try {
			result = dao.isLoginUnique(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return result;
	}
	
	public void updateBalance(long id, BigDecimal balance) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		try {
			dao.updateBalance(id, balance);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
	public void updateStatus(long id, short status) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		try {
			dao.updateStatus(id, status);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
	public List<User> findAllUsers() throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		List<User> users = null;
		try {
			users = dao.findAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return users;
	}
}
