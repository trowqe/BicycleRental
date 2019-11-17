package by.bokshic.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.dao.impl.RentDAO;
import by.bokshic.bicycle.dao.pool.ConnectionPool;
import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.service.ServiceException;

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
