package by.epam.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.EntityDAO;
import by.epam.bicycle.dao.creator.EntityCreator;
import by.epam.bicycle.dao.creator.EntityCreatorDirector;
import by.epam.bicycle.dao.pool.WrapperConnection;
import by.epam.bicycle.entity.Entity;


public abstract class AbstractDAO<T extends Entity> implements EntityDAO<T> {
	private final Class<T> entityClass; 
	private final String tableName; 
	private String language;
	private WrapperConnection wrappedConnection;
	
	private final static String SELECT_ALL_ENTITIES = "select * from ";
	private final static String SELECT_ENTITIE_BY_ID = " where id = ?";	
	private final static String DELETE_ENTITIE_BY_ID = "delete from ";	
	
	public AbstractDAO(Class<T> entityClass, String tableName) {
		this.entityClass = entityClass;
		this.tableName = tableName;
		this.language = ConfigurationManager.getProperty(ConfigurationManager.LANGUAGE_DEFAULT);
	}
	
	public AbstractDAO(Class<T> entityClass, String tableName, String language) {
		this.entityClass = entityClass;
		this.tableName = tableName;
		this.language = language;
	}
	
	public AbstractDAO(Class<T> entityClass, String tableName, Connection connection) {
		this(entityClass, tableName);
		setWrappedConnection(new WrapperConnection(connection));
	}
	
	public AbstractDAO(Class<T> entityClass, String tableName, Connection connection, String language) {
		this(entityClass, tableName, language);
		setWrappedConnection(new WrapperConnection(connection));
	}

	public WrapperConnection getWrappedConnection() {
		return wrappedConnection;
	}

	public void setWrappedConnection(WrapperConnection wrappedConnection) {
		this.wrappedConnection = wrappedConnection;
	}
	
	public void setWrappedConnection(Connection connection) {
		setWrappedConnection(new WrapperConnection(connection));
	}
	
	public String getTableName() {
		return tableName;
	}	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<T> findAll() throws DAOException {
		return findListOfEntities(SELECT_ALL_ENTITIES + tableName);
	}
		
	public T findEntityById(long id) throws DAOException {
		String sql = SELECT_ALL_ENTITIES + tableName + SELECT_ENTITIE_BY_ID;
		return findSingleEntitie(sql, id);
	}
	
	public void delete(long id) throws DAOException {
		String sql = DELETE_ENTITIE_BY_ID + tableName + SELECT_ENTITIE_BY_ID;
		executeUpdateEntitie(sql, id);
	}
	
	public void delete(T entity) throws DAOException {
		long id = entity.getId();
		delete(id);
	}
	
	public T findSingleEntitie(String sql) throws DAOException {
		T entitie = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getWrappedConnection().getStatement();
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) { 
				EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
				EntityCreator<T> creator = creatorDirector.getCreator(entityClass, language);
				entitie = creator.execute(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}

		return entitie;	
	}
	
	public T findSingleEntitie(String sql, Object ... params) throws DAOException {
		T entitie = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = getWrappedConnection().getPreparedStatement(sql, params);
			resultSet = statement.executeQuery();

			if (resultSet.next()) { 
				EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
				EntityCreator<T> creator = creatorDirector.getCreator(entityClass, language);
				entitie = creator.execute(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}

		return entitie;	
	}
	
	public List<T> findListOfEntities(String sql, Object ... params) throws DAOException {
		List<T> entities = new ArrayList<T>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = getWrappedConnection().getPreparedStatement(sql, params);
			resultSet = statement.executeQuery();

			EntityCreatorDirector creatorDirector = new EntityCreatorDirector();
			EntityCreator<T> creator = creatorDirector.getCreator(entityClass, language);

			while (resultSet.next()) {
				T entitie = creator.execute(resultSet);
				entities.add(entitie);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}

		return entities;
	}
	
	
	public void executeUpdateEntitie(String sql, Object ... params) throws DAOException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = getWrappedConnection().getPreparedStatement(sql, params);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			getWrappedConnection().closeResultSet(resultSet);
			getWrappedConnection().closeStatement(statement);
		}
	}

	
}
