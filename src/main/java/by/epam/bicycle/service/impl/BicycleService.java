package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class BicycleService extends AbstractService<Bicycle> {

	public BicycleService() {
		super(Bicycle.class);
	}
	
	public BicycleService(String language) {
		super(Bicycle.class, language);
	}

	public List<Bicycle> getActiveBicyclesByFilter(long rentalPointId, long bicycleTypeId, String firm, String model) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		BicycleDAO dao = new BicycleDAO(connection, language);
		try {
			List<Bicycle> bicycles = dao.findActiveBicycleByFilter(rentalPointId, bicycleTypeId, firm, model);
			return bicycles;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}
}
