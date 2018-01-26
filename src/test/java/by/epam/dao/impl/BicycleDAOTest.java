package by.epam.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.entity.Bicycle;

public class BicycleDAOTest {
	private static Logger logger = LogManager.getLogger(BicycleDAOTest.class);
	
	@Test 
	public void test() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		BicycleDAO dao = new BicycleDAO(connection, "ru");
		try {
			List<Bicycle> list  = dao.findActiveBicycleByFilter(2L, 1L, "", "");
			for (Bicycle b : list) {
				logger.debug(b);	
			}
			
		}
		catch (DAOException e) {
			logger.debug(e);
			logger.debug(e.getCause());
		}
		finally {
			pool.returnConnectionToPool(connection);
		}
	}
}	
