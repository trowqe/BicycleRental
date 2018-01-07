package by.epam.bicycle.service;

import java.util.List;
import by.epam.bicycle.entity.Entity;

public interface EntityService <K, T extends Entity> {
	List<T> findAll() throws ServiceException;
	T findEntityById(K id) throws ServiceException;
	void delete(K id) throws ServiceException;
	void delete(T entity) throws ServiceException;
	void create(T entity) throws ServiceException;
	void updateById(K id, T entity) throws ServiceException;
}
