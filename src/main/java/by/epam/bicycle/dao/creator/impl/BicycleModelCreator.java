package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.BicycleType;

public class BicycleModelCreator implements EntityCreator<BicycleModel> {

	public BicycleModel execute(ResultSet resultSet) throws SQLException {
		BicycleModel bicycleModel = new BicycleModel();
		bicycleModel.setId(resultSet.getLong(BicycleModel.BICYCLE_MODEL_ID_DB_FIELD));
		bicycleModel.setFirm(resultSet.getString(BicycleModel.FIRM_DB_FIELD));
		bicycleModel.setModel(resultSet.getString(BicycleModel.MODEL_DB_FIELD));
		bicycleModel.setNotes(resultSet.getString(BicycleModel.NOTES_DB_FIELD));
		
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<BicycleType> creator = creatorDirector.getCreator(BicycleType.class);
		BicycleType bicycleType = creator.execute(resultSet);
		bicycleModel.setBicycleType(bicycleType);		
		
		return bicycleModel;
	}

}
