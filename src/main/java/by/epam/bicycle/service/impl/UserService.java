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

	public User getUserByLoginAndPassword(String login, String password) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		UserDAO dao = new UserDAO(connection);
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
}
