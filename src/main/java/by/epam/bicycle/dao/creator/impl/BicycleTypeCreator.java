package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.entity.BicycleType;

public class BicycleTypeCreator implements EntityCreator<BicycleType> {

	public BicycleType execute(ResultSet resultSet) throws SQLException {
		BicycleType bicycleType = new BicycleType();
		bicycleType.setId(resultSet.getLong(BicycleType.BICYCLE_TYPE_ID_DB_FIELD));
		bicycleType.setName(resultSet.getString(BicycleType.NAME_DB_FIELD));
		return bicycleType;
	}
	
	public BicycleType execute(long id, ResultSet resultSet) throws SQLException {
		BicycleType bicycleType = execute(resultSet);
		bicycleType.setId(id);
		return bicycleType;
		
	}

}
