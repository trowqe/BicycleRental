package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.RentalPoint;

public class RentalPointDAO extends AbstractDAO<RentalPoint> {

	public RentalPointDAO() {
		super(RentalPoint.class, RentalPoint.TABLE_NAME);
	}
	
	public RentalPointDAO(String language) {
		super(RentalPoint.class, RentalPoint.TABLE_NAME, language);
	}

	public RentalPointDAO(Connection connection, String language) {
		super(RentalPoint.class, RentalPoint.TABLE_NAME, connection, language);
	}

	public void create(RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(long id, RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
