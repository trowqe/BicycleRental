package by.bokshic.bicycle.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.entity.Rent;

/**
 * 
 * Class that provides methods for Rent entity create queries to the database.
 *  
 * @author khatkovskaya
 * 
 */
public class RentDAO extends AbstractDAO<Rent> {
	/**
	  * SQL query that insert new rent.
	  */
	private static final String SQL_INSERT_NEW_RENT = "INSERT INTO RENTS(datetime_create, user_id, bicycle_id, tariff_id) VALUES(?, ?, ?, ?)";
	
	/**
	  * SQL query that select whole info about rents for specified user id.
	  */
	private static final String SQL_SELECT_RENTS_BY_USER_ID = "SELECT r.*, m.*, rp.*, t.*, b.*, bt.* FROM rents as r " 
			+ " LEFT JOIN bicycles as b ON r.bicycle_id = b.id LEFT JOIN bicycle_models as m ON b.bicycle_model_id = m.id "
			+ " LEFT JOIN rental_points as rp ON b.rental_point_id = rp.id"
			+ " LEFT JOIN bicycle_types as bt ON m.bicycle_type_id = bt.id"
			+ " LEFT JOIN tariffs as t ON r.tariff_id = t.id"
			+ " WHERE r.user_id = ? "
			+ " ORDER BY r.datetime_create desc";
	
	/**
	  * SQL query that select whole info about rent for specified rent id.
	  */
	private static final String SQL_SELECT_RENT_BY_ID = "SELECT r.*, m.*, rp.*, t.*, b.*, bt.* FROM rents as r " 
			+ " LEFT JOIN bicycles as b ON r.bicycle_id = b.id LEFT JOIN bicycle_models as m ON b.bicycle_model_id = m.id "
			+ " LEFT JOIN rental_points as rp ON b.rental_point_id = rp.id"
			+ " LEFT JOIN bicycle_types as bt ON m.bicycle_type_id = bt.id"
			+ " LEFT JOIN tariffs as t ON r.tariff_id = t.id"
			+ " WHERE r.id = ? ";
	
	/**
	  * SQL query that update datetime_finish and amount of rent.
	  */
	private static final String SQL_UPDATE_RENT_BY_ID = "UPDATE rents SET datetime_finish=?, amount=? WHERE id=?";
	
	/** 
	 * Creates RentDAO with default entity class type and name of table.
	 * */
	public RentDAO() {
		super(Rent.class, Rent.TABLE_NAME);
	}
	
	/** 
	 * Creates RentDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public RentDAO(String language) {
		super(Rent.class, Rent.TABLE_NAME, language);
	}
	
	/** 
	 * Creates RentDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public RentDAO(Connection connection, String language) {
		super(Rent.class, Rent.TABLE_NAME, connection, language);
	}
	
	/** 
	 * {@inheritDoc}
	 * 
	 * */
	public void create(Rent entity) throws DAOException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			long userId = entity.getUser().getId();
			long bicycleId = entity.getBicycle().getId();
			long tariffId = entity.getTariff().getId();
			statement = getWrappedConnection().getPreparedStatement(SQL_INSERT_NEW_RENT, new Date(), userId, bicycleId,
					tariffId);
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
	 * */
	public Rent findEntityById(long id) throws DAOException {
		return findSingleEntitie(SQL_SELECT_RENT_BY_ID, id);
	}
	
	/** 
	 * {@inheritDoc}
	 * */
	public void updateById(long id, Rent entity) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_RENT_BY_ID, entity.getFinishDateTime(), entity.getAmount(), id);
	}
	
	/** 
	 * Finds rents of specified user.
	 * 
	 * @param id the id of the user
	 * @return list of rents
	 * */
	public List<Rent> getRentsByUserId(long userId) throws DAOException {
		return findListOfEntities(SQL_SELECT_RENTS_BY_USER_ID, userId);
	}

}
