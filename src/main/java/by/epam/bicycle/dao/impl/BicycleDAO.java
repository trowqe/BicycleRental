package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.Bicycle;

public class BicycleDAO extends AbstractDAO<Long, Bicycle> {
	public static final String SQL_SELECT_ALL_BICYCLES = "select b.*, m.*, r.*,  t.* from bicycles b "
			+ "left join bicycle_models m on b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id";

	public static final String SQL_SELECT_BICYCLE_BY_FILTER = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE "
			+ "b.id NOT IN (SELECT bicycle_id FROM rents WHERE datetime_finish is null) ";
	
	public static final String SQL_SELECT_BICYCLE_BY_ID = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE "
			+ "b.id = ?";

	/**
	 * {@inheritDoc}
	 * 
	 */
	public BicycleDAO() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public BicycleDAO(Connection connection) {
		super(connection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DAOException
	 *             if will be catch SQLException
	 * 
	 */
	public List<Bicycle> findAll() throws DAOException {
		List<Bicycle> bicycles = new ArrayList<Bicycle>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = wrappedConnection.getStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_BICYCLES);

			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<Bicycle> creator = creatorDirector.getCreator(Bicycle.class);

			while (resultSet.next()) {
				Bicycle bicycle = (Bicycle) creator.execute(resultSet);
				bicycles.add(bicycle);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get all bicycles", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);
		}

		return bicycles;
	}

	public Bicycle findEntityById(Long id) throws DAOException {
		Bicycle bicycle = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = wrappedConnection.getPreparedStatement(SQL_SELECT_BICYCLE_BY_ID, id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) { 
				EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
				EntityCreator<Bicycle> creator = creatorDirector.getCreator(Bicycle.class);
				bicycle = (Bicycle) creator.execute(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get bicycle", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);		
		}
		
		return bicycle;
	}

	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void create(Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public List<Bicycle> findActiveBicycleByFilter(long rentalPointId, long bicycleTypeId, String firm, String model)
			throws DAOException {
		List<Bicycle> bicycles = new ArrayList<Bicycle>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String sql = SQL_SELECT_BICYCLE_BY_FILTER;
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
				sql += " and m.firm like ? ";
				params.add(firm);
			}

			if (!model.equals("")) {
				sql += " and m.model like ? ";
				params.add(model);
			}

			Object[] arrParams = params.toArray();

			statement = wrappedConnection.getPreparedStatement(sql, arrParams);
			resultSet = statement.executeQuery();

			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<Bicycle> creator = creatorDirector.getCreator(Bicycle.class);

			while (resultSet.next()) {
				Bicycle bicycle = (Bicycle) creator.execute(resultSet);
				bicycles.add(bicycle);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get bicycles", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);
		}

		return bicycles;
	}

}
