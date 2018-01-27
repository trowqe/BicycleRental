package by.epam.bicycle.service.impl;

import java.sql.Connection;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.AbstractService;
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
		try {
			password = HashUtils.getHashMD5(password);
			User user = dao.findUserByLoginAndPassword(login, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
	
	public boolean isLoginUnique(String login) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		UserDAO dao = new UserDAO(connection, language);
		try {
			return dao.isLoginUnique(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
}
