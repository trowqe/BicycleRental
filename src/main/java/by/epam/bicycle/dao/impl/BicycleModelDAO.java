package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleModel;

/**
 * 
 * Class that provides methods for BicycleModel entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class BicycleModelDAO extends AbstractDAO<BicycleModel> {
	/**
	  * SQL query that select whole info about all bicycle models.
	  */
	private final static String SELECT_ALL_MODELS = "select m.*, t.* from bicycle_models m left join bicycle_types t on " 
			+ " m.bicycle_type_id = t.id";
	
	/** 
	 * Creates BicycleModelDAO with default entity class type and name of table.
	 * */
	public BicycleModelDAO() {
		super(BicycleModel.class, BicycleModel.TABLE_NAME);
	}
	
	/** 
	 * Creates BicycleModelDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public BicycleModelDAO(String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, language);
	}
	
	/** 
	 * Creates BicycleModelDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public BicycleModelDAO(Connection connection, String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public List<BicycleModel> findAll() throws DAOException  {
		return findListOfEntities(SELECT_ALL_MODELS);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(BicycleModel entity) throws DAOException {		
		throw new UnsupportedOperationException();		
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, BicycleModel entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
}
