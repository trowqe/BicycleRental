package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.RentalPoint;

public class RentalPointDAO extends AbstractDAO<Long, RentalPoint> {
	public static final String SQL_SELECT_ALL_RENTAL_POINTS = "SELECT id as rental_point_id,"
			+ " address, phone, working_hours"
			+ " FROM rental_points";

	/**
	 * {@inheritDoc}
	 * 
	 */
	public RentalPointDAO() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public RentalPointDAO(Connection connection) {
		super(connection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DAOException
	 *             if will be catch SQLException
	 * 
	 */
	public List<RentalPoint> findAll() throws DAOException {
		List<RentalPoint> rentalPoints = new ArrayList<RentalPoint>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = wrappedConnection.getStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_RENTAL_POINTS);

			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<RentalPoint> creator = creatorDirector.getCreator(RentalPoint.class);

			while (resultSet.next()) {
				RentalPoint rentalPoint = (RentalPoint) creator.execute(resultSet);
				rentalPoints.add(rentalPoint);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get all rental points", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);
		}

		return rentalPoints;
	}

	public RentalPoint findEntityById(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void create(RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
