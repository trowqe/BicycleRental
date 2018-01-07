package by.epam.bicycle.dao.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.entity.Entity;

public interface EntityCreator <T extends Entity> {
	public T execute(ResultSet resultSet) throws SQLException;
}
