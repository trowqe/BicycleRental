package by.bokshic.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.dao.creator.EntityCreator;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.dao.creator.EntityCreator;
import by.bokshic.bicycle.dao.creator.EntityCreatorDirector;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.RentalPoint;


public class BicycleCreator extends AbstractCreator<Bicycle> {
	public BicycleCreator() {
	}
	
	public BicycleCreator(String language) {
		super(language);
	}

	public Bicycle execute(ResultSet resultSet) throws SQLException {
		Bicycle bicycle = new Bicycle();
		
		long bicycleId = resultSet.getLong(Bicycle.ID_DB_FIELD);
		bicycle.setId(bicycleId);		
		
		long rentalPointId = resultSet.getLong(Bicycle.RENTAL_POINT_ID_DB_FIELD);
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		String language = getLanguage();
		EntityCreator<RentalPoint> creator = creatorDirector.getCreator(RentalPoint.class, language);
		RentalPoint rentalPoint = creator.execute(rentalPointId, resultSet);
		bicycle.setPoint(rentalPoint);
		
		long bicycleModelId = resultSet.getLong(Bicycle.MODEL_ID_DB_FIELD);
		EntityCreator<BicycleModel> creatorModel = creatorDirector.getCreator(BicycleModel.class, language);
		BicycleModel bicycleModel = creatorModel.execute(bicycleModelId, resultSet);
		bicycle.setModel(bicycleModel);
				
		return bicycle;
	}

	public Bicycle execute(long id, ResultSet resultSet) throws SQLException {
		Bicycle bicycle = execute(resultSet);
		bicycle.setId(id);
		return bicycle;
		
	}

}
