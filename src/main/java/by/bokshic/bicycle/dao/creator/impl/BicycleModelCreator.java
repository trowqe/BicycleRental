package by.bokshic.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.dao.creator.EntityCreator;
import by.bokshic.bicycle.dao.creator.EntityCreatorDirector;
import by.bokshic.bicycle.entity.BicycleModel;

public class BicycleModelCreator extends AbstractCreator<BicycleModel> {
	public BicycleModelCreator() {
	}
	
	public BicycleModelCreator(String language) {
		super(language);
	}

	public BicycleModel execute(ResultSet resultSet) throws SQLException {
		BicycleModel bicycleModel = new BicycleModel();
		bicycleModel.setId(resultSet.getLong(BicycleModel.ID_DB_FIELD));
		String language = getLanguage();
		String firmDBField = BicycleModel.FIRM_DB_FIELD + "_" + language;
		bicycleModel.setFirm(resultSet.getString(firmDBField));
		
		String modelDBField = BicycleModel.MODEL_DB_FIELD + "_" + language;
		bicycleModel.setModel(resultSet.getString(modelDBField));
		
		String notesDBField = BicycleModel.NOTES_DB_FIELD + "_" + language;
		bicycleModel.setNotes(resultSet.getString(notesDBField));
		
		long bicycleTypeId = resultSet.getLong(BicycleModel.BICYCLE_TYPE_ID_DB_FIELD);
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<BicycleType> creator = creatorDirector.getCreator(BicycleType.class, language);
		
		BicycleType bicycleType = creator.execute(bicycleTypeId, resultSet);
		bicycleModel.setBicycleType(bicycleType);		
		
		return bicycleModel;
	}
	
	public BicycleModel execute(long id, ResultSet resultSet) throws SQLException {
		BicycleModel bicycleModel = execute(resultSet);
		bicycleModel.setId(id);
		return bicycleModel;
		
	}

}
