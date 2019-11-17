package by.bokshic.bicycle.dao;

import by.bokshic.bicycle.dao.impl.AbstractDAO;
import by.bokshic.bicycle.dao.impl.BicycleDAO;
import by.bokshic.bicycle.dao.impl.BicycleModelDAO;
import by.bokshic.bicycle.dao.impl.BicycleTypeDAO;
import by.bokshic.bicycle.dao.impl.RentDAO;
import by.bokshic.bicycle.dao.impl.RentalPointDAO;
import by.bokshic.bicycle.dao.impl.RoleDAO;
import by.bokshic.bicycle.dao.impl.TariffDAO;
import by.bokshic.bicycle.dao.impl.UserDAO;
import by.bokshic.bicycle.entity.Bicycle;
import by.bokshic.bicycle.entity.BicycleModel;
import by.bokshic.bicycle.entity.BicycleType;
import by.bokshic.bicycle.entity.Entity;
import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.entity.RentalPoint;
import by.bokshic.bicycle.entity.Role;
import by.bokshic.bicycle.entity.Tariff;
import by.bokshic.bicycle.entity.User;

public class DAOFactory {

	@SuppressWarnings("unchecked")
	public <T extends Entity> AbstractDAO<T> getEntityDAO(Class<T> entityClass, String language) {
		AbstractDAO<T> dao = null;
		if (entityClass.equals(Bicycle.class)) {
	    	dao = (AbstractDAO<T>) new BicycleDAO();
	    } else if (entityClass.equals(BicycleModel.class)) {
	    	dao = (AbstractDAO<T>) new BicycleModelDAO();
	    } else if (entityClass.equals(BicycleType.class)) {
	    	dao = (AbstractDAO<T>) new BicycleTypeDAO();
	    } else if (entityClass.equals(RentalPoint.class)) {
	    	dao = (AbstractDAO<T>) new RentalPointDAO();
	    } else if (entityClass.equals(Rent.class)) {
	    	dao = (AbstractDAO<T>) new RentDAO();
	    } else if (entityClass.equals(Role.class)) {
	    	dao = (AbstractDAO<T>) new RoleDAO();
	    } else if (entityClass.equals(Tariff.class)) {
	    	dao = (AbstractDAO<T>) new TariffDAO();
	    } else if (entityClass.equals(User.class)) {
	    	dao = (AbstractDAO<T>) new UserDAO();
	    } else {
	    	throw new UnsupportedOperationException();
	    }
		dao.setLanguage(language);
		return dao;
	}
}
