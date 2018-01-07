package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.service.EntityService;
import by.epam.bicycle.service.ServiceException;

public class BicycleService implements EntityService<Long, Bicycle> {
	
	public List<Bicycle> findAll() throws ServiceException {
		List<Bicycle> bicycles = new ArrayList<Bicycle>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			 bicycles = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycle", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return bicycles;
	}

	public Bicycle findEntityById(Long id) throws ServiceException {
		Bicycle bicycle = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			 bicycle = dao.findEntityById(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycle", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return bicycle;
	}

	public void delete(Long id) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			 dao.delete(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycle", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}		
	}

	public void delete(Bicycle entity) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			 dao.delete(entity);
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycle", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}			
	}

	public void create(Bicycle entity) throws ServiceException {
		throw new UnsupportedOperationException();		
	}

	public void updateById(Long id, Bicycle entity) throws ServiceException {
		throw new UnsupportedOperationException();		
	}
	
	public List<Bicycle> getActiveBicyclesByFilter(long rentalPointId, long bicycleTypeId, String firm, String model) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection);
		try {
			List<Bicycle> bicycles = dao.findActiveBicycleByFilter(rentalPointId, bicycleTypeId, firm, model);
			return bicycles;
		} catch (DAOException e) {
			throw new ServiceException("Cannot get bicycles", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
}
