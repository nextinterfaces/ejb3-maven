package org.roussev.app.dao.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * This interface describes all basic DAO implementations.
 * <p>
 * 
 * @author Atanas Roussev
 */
public interface BaseDao<E extends Indexable> {

	/**
	 * Dependency injection setter method. This method is not needed within EJB
	 * environment as EntityManager is automatically populated via @EJB DI
	 * injection.
	 * <p>
	 * The method is rather used at unit testing during out-of-container DI
	 * injection.
	 * 
	 * @param em
	 *            EntityManager the EntityManager
	 */
	void setEntityManager(EntityManager em);

	/**
	 * Persists an instance of this new entity to the database
	 * 
	 * @param newInstance
	 * @return the new entity instance being created
	 */
	E create(E newInstance);

	/**
	 * Retrieves an object from the database using the given id as primary key.
	 * 
	 * @param id
	 *            the primary key id
	 */
	E read(Serializable id);

	/**
	 * Saves changes made to a persistent object.
	 * 
	 * @param transientObject
	 *            the entity to be updated
	 */
	E update(E transientObject);

	/**
	 * Removes an entity from the database
	 * 
	 * @param persistentObject
	 *            the entity to be deleted
	 */
	void delete(E persistentObject);

	/**
	 * Checks if an entity was previously persisted to the database using the
	 * indicated id as primary key.
	 */
	boolean exists(Serializable id);

	/**
	 * Returns all instances of this entity
	 */
	List<E> list();

	/**
	 * This method hooks the correct entity type E simplifying type discovery.
	 */
	Class<E> getType();

}
