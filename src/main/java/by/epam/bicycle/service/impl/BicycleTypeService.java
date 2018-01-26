package by.epam.bicycle.service.impl;

import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.service.AbstractService;

public class BicycleTypeService extends AbstractService<BicycleType> {
	
	public BicycleTypeService() {
		super(BicycleType.class);
	}
	
	public BicycleTypeService(String language) {
		super(BicycleType.class, language);
	}
}
