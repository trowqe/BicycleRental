package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleModel;

public class BicycleModelDAO extends AbstractDAO<Long, BicycleModel> {
	public BicycleModelDAO() {
		super(BicycleModel.class, BicycleModel.TABLE_NAME);
	}
	
	public BicycleModelDAO(Connection connection) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, connection);
	}
	
	public void create(BicycleModel entity) throws DAOException {		
		throw new UnsupportedOperationException();		
	}
	
	public void updateById(Long id, BicycleModel entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
}
