package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class BicycleTypeService extends AbstractService<BicycleType> {
	
	public BicycleTypeService() {
		super(BicycleType.class);
	}

	public List<BicycleType> findAll() throws ServiceException {
		List<BicycleType> bicycleTypes = new ArrayList<BicycleType>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleTypeDAO dao = new BicycleTypeDAO(connection);
		try {
			bicycleTypes = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return bicycleTypes;
	}
}
