package by.epam.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.TariffDAO;
import by.epam.bicycle.entity.Tariff;

public class TariffDAOTest {
	private static Logger logger = LogManager.getLogger(TariffDAOTest.class);
	
	@Test 
	public void test() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		TariffDAO dao = new TariffDAO(connection, "ru");
		try {
			List<Tariff> list  = dao.findTariffsByBicycleTypeId(1l);
			for (Tariff b : list) {
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
