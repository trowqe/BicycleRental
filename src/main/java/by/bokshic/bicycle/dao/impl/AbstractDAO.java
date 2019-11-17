package by.bokshic.bicycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bokshic.bicycle.config.ConfigurationManager;
import by.bokshic.bicycle.dao.DAOException;
import by.bokshic.bicycle.dao.EntityDAO;
import by.bokshic.bicycle.dao.creator.EntityCreator;
import by.bokshic.bicycle.dao.creator.EntityCreatorDirector;
import by.bokshic.bicycle.dao.pool.WrapperConnection;
import by.bokshic.bicycle.entity.Entity;

/**
 * 
 * Abstract class that override some of general methods of EntityDAO.
 * 
 * @param <T> 	the type of entity
 * 
 * @author khatkovskaya
 * 
 */
public abstract class AbstractDAO<T extends Entity> implements EntityDAO<T> {
	/**
	  * The <tt>Class</tt> of entity object.
	  */
	private final Class<T> entityClass; 
	
	/**
	  * Name of table for common query.
	  */
	private final String tableName; 
	
	/**
	  * Language that specified data that will be query from table.
	  */
	private String language;
	
	/**
	  * Connection to the database in wrapper class.
	  */
	private WrapperConnection wrappedConnection;
	
	/**
	  * Common query for select all entities.
	  */
	private final static String SELECT_ALL_ENTITIES = "select * from ";
	
	/**
	  * Common part of query for where construction for select by id. 
	  */
	private final static String SELECT_ENTITIE_BY_ID = " where id = ?";	
	
	/**
	  * Common query for delete entity.
	  */
	private final static String DELETE_ENTITIE_BY_ID = "delete from ";	
	
	
	/**
	 * Creates AbstractDAO with the specified entity class and name of table
	 * and default language, that will be get from configuration config.properties.
	 * 
	 * @param entityClass the class of entity object
	 * @param tableName the name of table for common query
	*/
	public AbstractDAO(Class<T> entityClass, String tableName) {
		this.entityClass = entityClass;
		this.tableName = tableName;
		this.language = ConfigurationManager.getProperty(ConfigurationManager.LANGUAGE_DEFAULT);
	}
	
	/**
	 * Creates AbstractDAO with the specified entity class, name of table and language.
	 * @param entityClass the class of entity object
	 * @param tableName the name of table for common query
	 * @param language the language for select query
	*/
	public AbstractDAO(Class<T> entityClass, String tableName, String language) {
		this.entityClass = entityClass;
		this.tableName = tableName;
		this.language = language;
	}
	
	/**
	 * Creates AbstractDAO with the specified entity class, name of table and connection to the database.
	 * Language sets by default from configuration config.properties.
	 * 
	 * @param entityClass the class of entity object
	 * @param tableName the name of table for common query
	 * @param connection the connection to the database
	 * */
	public AbstractDAO(Class<T> entityClass, String tableName, Connection connection) {
		this(entityClass, tableName);
		setWrappedConnection(new WrapperConnection(connection));
	}
	
	/**
	 * Creates AbstractDAO with the specified entity class, name of table, connection to the database and language.
	 * 
	 * @param entityClass the class of entity object
	 * @param tableName the name of table for common query
	 * @param connection the connection to the database
	 * @param language the language for select query
	 * */
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
	
	/**
	 * {@inheritDoc}
	 * */
	public List<T> findAll() throws DAOException {
		return findListOfEntities(SELECT_ALL_ENTITIES + tableName);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public T findEntityById(long id) throws DAOException {
		String sql = SELECT_ALL_ENTITIES + tableName + SELECT_ENTITIE_BY_ID;
		return findSingleEntitie(sql, id);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public void delete(long id) throws DAOException {
		String sql = DELETE_ENTITIE_BY_ID + tableName + SELECT_ENTITIE_BY_ID;
		executeUpdateEntitie(sql, id);
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public void delete(T entity) throws DAOException {
		long id = entity.getId();
		delete(id);
	}
	
	/**
	 * Executes specified select query that should return single entity.
	 * @param sql the string of sql query
	 * @return founded entity
	 * @throws DAOException when catches SQLException
	 * */
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
	
	/**
	 * Executes specified select query with parameters that should return single entity.
	 * 
	 * @param sql the string of sql query
	 * @param params list of parameters in sql query
	 * @return founded entity
	 * @throws DAOException when catches SQLException
	*/
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
	
	/**
	 * Executes specified select query with parameters that should return list of entities.
	 * 
	 * @param sql the string of sql query
	 * @param params list of parameters in sql query
	 * @return founded list of entities
	 * @throws DAOException when catches SQLException
	*/
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
	
	/**
	 * Executes specified update query with parameters.
	 * 
	 * @param sql the string of sql query
	 * @param params list of parameters in sql query
	 * @throws DAOException when catches SQLException
	*/
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
