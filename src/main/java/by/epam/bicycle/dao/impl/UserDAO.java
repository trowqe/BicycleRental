package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.User;

/**
 * 
 * Class that extends AbstractDAO for User entity. 
 *  
 * @author khatkovskaya
 * 
 */
public class UserDAO extends AbstractDAO<Long, User> {
	public static final String SQL_SELECT_ALL_USERS = "SELECT u.*, r.name FROM users "
			+ "LEFT JOIN roles r ON u.role_id = r.id";
	public static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.*, r.name as rolename FROM users u "
			+ "LEFT JOIN roles r ON u.role_id = r.id WHERE u.login = ? and u.password = ?";
	
	/**
     * {@inheritDoc}
     * 
    */
	public UserDAO() {
		super();
	}
	
	/**
     * {@inheritDoc}
     * 
    */
	public UserDAO(Connection connection) {
		super(connection);
	}

	/**
     * {@inheritDoc}
     * 
     * @throws DAOException if will be catch SQLException 
     * 
    */
	public List<User> findAll() throws DAOException {
		List<User> users = new ArrayList<User>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getWrappedConnection().getStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
	
			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<User> creator = creatorDirector.getCreator(User.class);
			
			while (resultSet.next()) {
				User user = (User) creator.execute(resultSet);
				users.add(user);
			}		
		} catch (SQLException e) {
			throw new DAOException("Cannot get all users", e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);		
		}

		return users;
	}	

	/**
     * {@inheritDoc}
     * 
     * This implementation always throws an {@code UnsupportedOperationException}.
     * 
    */
	public User findEntityById(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
     * {@inheritDoc}
     * 
     * This implementation always throws an {@code UnsupportedOperationException}.
     * 
    */
	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	/**
     * {@inheritDoc}
     * 
     * This implementation always throws an {@code UnsupportedOperationException}.
     * 
    */
	public void delete(User entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	/**
     * {@inheritDoc}
     * 
     * This implementation always throws an {@code UnsupportedOperationException}.
     * 
    */
	public void create(User entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
     * {@inheritDoc}
     * 
     * This implementation always throws an {@code UnsupportedOperationException}.
     * 
    */
	public void updateById(Long id, User entity) throws DAOException {
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
		User user = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = getWrappedConnection().getPreparedStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD, login, password);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) { 
				EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
				EntityCreator<User> creator = creatorDirector.getCreator(User.class);
				user = (User) creator.execute(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get user", e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);		
		}
		
		return user;
	}

}
