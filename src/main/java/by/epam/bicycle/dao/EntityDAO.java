package by.epam.bicycle.dao;

import java.util.List;

import by.epam.bicycle.entity.Entity;

/**
 * 
 * Interface that contains a signatures of general methods of DAO classes.
 * 
 * @param <K> 	the type of identifier
 * @param <T> 	the type of entity
 * 
 * @author khatkovskaya
 * 
 */
public interface EntityDAO <K, T extends Entity> {
	/**
     * Finds all entities.
     * 
     * @return list of founded entities.
     * @throws DAOException if something goes wrong
     */
	List<T> findAll() throws DAOException;
	
	/**
     * Finds entity by it's identifiable field.
     * 
     * @param id identifier of the entity
     * @return founded entity
     * @throws DAOException if something goes wrong
     */
	T findEntityById(K id) throws DAOException;
	
	/**
     * Deletes entity founded by it's identifiable field.
     * 
     * @param id identifier of the entity
     * @throws DAOException if something goes wrong
     */
	void delete(K id) throws DAOException;

	/**
     * Deletes concrete entity.
     * 
     * @param entity concrete entity
     * @throws DAOException if something goes wrong
     */
	void delete(T entity) throws DAOException;

	/**
     * Creates concrete entity.
     * 
     * @param entity concrete entity
     * @throws DAOException if something goes wrong
     */
	void create(T entity) throws DAOException;
	
	/**
     * Updates concrete entity founded by id.
     * 
     * @param id identifier of the entity
     * @param entity updated entity
     * @throws DAOException if something goes wrong
     */
	void updateById(K id, T entity) throws DAOException;
}
