package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.RentalPointDAO;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class RentalPointService extends AbstractService<RentalPoint>{
	
	public RentalPointService() {
		super(RentalPoint.class);
	}

	public List<RentalPoint> findAll() throws ServiceException {
		List<RentalPoint> rentalPoints = new ArrayList<RentalPoint>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		RentalPointDAO dao = new RentalPointDAO(connection);
		try {
			rentalPoints = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return rentalPoints;
	}
}
