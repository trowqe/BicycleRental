package by.epam.bicycle.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.dao.AbstractDAO;
import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.DAOFactory;
import by.epam.bicycle.entity.Entity;

public abstract class AbstractService<T extends Entity> implements EntityService<T> {
	private final Class<T> entityClass; 
	private String language;

	public AbstractService(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.language = ConfigurationManager.getProperty("language.default");
	}
		
	public AbstractService(Class<T> entityClass, String language) {
		super();
		this.entityClass = entityClass;
		this.language = language;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<T> findAll() throws ServiceException {
		List<T> entities = new ArrayList<T>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			 entities = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return entities;	
	}
	
	public T findEntityById(long id) throws ServiceException {
		T entitie = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			entitie = dao.findEntityById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}
		return entitie;
	}
	
	public void delete(long id) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			 dao.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}		
	}
	
	public void delete(T entity) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			 dao.delete(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}			
	}
	
	public void create(T entity) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			 dao.create(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}		
	}
	
	public void updateById(long id, T entity) throws ServiceException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		AbstractDAO<T> dao = daoFactory.getEntityDAO(entityClass, language);
		dao.setWrappedConnection(connection);
		try {
			 dao.updateById(id, entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			pool.returnConnectionToPool(connection);
		}		
	}
}
