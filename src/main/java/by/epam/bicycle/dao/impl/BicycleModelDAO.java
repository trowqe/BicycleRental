package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.BicycleModel;

public class BicycleModelDAO extends AbstractDAO<BicycleModel> {
	private final static String SELECT_ALL_MODELS = "select m.*, t.* from bicycle_models m left join bicycle_types t on " 
			+ " m.bicycle_type_id = t.id";
	
	public BicycleModelDAO() {
		super(BicycleModel.class, BicycleModel.TABLE_NAME);
	}
	
	public BicycleModelDAO(String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, language);
	}
	
	public BicycleModelDAO(Connection connection, String language) {
		super(BicycleModel.class, BicycleModel.TABLE_NAME, connection, language);
	}
	
	public List<BicycleModel> findAll() throws DAOException  {
		return findListOfEntities(SELECT_ALL_MODELS);
	}
	
	public void create(BicycleModel entity) throws DAOException {		
		throw new UnsupportedOperationException();		
	}
	
	public void updateById(long id, BicycleModel entity) throws DAOException {
		throw new UnsupportedOperationException();				
	}
}
