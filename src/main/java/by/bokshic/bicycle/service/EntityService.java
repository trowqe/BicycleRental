package by.bokshic.bicycle.service;

import java.util.List;
import by.bokshic.bicycle.entity.Entity;

public interface EntityService <T extends Entity> {
	List<T> findAll() throws ServiceException;
	T findEntityById(long id) throws ServiceException;
	void delete(long id) throws ServiceException;
	void delete(T entity) throws ServiceException;
	void create(T entity) throws ServiceException;
	void updateById(long id, T entity) throws ServiceException;
}
