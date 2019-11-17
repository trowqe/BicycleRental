package by.bokshic.bicycle.dao.creator;

import java.util.HashMap;
import java.util.Map;

import by.bokshic.bicycle.dao.creator.impl.*;
import by.bokshic.bicycle.entity.*;
import by.bokshic.bicycle.dao.creator.impl.AbstractCreator;
import by.bokshic.bicycle.dao.creator.impl.BicycleCreator;
import by.bokshic.bicycle.dao.creator.impl.BicycleModelCreator;
import by.bokshic.bicycle.dao.creator.impl.BicycleTypeCreator;
import by.bokshic.bicycle.dao.creator.impl.RentCreator;
import by.bokshic.bicycle.dao.creator.impl.RentalPointCreator;
import by.bokshic.bicycle.dao.creator.impl.TariffCreator;
import by.bokshic.bicycle.dao.creator.impl.UserCreator;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.entity.Entity;
import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.entity.Tariff;


public class EntityCreatorDirector {
	private final Map<Class<? extends Entity>, EntityCreator<? extends Entity>> map = new HashMap<Class<? extends Entity>, EntityCreator<? extends Entity>>();
	
	public EntityCreatorDirector() {
		map.put(User.class, new UserCreator());
		map.put(RentalPoint.class, new RentalPointCreator());
		map.put(BicycleType.class, new BicycleTypeCreator());
		map.put(Bicycle.class, new BicycleCreator());
		map.put(BicycleModel.class, new BicycleModelCreator());
		map.put(Tariff.class, new TariffCreator());
		map.put(Rent.class, new RentCreator());
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> EntityCreator<T> getCreator(Class<T> entity, String language){
		AbstractCreator<T> creator = (AbstractCreator<T>) map.get(entity);
		creator.setLanguage(language);
		return creator;
	}
}
