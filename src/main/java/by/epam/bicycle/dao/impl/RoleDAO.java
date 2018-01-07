package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Role;

public class RoleDAO  extends AbstractDAO<Long, Role> {
	
	public RoleDAO() {
		super(Role.class, Role.TABLE_NAME);
	}
	
	public RoleDAO(Connection connection) {
		super(Role.class, Role.TABLE_NAME, connection);
	}

	public void create(Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(Long id, Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
