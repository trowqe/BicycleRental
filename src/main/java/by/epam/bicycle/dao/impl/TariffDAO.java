package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Tariff;

public class TariffDAO extends AbstractDAO<Tariff>  {
	public static final String SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID = "select t.*, bt.* from tariffs t " +
			"left join bicycle_types bt on t.bicycle_type_id = bt.id " +
			"where t.bicycle_type_id = ?";

	public TariffDAO() {
		super(Tariff.class, Tariff.TABLE_NAME);
	}
	
	public TariffDAO(String language) {
		super(Tariff.class, Tariff.TABLE_NAME, language);
	}
	
	public TariffDAO(Connection connection, String language) {
		super(Tariff.class, Tariff.TABLE_NAME, connection, language);
	}

	public void create(Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}

	public void updateById(long id, Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
	
	public List<Tariff> findTariffsByBicycleTypeId(long bicycleTypeId) throws DAOException {
		return findListOfEntities(SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID, bicycleTypeId);
	}

}
