package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleModel;

public class BicycleModelDAO extends AbstractDAO<BicycleModel> {
	
	public BicycleModelDAO() {
		super(BicycleModel.class, BicycleModel.TABLE_NAME);
	}
	
	public BicycleModelDAO(String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, language);
	}
	
	public BicycleModelDAO(Connection connection, String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, connection, language);
	}
	
	public void create(BicycleModel entity) throws DAOException {		
		throw new UnsupportedOperationException();		
	}
	
	public void updateById(long id, BicycleModel entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
}
