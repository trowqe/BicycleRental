package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.List;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Tariff;

public class TariffDAO extends AbstractDAO<Tariff>  {
	/**
	  * SQL query that select tariffs by bicycle type id.
	  */
	public static final String SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID = "select t.*, bt.* from tariffs t " +
			"left join bicycle_types bt on t.bicycle_type_id = bt.id " +
			"where t.bicycle_type_id = ?";
	
	/** 
	 * Creates TariffDAO with default entity class type and name of table.
	 * */
	public TariffDAO() {
		super(Tariff.class, Tariff.TABLE_NAME);
	}
	
	/** 
	 * Creates TariffDAO with specified language and default entity class type and name of table.
	 * @param language the language for select query
	 * */
	public TariffDAO(String language) {
		super(Tariff.class, Tariff.TABLE_NAME, language);
	}
	
	/** 
	 * Creates TariffDAO with specified language and connection to the database
	 * and default entity class type and name of table.
	 * 
	 * @param connection the connection to the database.
	 * @param language the language for select query
	 * */
	public TariffDAO(Connection connection, String language) {
		super(Tariff.class, Tariff.TABLE_NAME, connection, language);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void create(Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();		
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws UnsupportedOperationException
	 * */
	public void updateById(long id, Tariff entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
	
	/** 
	 * Finds tariffs by bicycle type id.
	 * 
	 * @param bicycleTypeId the id of bicycle type
	 * @return list of tariffs
	 * */
	public List<Tariff> findTariffsByBicycleTypeId(long bicycleTypeId) throws DAOException {
		return findListOfEntities(SQL_SELECT_TARIFF_BY_BICYCLE_TYPE_ID, bicycleTypeId);
	}

}
