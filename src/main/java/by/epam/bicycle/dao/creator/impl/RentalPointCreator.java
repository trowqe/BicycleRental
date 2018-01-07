package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.entity.RentalPoint;

public class RentalPointCreator implements EntityCreator<RentalPoint> {

	public RentalPoint execute(ResultSet resultSet) throws SQLException {
		RentalPoint rentalPoint = new RentalPoint();
		rentalPoint.setId(resultSet.getLong(RentalPoint.RENTAL_POINT_ID_DB_FIELD));
		rentalPoint.setPhone(resultSet.getString(RentalPoint.PHONE_DB_FIELD));
		rentalPoint.setWorkingHours(resultSet.getString(RentalPoint.WORKING_HOURS_DB_FIELD));
		rentalPoint.setAddress(resultSet.getString(RentalPoint.ADDRESS_DB_FIELD));
		return rentalPoint;
	}
	
	public RentalPoint execute(long id, ResultSet resultSet) throws SQLException {
		RentalPoint rentalPoint = execute(resultSet);
		rentalPoint.setId(id);
		return rentalPoint;
		
	}

}
