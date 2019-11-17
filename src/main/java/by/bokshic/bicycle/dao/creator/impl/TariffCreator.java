package by.bokshic.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.entity.Tariff;
import by.bokshic.bicycle.dao.creator.EntityCreator;
import by.bokshic.bicycle.dao.creator.EntityCreatorDirector;
import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.entity.Tariff;

public class TariffCreator extends AbstractCreator<Tariff> {
	public TariffCreator() {
	}
	
	public TariffCreator(String language) {
		super(language);
	}

	public Tariff execute(ResultSet resultSet) throws SQLException {
		Tariff tariff = new Tariff();
		tariff.setId(resultSet.getLong(Tariff.TARIFF_ID_DB_FIELD));
		tariff.setPrice(resultSet.getBigDecimal(Tariff.PRICE_DB_FIELD));
		tariff.setRentalTime(resultSet.getFloat(Tariff.RENTAL_TIME_DB_FIELD));
		String language = getLanguage();
		String descriptionDBField = Tariff.DESCRIPTION_FIELD + "_" + language;
		tariff.setDescription(resultSet.getString(descriptionDBField));
		
		long bicycleTypeId = resultSet.getLong(Tariff.BICYCLE_TYPE_ID_DB_FIELD);
		EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
		EntityCreator<BicycleType> creator = creatorDirector.getCreator(BicycleType.class, language);
		BicycleType bicycleType = creator.execute(bicycleTypeId, resultSet);
		tariff.setBicycleType(bicycleType);
		return tariff;
	}
	
	public Tariff execute(long id, ResultSet resultSet) throws SQLException {
		Tariff tariff = execute(resultSet);
		tariff.setId(id);
		return tariff;
	}

}
