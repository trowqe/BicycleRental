package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.RentalPoint;


public class BicycleCreator implements EntityCreator<Bicycle> {

	public Bicycle execute(ResultSet resultSet) throws SQLException {
		Bicycle bicycle = new Bicycle();
		
		long bicycleId = resultSet.getLong(Bicycle.ID_DB_FIELD);
		bicycle.setId(bicycleId);		
		
		long rentalPointId = resultSet.getLong(Bicycle.RENTAL_POINT_ID_DB_FIELD);
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<RentalPoint> creator = creatorDirector.getCreator(RentalPoint.class);
		RentalPoint rentalPoint = creator.execute(rentalPointId, resultSet);
		bicycle.setPoint(rentalPoint);
		
		long bicycleModelId = resultSet.getLong(Bicycle.MODEL_ID_DB_FIELD);
		EntityCreator<BicycleModel> creatorModel = creatorDirector.getCreator(BicycleModel.class);
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
