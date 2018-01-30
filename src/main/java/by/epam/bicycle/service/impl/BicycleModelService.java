package by.epam.bicycle.service.impl;

import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.service.AbstractService;

public class BicycleModelService extends AbstractService<BicycleModel> {
	
	public BicycleModelService() {
		super(BicycleModel.class);
	}
	
	public BicycleModelService(String language) {
		super(BicycleModel.class, language);
	}
}
