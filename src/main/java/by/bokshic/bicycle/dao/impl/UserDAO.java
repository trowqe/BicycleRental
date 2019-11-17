package by.bokshic.bicycle.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import by.bokshic.bicycle.config.ConfigurationManager;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.config.ConfigurationManager;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.User;

/**
 * 
 * Class that extends AbstractDAO for User entity.
 * 
 * @author khatkovskaya
 * 
 */
public class UserDAO extends AbstractDAO<User> {
	/**
	  * SQL query that select whole info about users.
	  */
	private static final String SQL_SELECT_ALL_USERS = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.role_id = 1";

	/**
	  * SQL query that select info about user by login and password.
	  */
	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ? and u.password = ?";
	
	/**
	  * SQL query that select user by login.
	  */
	private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ?";
	
	/**
	  * SQL query that insert new user.
	  */
	private static final String SQL_INSERT_NEW_USER = "INSERT INTO users(name, surname, patronymic, mobile_phone,"
			+ "email, login, password, status, role_id, create_datetime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	/**
	  * SQL query that update balance of user.
	  */
	private static final String SQL_UPDATE_USER_SET_BALANCE = "UPDATE users SET balance=? WHERE id=?";
	
	/**
	  * SQL query that update status of user.
	  */
	private static final String SQL_UPDATE_USER_SET_STATUS = "UPDATE users SET status=? WHERE id=?";
	
	/** 
	 * Creates UserDAO with default entity class type and name of table.
	 * */
	public UserDAO() {
		super(User.class, User.TABLE_NAME);
	}
	
	/** 
	 * Creates UserDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public UserDAO(String language) {
		super(User.class, User.TABLE_NAME, language);
	}
	
	/** 
	 * Creates UserDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public UserDAO(Connection connection, String language) {
		super(User.class, User.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * */
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
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
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
	

	/**
	 * Checks that login is unique. 
	 * 
	 * @param login
	 * @return true if users with specified login not found
	 * @throws DAOException
	 */
	public boolean isLoginUnique(String login) throws DAOException {
		User user = findSingleEntitie(SQL_SELECT_USER_BY_LOGIN, login);
		return (user == null);		
	}
	
	/**
	 * Updates the balance of user.
	 * 
	 * @param id 
	 * @return balance 
	 * @throws DAOException
	 */
	public void updateBalance(long id, BigDecimal balance) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_USER_SET_BALANCE, balance, id);
	}
	
	/**
	 * Updates the status of user.
	 * 
	 * @param id 
	 * @return status 
	 * @throws DAOException
	 */
	public void updateStatus(long id, short status) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_USER_SET_STATUS, status, id);
	}
	
	/**
	 * Find all users.
	 * 
	 * @throws DAOException
	 */
	public List<User> findAllUsers() throws DAOException {
		return findListOfEntities(SQL_SELECT_ALL_USERS);
	}
	
}
