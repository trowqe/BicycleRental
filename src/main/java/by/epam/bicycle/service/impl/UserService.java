package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.controller.command.LoginCommand;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.EntityService;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.utils.HashUtils;

public class UserService implements EntityService<Long, User>{
	private static Logger logger = LogManager.getLogger(LoginCommand.class);
	
	public User getUserByLoginAndPassword(String login, String password) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		UserDAO dao = new UserDAO(connection);
		try {
			logger.debug("password = " + password);
			password = HashUtils.getHashMD5(password);
			logger.debug("password = " + password);
			User user = dao.findUserByLoginAndPassword(login, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Cannot get user", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

	public List<User> findAll() throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public User findEntityById(Long id) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void delete(Long id) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void delete(User entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void create(User entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void updateById(Long id, User entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}
}
