package by.bokshic.bicycle.dao.impl;

import java.sql.Connection;

import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.RentalPoint;

/**
 * 
 * Class that provides methods for RentalPoint entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class RentalPointDAO extends AbstractDAO<RentalPoint> {
	/** 
	 * Creates RentalPointDAO with default entity class type and name of table.
	 * */
	public RentalPointDAO() {
		super(RentalPoint.class, RentalPoint.TABLE_NAME);
	}
	
	/** 
	 * Creates RentalPointDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public RentalPointDAO(String language) {
		super(RentalPoint.class, RentalPoint.TABLE_NAME, language);
	}
	
	/** 
	 * Creates RentalPointDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public RentalPointDAO(Connection connection, String language) {
		super(RentalPoint.class, RentalPoint.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
