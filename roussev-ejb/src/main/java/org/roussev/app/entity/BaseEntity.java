package org.roussev.app.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.roussev.app.dao.core.Indexable;

/**
 * This class is the parent class for all entities grouping common data behaviour.
 * <p>
 * 
 * @author Atanas Roussev
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable, Indexable {

	/**
	 * Utility method overriding 
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
