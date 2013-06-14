package org.roussev.app.dao.core;

/**
 * Interface for an object with a database primary key ID
 * <p>
 * 
 * @author Atanas Roussev
 */
public interface Indexable {

	/**
	 * returns the database id
	 * 
	 * @return
	 */
	Long getId();

	/**
	 * @param id
	 *            the id to set
	 */
	void setId(Long id);

}