package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleType;

public class BicycleTypeDAO extends AbstractDAO<BicycleType> {

	public BicycleTypeDAO() {
		super(BicycleType.class, BicycleType.TABLE_NAME);
	}
	
	public BicycleTypeDAO(String language) {
		super(BicycleType.class, BicycleType.TABLE_NAME, language);
	}
	
	public BicycleTypeDAO(Connection connection, String language) {
		super(BicycleType.class, BicycleType.TABLE_NAME, connection, language);
	}

	public void create(BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(long id, BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	} 

}
