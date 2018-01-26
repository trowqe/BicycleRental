package by.epam.bicycle.dao.creator;

import java.util.HashMap;
import java.util.Map;

import by.epam.bicycle.dao.creator.impl.BicycleCreator;
import by.epam.bicycle.dao.creator.impl.BicycleModelCreator;
import by.epam.bicycle.dao.creator.impl.BicycleTypeCreator;
import by.epam.bicycle.dao.creator.impl.RentalPointCreator;
import by.epam.bicycle.dao.creator.impl.TariffCreator;
import by.epam.bicycle.dao.creator.impl.UserCreator;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.Entity;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.entity.Tariff;


public class EntityCreatorDirector {
	private final Map<Class<? extends Entity>, EntityCreator<? extends Entity>> map = new HashMap<Class<? extends Entity>, EntityCreator<? extends Entity>>();
	
	public EntityCreatorDirector() {
		map.put(User.class, new UserCreator());
		map.put(RentalPoint.class, new RentalPointCreator());
		map.put(BicycleType.class, new BicycleTypeCreator());
		map.put(Bicycle.class, new BicycleCreator());
		map.put(BicycleModel.class, new BicycleModelCreator());
		map.put(Tariff.class, new TariffCreator());
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> EntityCreator<T> getCreator(Class<T> entity, String language){
		AbstractCreator<T> creator = (AbstractCreator<T>) map.get(entity);
		creator.setLanguage(language);
		return creator;
	}
}
