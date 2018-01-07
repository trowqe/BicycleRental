package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.Tariff;

public class TariffDAO extends AbstractDAO<Long, Tariff>  {
	public static final String SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID = "select t.*, bt.* from tariffs t " +
			"left join bicycle_types bt on t.bicycle_type_id = bt.id " +
			"where t.bicycle_type_id = ?";
	/**
     * {@inheritDoc}
     * 
    */
	public TariffDAO() {
		super();
	}
	
	/**
     * {@inheritDoc}
     * 
    */
	public TariffDAO(Connection connection) {
		super(connection);
	}

	/**
     * {@inheritDoc}
     * 
     * @throws DAOException if will be catch SQLException 
     * 
    */
	public List<Tariff> findAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	public Tariff findEntityById(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void delete(Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void create(Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void updateById(Long id, Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
	
	public List<Tariff> findTariffsByBicycleTypeId(long bicycleTypeId) throws DAOException {
		List<Tariff> tariffs = new ArrayList<Tariff>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = wrappedConnection.getPreparedStatement(SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID, bicycleTypeId);
			resultSet = statement.executeQuery();

			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<Tariff> creator = creatorDirector.getCreator(Tariff.class);

			while (resultSet.next()) {
				Tariff tariff = (Tariff) creator.execute(resultSet);
				tariffs.add(tariff);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get tariffs", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);
		}

		return tariffs;
	}

}
