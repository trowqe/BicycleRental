package by.bokshic.bicycle.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.Bicycle;

/**
 * 
 * Class that provides methods for Bicycle entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class BicycleDAO extends AbstractDAO<Bicycle> {

	/**
	  * SQL query that select whole info about all bicycles.
	  */
	private static final String SQL_SELECT_ALL_BICYCLES = "select b.*, m.*, r.*,  t.* from bicycles b "
			+ "left join bicycle_models m on b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id order by b.id";
	
	/**
	  * SQL query that select whole info about all free bicycles.
	  */
	private static final String SQL_SELECT_FREE_BICYCLES = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE "
			+ "b.id NOT IN (SELECT bicycle_id FROM rents WHERE datetime_finish is null) ";
	
	/**
	  * SQL query that select whole info about bicycle filtered by id.
	  */
	private static final String SQL_SELECT_BICYCLE_BY_ID = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE b.id = ?";
	
	/**
	  * SQL query that insert new bicycle
	  */
	private static final String SQL_INSERT_BICYCLE = "INSERT INTO bicycles (rental_point_id, bicycle_model_id) " 
			+ " VALUES(?, ?) ";
	
	/**
	  * SQL query that update bicycle specified by id.
	  */
	private static final String SQL_UPDATE_BICYCLE = "UPDATE bicycles SET rental_point_id = ?, bicycle_model_id = ? " 
			+ " WHERE id = ? ";

	/** 
	 * Creates BicycleDAO with default entity class type and name of table.
	 * */
	public BicycleDAO() {
		super(Bicycle.class, Bicycle.TABLE_NAME);
	}
	
	/** 
	 * Creates BicycleDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public BicycleDAO(String language) {
		super(Bicycle.class, Bicycle.TABLE_NAME, language);
	}
	
	/** 
	 * Creates BicycleDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public BicycleDAO(Connection connection, String language) {
		super(Bicycle.class, Bicycle.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public List<Bicycle> findAll() throws DAOException {
		return findListOfEntities(SQL_SELECT_ALL_BICYCLES);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public Bicycle findEntityById(long id) throws DAOException {
		return findSingleEntitie(SQL_SELECT_BICYCLE_BY_ID, id);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	/** 
	 * Finds bicycles by specified parameters: id of rental point, id of bicycle type, 
	 * firm and model.
	 * 
	 * @param rentalPointId the id of rental point
	 * @param bicycleTypeId the id of bicycle type
	 * @param firm the name of bicycle firm
	 * @param model the name of bicycle model
	 * @return list of bicycles
	 * */
	public List<Bicycle> findActiveBicycleByFilter(long rentalPointId, long bicycleTypeId, String firm, String model)
			throws DAOException {

		String sql = SQL_SELECT_FREE_BICYCLES;
		List<Object> params = new ArrayList<Object>();

		if (rentalPointId != -1) {
			sql += " and b.rental_point_id = ? ";
			params.add(rentalPointId);
		}

		if (bicycleTypeId != -1) {
			sql += " and m.bicycle_type_id = ? ";
			params.add(bicycleTypeId);
		}

		if (!firm.equals("")) {
			sql += " and m.firm_" + getLanguage() + " like ? ";
			params.add(firm);
		}

		if (!model.equals("")) {
			sql += " and m.model_" + getLanguage() + " like ? ";
			params.add(model);
		}
		
		sql += " order by b.id "; 
		
		Object[] arrParams = params.toArray();

		return findListOfEntities(sql, arrParams);
	}
	
	/** 
	 * Creates new bicycle by specified parameters: id of rental point, id of bicycle model.
	 * 
	 * @param rentalPointId the id of rental point
	 * @param bicycleModelId the id of bicycle model
	 * */
	public void createByPointAndModelId(long rentalPointId, long bicycleModelId) throws DAOException {
		executeUpdateEntitie(SQL_INSERT_BICYCLE, rentalPointId, bicycleModelId);		
	}
	
	/** 
	 * Updates id of rental point and id of bicycle model of bicycle that specified by id.
	 * 
	 * @param id the id of bicycle
	 * @param rentalPointId the id of rental point
	 * @param bicycleModelId the id of bicycle model
	 * */
	public void updateByPointAndModelId(long id, long rentalPointId, long bicycleModelId) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_BICYCLE, rentalPointId, bicycleModelId, id);		
	}

}
