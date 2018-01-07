package by.epam.bicycle.dao;

import java.sql.Connection;

import by.epam.bicycle.entity.Entity;


public abstract class AbstractDAO<K, T extends Entity> implements EntityDAO<K, T> {
	
	/**
	 * Connection to the database in specific wrapper.
	 */
	protected WrapperConnection wrappedConnection;
	
	/**
	 * Default constructor.
	 */
	public AbstractDAO() {
	}
	
	/**
	 * Sets connection to the database.
	 * 
	 * @param connection concrete connection to the database.
	 */
	public AbstractDAO(Connection connection) {
		wrappedConnection = new WrapperConnection(connection);
	}
	
}
