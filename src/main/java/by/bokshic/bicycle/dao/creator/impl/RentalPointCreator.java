package by.bokshic.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.RentalPoint;

public class RentalPointCreator extends AbstractCreator<RentalPoint> {
	public RentalPointCreator() {
	}
	
	public RentalPointCreator(String language) {
		super(language);
	}

	public RentalPoint execute(ResultSet resultSet) throws SQLException {
		RentalPoint rentalPoint = new RentalPoint();
		rentalPoint.setId(resultSet.getLong(RentalPoint.RENTAL_POINT_ID_DB_FIELD));
		rentalPoint.setPhone(resultSet.getString(RentalPoint.PHONE_DB_FIELD));
		String language = getLanguage();
		String workingHoursDBField = RentalPoint.WORKING_HOURS_DB_FIELD + "_" + language;
		rentalPoint.setWorkingHours(workingHoursDBField);
		String addressDbField = RentalPoint.ADDRESS_DB_FIELD + "_" + language;
		rentalPoint.setAddress(resultSet.getString(addressDbField));
		return rentalPoint;
	}
	
	public RentalPoint execute(long id, ResultSet resultSet) throws SQLException {
		RentalPoint rentalPoint = execute(resultSet);
		rentalPoint.setId(id);
		return rentalPoint;
		
	}

}
