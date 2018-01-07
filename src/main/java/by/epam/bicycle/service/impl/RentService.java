package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.RentDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.EntityService;
import by.epam.bicycle.service.ServiceException;

public class RentService implements EntityService<Long, Rent>{
	
	public List<Rent> findAll() throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public Rent findEntityById(Long id) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void delete(Long id) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void delete(Rent entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void create(Rent entity) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void updateById(Long id, Rent entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void createNewRent(long userId, long bicycleId, long tariffId) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		RentDAO dao = new RentDAO(connection);
		
		User user = new User(userId);
		Bicycle bicycle = new Bicycle(bicycleId);
		Tariff tariff = new Tariff(tariffId);
		
		Rent rent = new Rent(user, bicycle, tariff);
		
		try {
			dao.create(rent);
		} catch (DAOException e) {
			throw new ServiceException("Cannot create rent", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

}
