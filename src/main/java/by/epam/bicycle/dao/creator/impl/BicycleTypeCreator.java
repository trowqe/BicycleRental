package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.AbstractCreator;
import by.epam.bicycle.entity.BicycleType;

public class BicycleTypeCreator extends AbstractCreator<BicycleType> {
	public BicycleTypeCreator() {
	}
	
	public BicycleTypeCreator(String language) {
		super(language);
	}

	public BicycleType execute(ResultSet resultSet) throws SQLException {
		BicycleType bicycleType = new BicycleType();
		bicycleType.setId(resultSet.getLong(BicycleType.BICYCLE_TYPE_ID_DB_FIELD));
		String language = getLanguage();
		String nameDBField = BicycleType.NAME_DB_FIELD + "_" + language;
		bicycleType.setName(resultSet.getString(nameDBField));
		return bicycleType;
	}
	
	public BicycleType execute(long id, ResultSet resultSet) throws SQLException {
		BicycleType bicycleType = execute(resultSet);
		bicycleType.setId(id);
		return bicycleType;
		
	}

}
