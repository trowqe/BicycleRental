package by.epam.bicycle.dao;

import java.sql.Connection;
import java.util.List;

import by.epam.bicycle.entity.Entity;

/**
 * 
 * Abstract class that contains a signatures of general methods of DAO classes.
 * 
 * @param <K> 	the type of identifier
 * @param <T> 	the type of entity
 * 
 * @author khatkovskaya
 * 
 */
public abstract class AbstractDAO<K, T extends Entity> {
	
	/**
	 * Connection to the database in specific wrapper.
	 */
	protected WrapperConnection wrappedConnection;
	
	/**
	 * Default constructor.
	 */
	public AbstractDAO() {
	}
	
	/**
	 * Sets connection to the database.
	 * 
	 * @param connection concrete connection to the database.
	 */
	public AbstractDAO(Connection connection) {
		wrappedConnection = new WrapperConnection(connection);
	}
	
	/**
     * Finds all entities.
     * 
     * @return list of founded entities.
     * @throws DAOException if something goes wrong
     */
	public abstract List<T> findAll() throws DAOException;
	
	/**
     * Finds entity by it's identifiable field.
     * 
     * @param id identifier of the entity
     * @return founded entity
     * @throws DAOException if something goes wrong
     */
	public abstract T findEntityById(K id) throws DAOException;
	
	/**
     * Deletes entity founded by it's identifiable field.
     * 
     * @param id identifier of the entity
     * @throws DAOException if something goes wrong
     */
	public abstract void delete(K id) throws DAOException;

	/**
     * Deletes concrete entity.
     * 
     * @param entity concrete entity
     * @throws DAOException if something goes wrong
     */
	public abstract void delete(T entity) throws DAOException;

	/**
     * Creates concrete entity.
     * 
     * @param entity concrete entity
     * @throws DAOException if something goes wrong
     */
	public abstract void create(T entity) throws DAOException;
	
	/**
     * Updates concrete entity founded by id.
     * 
     * @param id identifier of the entity
     * @param entity updated entity
     * @throws DAOException if something goes wrong
     */
	public abstract void updateById(K id, T entity) throws DAOException;
}
