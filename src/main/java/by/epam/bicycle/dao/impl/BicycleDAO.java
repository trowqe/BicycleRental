package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Bicycle;

public class BicycleDAO extends AbstractDAO<Bicycle> {
	private static final String SQL_SELECT_ALL_BICYCLES = "select b.*, m.*, r.*,  t.* from bicycles b "
			+ "left join bicycle_models m on b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id";

	private static final String SQL_SELECT_BICYCLE_BY_FILTER = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE "
			+ "b.id NOT IN (SELECT bicycle_id FROM rents WHERE datetime_finish is null) ";

	private static final String SQL_SELECT_BICYCLE_BY_ID = "SELECT b.*, m.*, t.*, r.* from bicycles b "
			+ "LEFT JOIN bicycle_models m ON b.bicycle_model_id = m.id "
			+ "left join bicycle_types t on m.bicycle_type_id = t.id "
			+ "left join rental_points r on b.rental_point_id = r.id WHERE b.id = ?";


	public BicycleDAO() {
		super(Bicycle.class, Bicycle.TABLE_NAME);
	}
	
	public BicycleDAO(String language) {
		super(Bicycle.class, Bicycle.TABLE_NAME, language);
	}

	public BicycleDAO(Connection connection, String language) {
		super(Bicycle.class, Bicycle.TABLE_NAME, connection, language);
	}
	
	public List<Bicycle> findAll() throws DAOException {
		return findListOfEntities(SQL_SELECT_ALL_BICYCLES);
	}
	
	public Bicycle findEntityById(long id) throws DAOException {
		return findSingleEntitie(SQL_SELECT_BICYCLE_BY_ID, id);
	}
	
	public void create(Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(long id, Bicycle entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public List<Bicycle> findActiveBicycleByFilter(long rentalPointId, long bicycleTypeId, String firm, String model)
			throws DAOException {

		String sql = SQL_SELECT_BICYCLE_BY_FILTER;
		List<Object> params = new ArrayList<Object>();

		if (rentalPointId != -1) {
			sql += " and b.rental_point_id = ? ";
			params.add(rentalPointId);
		}

		if (bicycleTypeId != -1) {
			sql += " and m.bicycle_type_id = ? ";
			params.add(bicycleTypeId);
		}

		if (!firm.equals("")) {
			sql += " and m.firm_" + getLanguage() + " like ? ";
			params.add(firm);
		}

		if (!model.equals("")) {
			sql += " and m.model_" + getLanguage() + " like ? ";
			params.add(model);
		}

		Object[] arrParams = params.toArray();

		return findListOfEntities(sql, arrParams);
	}

}
