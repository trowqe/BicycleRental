package by.epam.bicycle.service.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.TariffDAO;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.service.EntityService;
import by.epam.bicycle.service.ServiceException;

public class TariffService implements EntityService<Long, Tariff> {
	public List<Tariff> getTarriffListByBicycleTypeId(long bicycleTypeId) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		TariffDAO dao = new TariffDAO(connection);
		try {
			List<Tariff> tariffs = dao.findTariffsByBicycleTypeId(bicycleTypeId);
			return tariffs;
		} catch (DAOException e) {
			throw new ServiceException("Cannot get tariffs", e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
	}

	public List<Tariff> findAll() throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public Tariff findEntityById(Long id) throws ServiceException {
		throw new UnsupportedOperationException();	
	}

	public void delete(Long id) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void delete(Tariff entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void create(Tariff entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}

	public void updateById(Long id, Tariff entity) throws ServiceException {
		throw new UnsupportedOperationException();			
	}
	
}
