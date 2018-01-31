package by.epam.bicycle.dao;

import by.epam.bicycle.dao.impl.AbstractDAO;
import by.epam.bicycle.dao.impl.BicycleDAO;
import by.epam.bicycle.dao.impl.BicycleModelDAO;
import by.epam.bicycle.dao.impl.BicycleTypeDAO;
import by.epam.bicycle.dao.impl.RentDAO;
import by.epam.bicycle.dao.impl.RentalPointDAO;
import by.epam.bicycle.dao.impl.RoleDAO;
import by.epam.bicycle.dao.impl.TariffDAO;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.entity.Bicycle;
import by.epam.bicycle.entity.BicycleModel;
import by.epam.bicycle.entity.BicycleType;
import by.epam.bicycle.entity.Entity;
import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.RentalPoint;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.Tariff;
import by.epam.bicycle.entity.User;

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
