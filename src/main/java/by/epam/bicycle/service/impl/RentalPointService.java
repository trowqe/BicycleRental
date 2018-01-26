package by.epam.bicycle.service.impl;

import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.service.AbstractService;

public class RentalPointService extends AbstractService<RentalPoint>{
	
	public RentalPointService() {
		super(RentalPoint.class);
	}
	
	public RentalPointService(String language) {
		super(RentalPoint.class, language);
	}
}
