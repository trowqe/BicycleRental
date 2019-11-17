package by.bokshic.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.bokshic.bicycle.service.ServiceException;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.dao.impl.TariffDAO;
import by.bokshic.bicycle.dao.pool.ConnectionPool;
import by.bokshic.bicycle.entity.Tariff;

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
		List<Tariff> tariffs = null;
		try {
			tariffs = dao.findTariffsByBicycleTypeId(bicycleTypeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return tariffs;
	}	
}
