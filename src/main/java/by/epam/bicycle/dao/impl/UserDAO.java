package by.epam.bicycle.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import by.epam.bicycle.config.ConfigurationManager;
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
	private static final String SQL_SELECT_ALL_USERS = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.role_id = 1";
	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ? and u.password = ?";
	private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ?";
	private static final String SQL_INSERT_NEW_USER = "INSERT INTO users(name, surname, patronymic, mobile_phone,"
			+ "email, login, password, status, role_id, create_datetime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_USER_SET_BALANCE = "UPDATE users SET balance=? WHERE id=?";
	private static final String SQL_UPDATE_USER_SET_STATUS = "UPDATE users SET status=? WHERE id=?";

	public UserDAO() {
		super(User.class, User.TABLE_NAME);
	}

	public UserDAO(String language) {
		super(User.class, User.TABLE_NAME, language);
	}

	public UserDAO(Connection connection, String language) {
		super(User.class, User.TABLE_NAME, connection, language);
	}

	public void create(User user) throws DAOException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String name = user.getName();
			String surname = user.getSurname();
			String patronymic = user.getPatronymic();
			String mobilePhone = user.getMobilePhone();
			String email = user.getEmail();
			String login = user.getLogin();
			String password = user.getPassword();
			short status = Short.parseShort(ConfigurationManager.getProperty(ConfigurationManager.USER_STATUS_ACTIVE));
			long roleId = user.getRole().getId();
			
			statement = getWrappedConnection().getPreparedStatement(SQL_INSERT_NEW_USER, name, surname, patronymic,
					mobilePhone, email, login, password, status, roleId, new Date());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}
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
	
	public boolean isLoginUnique(String login) throws DAOException {
		User user = findSingleEntitie(SQL_SELECT_USER_BY_LOGIN, login);
		return (user == null);		
	}
	
	public void updateBalance(long id, BigDecimal balance) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_USER_SET_BALANCE, balance, id);
	}
	
	public void updateStatus(long id, short status) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_USER_SET_STATUS, status, id);
	}
	
	public List<User> findAllUsers() throws DAOException {
		return findListOfEntities(SQL_SELECT_ALL_USERS);
	}
	
}
