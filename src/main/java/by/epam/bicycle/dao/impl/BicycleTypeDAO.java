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
import by.epam.bicycle.entity.BicycleType;

public class BicycleTypeDAO extends AbstractDAO<Long, BicycleType> {
	public static final String SQL_SELECT_ALL_RENTAL_POINTS = "SELECT id as bicycle_type_id, name FROM bicycle_types";
	
	/**
     * {@inheritDoc}
     * 
    */
	public BicycleTypeDAO() {
		super();
	}
	
	/**
     * {@inheritDoc}
     * 
    */
	public BicycleTypeDAO(Connection connection) {
		super(connection);
	}

	/**
     * {@inheritDoc}
     * 
     * @throws DAOException if will be catch SQLException 
     * 
    */
	public List<BicycleType> findAll() throws DAOException {
		List<BicycleType> bicycleTypes = new ArrayList<BicycleType>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = wrappedConnection.getStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_RENTAL_POINTS);
	
			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<BicycleType> creator = creatorDirector.getCreator(BicycleType.class);
			
			while (resultSet.next()) {
				BicycleType bicycleType = (BicycleType) creator.execute(resultSet);
				bicycleTypes.add(bicycleType);
			}		
		} catch (SQLException e) {
			throw new DAOException("Cannot get all bicycle types", e);
		} finally {
			wrappedConnection.closeResultSet(resultSet);
			wrappedConnection.closeStatement(statement);		
		}

		return bicycleTypes;
	}

	public BicycleType findEntityById(Long id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void delete(BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void create(BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	} 

}
