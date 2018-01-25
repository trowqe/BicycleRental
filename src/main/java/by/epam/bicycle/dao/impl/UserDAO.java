package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.User;

/**
 * 
 * Class that extends AbstractDAO for User entity. 
 *  
 * @author khatkovskaya
 * 
 */
public class UserDAO extends AbstractDAO<User> {
	public static final String SQL_SELECT_ALL_USERS = "SELECT u.*, r.name FROM users "
			+ "LEFT JOIN roles r ON u.role_id = r.id";
	public static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ? and u.password = ?";
	
	public UserDAO() {
		super(User.class, User.TABLE_NAME);
	}
	
	public UserDAO(Connection connection) {
		super(User.class, User.TABLE_NAME, connection);
	}


	public void create(User entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(long id, User entity) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	/**
     * Finds user by it's login and password.
     * 
     * @param login 
     * @param password
     * @return founded entity
     * @throws DAOException 
     */
	public User findUserByLoginAndPassword(String login, String password) throws DAOException {
		return findSingleEntitie(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD, login, password);
	}

}
