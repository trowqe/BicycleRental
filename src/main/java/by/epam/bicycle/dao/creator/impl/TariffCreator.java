package by.epam.bicycle.dao.creator.impl;	

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.Tariff;

public class TariffCreator implements EntityCreator<Tariff> {

	public Tariff execute(ResultSet resultSet) throws SQLException {
		Tariff tariff = new Tariff();
		tariff.setId(resultSet.getLong(Tariff.TARIFF_ID_DB_FIELD));
		tariff.setPrice(resultSet.getBigDecimal(Tariff.PRICE_DB_FIELD));
		tariff.setRentalTime(resultSet.getFloat(Tariff.RENTAL_TIME_DB_FIELD));
		tariff.setDescription(resultSet.getString(Tariff.DESCRIPTION_FIELD));
		
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<BicycleType> creator = creatorDirector.getCreator(BicycleType.class);
		BicycleType bicycleType = creator.execute(resultSet);
		tariff.setBicycleType(bicycleType);
		return tariff;
	}

}
