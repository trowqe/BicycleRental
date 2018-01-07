package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.service.EntityService;
import by.epam.bicycle.service.ServiceException;

public class BicycleTypeService implements EntityService<Long, BicycleType> {
	
	public List<BicycleType> findAll() throws ServiceException {
		List<BicycleType> bicycleTypes = new ArrayList<BicycleType>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleTypeDAO dao = new BicycleTypeDAO(connection);
		try {
			bicycleTypes = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycleTypes", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return bicycleTypes;
	}

	public BicycleType findEntityById(Long id) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void delete(BicycleType entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void create(BicycleType entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void updateById(Long id, BicycleType entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void delete(Long id) throws ServiceException {
		throw new UnsupportedOperationException();	
		
	}
}
