package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Rent;

public class RentDAO extends AbstractDAO<Long, Rent> {
	private static final String SQL_INSERT_NEW_RENT = "INSERT INTO RENTS(datetime_create, user_id, bicycle_id, tariff_id) VALUES(?, ?, ?, ?)";

	/**
	 * {@inheritDoc}
	 * 
	 */
	public RentDAO() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public RentDAO(Connection connection) {
		super(connection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DAOException
	 *             if will be catch SQLException
	 * 
	 */
	public List<Rent> findAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	public Rent findEntityById(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Rent entity) throws DAOException {
		throw new UnsupportedOperationException();
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

	public void updateById(Long id, Rent entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
