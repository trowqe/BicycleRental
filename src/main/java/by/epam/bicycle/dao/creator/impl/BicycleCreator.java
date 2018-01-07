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
		bicycle.setId(resultSet.getLong(Bicycle.BICYCLE_ID_DB_FIELD));
		
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<RentalPoint> creator = creatorDirector.getCreator(RentalPoint.class);
		RentalPoint rentalPoint = creator.execute(resultSet);
		bicycle.setPoint(rentalPoint);
		
		EntityCreator<BicycleModel> creatorModel = creatorDirector.getCreator(BicycleModel.class);
		BicycleModel bicycleModel = creatorModel.execute(resultSet);
		bicycle.setModel(bicycleModel);
				
		return bicycle;
	}

}
