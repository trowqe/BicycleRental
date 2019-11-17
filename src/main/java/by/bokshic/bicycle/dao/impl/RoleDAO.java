package by.bokshic.bicycle.dao.impl;

import java.sql.Connection;

import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.Role;

/**
 * 
 * Class that provides methods for Role entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class RoleDAO extends AbstractDAO<Role> {
	
	/** 
	 * Creates RoleDAO with default entity class type and name of table.
	 * */
	public RoleDAO() {
		super(Role.class, Role.TABLE_NAME);
	}
	
	/** 
	 * Creates RoleDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public RoleDAO(String language) {
		super(Role.class, Role.TABLE_NAME, language);
	}
	
	/** 
	 * Creates RoleDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public RoleDAO(Connection connection, String language) {
		super(Role.class, Role.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
