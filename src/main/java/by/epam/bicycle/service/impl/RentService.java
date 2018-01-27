package by.epam.bicycle.service.impl;

import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.service.AbstractService;


public class RentService extends AbstractService<Rent>{
	
	public RentService() {
		super(Rent.class);
	}
	
	public RentService(String language) {
		super(Rent.class, language);
	}
}
