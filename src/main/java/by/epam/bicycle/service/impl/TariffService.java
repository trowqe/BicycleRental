package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.TariffDAO;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.AbstractService;
import by.epam.bicycle.service.ServiceException;

public class TariffService extends AbstractService<Tariff> {
	
	public TariffService() {
		super(Tariff.class);
	}
	
	public TariffService(String language) {
		super(Tariff.class, language);
	}
	
	public List<Tariff> getTarriffListByBicycleTypeId(long bicycleTypeId) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String language = getLanguage();
		TariffDAO dao = new TariffDAO(connection, language);
		try {
			List<Tariff> tariffs = dao.findTariffsByBicycleTypeId(bicycleTypeId);
			return tariffs;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}	
}
