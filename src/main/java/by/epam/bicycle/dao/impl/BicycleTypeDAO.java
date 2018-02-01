package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleType;

/**
 * 
 * Class that provides methods for BicycleType entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class BicycleTypeDAO extends AbstractDAO<BicycleType> {
	/** 
	 * Creates BicycleTypeDAO with default entity class type and name of table.
	 * */
	public BicycleTypeDAO() {
		super(BicycleType.class, BicycleType.TABLE_NAME);
	}
	
	/** 
	 * Creates BicycleTypeDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public BicycleTypeDAO(String language) {
		super(BicycleType.class, BicycleType.TABLE_NAME, language);
	}
	
	/** 
	 * Creates BicycleTypeDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public BicycleTypeDAO(Connection connection, String language) {
		super(BicycleType.class, BicycleType.TABLE_NAME, connection, language);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	} 

}
