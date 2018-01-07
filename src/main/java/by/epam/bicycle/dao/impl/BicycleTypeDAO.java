package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleType;

public class BicycleTypeDAO extends AbstractDAO<Long, BicycleType> {

	public BicycleTypeDAO() {
		super(BicycleType.class, BicycleType.TABLE_NAME);
	}
	
	public BicycleTypeDAO(Connection connection) {
		super(BicycleType.class, BicycleType.TABLE_NAME, connection);
	}

	public void create(BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, BicycleType entity) throws DAOException {
		throw new UnsupportedOperationException();
	} 

}
