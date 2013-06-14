package org.roussev.app.dao;

import javax.ejb.Stateless;

import org.roussev.app.dao.core.BaseDaoImp;
import org.roussev.app.entity.User;

/**
 * This is the UserDao implementation.
 * <p>
 * 
 * @author Atanas Roussev
 */
@Stateless
public class UserDaoImpl extends BaseDaoImp<User> implements UserDao {

	/**
	 * This method hooks the correct entity type, simplifying parent BaseDaoImp
	 * type discovery.
	 */
	@Override
	public Class<User> getType() {
		return User.class;
	}

}
