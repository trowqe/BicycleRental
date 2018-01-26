package by.epam.bicycle.dao.impl;

import java.sql.Connection;

import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.entity.Role;

public class RoleDAO extends AbstractDAO<Role> {
	
	public RoleDAO() {
		super(Role.class, Role.TABLE_NAME);
	}
	
	public RoleDAO(String language) {
		super(Role.class, Role.TABLE_NAME, language);
	}
	
	public RoleDAO(Connection connection, String language) {
		super(Role.class, Role.TABLE_NAME, connection, language);
	}

	public void create(Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public void updateById(long id, Role entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
