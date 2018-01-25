package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Rent;

public class RentDAO extends AbstractDAO<Rent> {
	private static final String SQL_INSERT_NEW_RENT = "INSERT INTO RENTS(datetime_create, user_id, bicycle_id, tariff_id) VALUES(?, ?, ?, ?)";

	public RentDAO() {
		super(Rent.class, Rent.TABLE_NAME);
	}

	public RentDAO(Connection connection) {
		super(Rent.class, Rent.TABLE_NAME, connection);
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
			throw new DAOException("Cannot insert rent", e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}
	}

	public void updateById(long id, Rent entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
