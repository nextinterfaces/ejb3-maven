package org.roussev.app.dao;

import javax.ejb.Remote;

import org.roussev.app.dao.core.BaseDao;
import org.roussev.app.entity.User;

/**
 * This remote session bean is a DAO class describing User entity access
 * methods.
 * <p>
 * 
 * @author Atanas Roussev
 */
@Remote
public interface UserDao extends BaseDao<User> {

}
