package by.epam.bicycle.service.impl;

import java.sql.Connection;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.RentDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class RentService extends AbstractService<Rent>{
	
	public RentService() {
		super(Rent.class);
	}
	
	public RentService(String language) {
		super(Rent.class, language);
	}

	public void createNewRent(long userId, long bicycleId, long tariffId) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		RentDAO dao = new RentDAO(connection, language);
		
		User user = new User(userId);
		Bicycle bicycle = new Bicycle(bicycleId);
		Tariff tariff = new Tariff(tariffId);
		
		Rent rent = new Rent(user, bicycle, tariff);
		
		try {
			dao.create(rent);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

}
