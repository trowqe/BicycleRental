package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Rent;

public class RentDAO extends AbstractDAO<Rent> {
	private static final String SQL_INSERT_NEW_RENT = "INSERT INTO RENTS(datetime_create, user_id, bicycle_id, tariff_id) VALUES(?, ?, ?, ?)";
	private static final String SQL_SELECT_RENTS_BY_USER_ID = "SELECT r.*, m.*, rp.*, t.*, b.*, bt.* FROM rents as r " 
			+ " LEFT JOIN bicycles as b ON r.bicycle_id = b.id LEFT JOIN bicycle_models as m ON b.bicycle_model_id = m.id "
			+ " LEFT JOIN rental_points as rp ON b.rental_point_id = rp.id"
			+ " LEFT JOIN bicycle_types as bt ON m.bicycle_type_id = bt.id"
			+ " LEFT JOIN tariffs as t ON r.tariff_id = t.id"
			+ " WHERE r.user_id = ? "
			+ " ORDER BY r.datetime_create desc";
	private static final String SQL_SELECT_RENT_BY_ID = "SELECT r.*, m.*, rp.*, t.*, b.*, bt.* FROM rents as r " 
			+ " LEFT JOIN bicycles as b ON r.bicycle_id = b.id LEFT JOIN bicycle_models as m ON b.bicycle_model_id = m.id "
			+ " LEFT JOIN rental_points as rp ON b.rental_point_id = rp.id"
			+ " LEFT JOIN bicycle_types as bt ON m.bicycle_type_id = bt.id"
			+ " LEFT JOIN tariffs as t ON r.tariff_id = t.id"
			+ " WHERE r.id = ? ";
	private static final String SQL_UPDATE_RENT_BY_ID = "UPDATE rents SET datetime_finish=?, amount=? WHERE id=?";

	public RentDAO() {
		super(Rent.class, Rent.TABLE_NAME);
	}

	public RentDAO(String language) {
		super(Rent.class, Rent.TABLE_NAME, language);
	}

	public RentDAO(Connection connection, String language) {
		super(Rent.class, Rent.TABLE_NAME, connection, language);
	}

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

	public void updateById(long id, Rent entity) throws DAOException {
		executeUpdateEntitie(SQL_UPDATE_RENT_BY_ID, entity.getFinishDateTime(), entity.getAmount(), id);
	}
	
	public Rent findEntityById(long id) throws DAOException {
		return findSingleEntitie(SQL_SELECT_RENT_BY_ID, id);
		
	}

	public List<Rent> getRentsByUserId(long userId) throws DAOException {
		return findListOfEntities(SQL_SELECT_RENTS_BY_USER_ID, userId);
	}

}
