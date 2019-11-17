package by.bokshic.bicycle.dao.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.Entity;
import by.bokshic.bicycle.entity.Entity;

public interface EntityCreator <T extends Entity> {
	public T execute(ResultSet resultSet) throws SQLException;
	public T execute(long id, ResultSet resultSet) throws SQLException;
}
