package by.bokshic.bicycle.service.impl;

import by.bokshic.bicycle.entity.BicycleType;

public class BicycleTypeService extends AbstractService<BicycleType> {
	
	public BicycleTypeService() {
		super(BicycleType.class);
	}
	
	public BicycleTypeService(String language) {
		super(BicycleType.class, language);
	}
}
