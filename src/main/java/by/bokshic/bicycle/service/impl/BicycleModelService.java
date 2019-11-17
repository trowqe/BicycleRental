package by.bokshic.bicycle.service.impl;

import by.bokshic.bicycle.entity.BicycleModel;

public class BicycleModelService extends AbstractService<BicycleModel> {
	
	public BicycleModelService() {
		super(BicycleModel.class);
	}
	
	public BicycleModelService(String language) {
		super(BicycleModel.class, language);
	}
}
