package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.RentalPoint;

public class RentalPointDAO extends AbstractDAO<Long, RentalPoint> {

	public RentalPointDAO() {
		super(RentalPoint.class, RentalPoint.TABLE_NAME);
	}

	public RentalPointDAO(Connection connection) {
		super(RentalPoint.class, RentalPoint.TABLE_NAME, connection);
	}

	public void create(RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, RentalPoint entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
