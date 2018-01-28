package by.epam.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import by.epam.bicycle.dao.creator.AbstractCreator;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;


public class RentCreator extends AbstractCreator<Rent> {
	public RentCreator() {
	}
	
	public RentCreator(String language) {
		super(language);
	}

	public Rent execute(ResultSet resultSet) throws SQLException {
		Rent rent = new Rent();
		
		rent.setId(resultSet.getLong(Rent.RENT_ID_DB_FIELD));		
		rent.setAmount(resultSet.getBigDecimal(Rent.AMOUNT_DB_FIELD));		
		
		Timestamp timestamp = resultSet.getTimestamp(Rent.CREATE_DATETIME_DB_FIELD);
		if (timestamp != null) {
		    Date date = new Date(timestamp.getTime());
		    rent.setCreateDateTime(date);		
		}
		
		timestamp = resultSet.getTimestamp(Rent.FINISH_DATETIME_DB_FIELD);
		if (timestamp != null) {
		    Date date = new Date(timestamp.getTime());
		    rent.setFinishDateTime(date);		
		}
				
		long userId = resultSet.getLong(Rent.USER_ID_DB_FIELD);
		User user = new User(userId);
		rent.setUser(user);		
			
		long bicycleId = resultSet.getLong(Rent.BICYCLE_ID_DB_FIELD);
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		String language = getLanguage();
		EntityCreator<Bicycle> creator = creatorDirector.getCreator(Bicycle.class, language);
		Bicycle bicycle = creator.execute(bicycleId, resultSet);
		rent.setBicycle(bicycle);
		
		long tariffId = resultSet.getLong(Rent.TARIFF_ID_DB_FIELD);
		EntityCreator<Tariff> creatorModel = creatorDirector.getCreator(Tariff.class, language);
		Tariff tariff = creatorModel.execute(tariffId, resultSet);
		rent.setTariff(tariff);
				
		return rent;
	}

	public Rent execute(long id, ResultSet resultSet) throws SQLException {
		Rent rent = execute(resultSet);
		rent.setId(id);
		return rent;
		
	}

}
