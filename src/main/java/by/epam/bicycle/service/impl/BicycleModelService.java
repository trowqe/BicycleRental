package by.epam.bicycle.service.impl;

import by.epam.bicycle.entity.BicycleModel;

public class BicycleModelService extends AbstractService<BicycleModel> {
	
	public BicycleModelService() {
		super(BicycleModel.class);
	}
	
	public BicycleModelService(String language) {
		super(BicycleModel.class, language);
	}
}
