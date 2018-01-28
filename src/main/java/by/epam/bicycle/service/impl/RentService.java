package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.RentDAO;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class RentService extends AbstractService<Rent>{
	
	public RentService() {
		super(Rent.class);
	}
	
	public RentService(String language) {
		super(Rent.class, language);
	}
	
	public List<Rent> getRentsByUserId(long userId) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		RentDAO dao = new RentDAO(connection, language);
		List<Rent> rents = null;
		try {
			 rents = dao.getRentsByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return rents;
	}
}
