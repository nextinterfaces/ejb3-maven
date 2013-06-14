package org.roussev.app.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.roussev.app.Globals;
import org.roussev.app.dao.UserDao;
import org.roussev.app.dao.core.BaseDao;

/**
 * This is a utility class for retrieving remote DAO instances from JNDI.
 * 
 * @author Atanas Roussev
 */
public class JndiInvoker {

	/**
	 * default name "localhost". Could be IP address or domain name too.
	 */
	private String server = "localhost";

	/**
	 * The method retrieves JNDI naming context using populated server instance
	 * variable. If no server has been specified, the default "localhost" is
	 * used.
	 * <p>
	 * 
	 * @return the JNDI context
	 */
	public Context getJndiContext() {
		
		Context ctx;
		try {
			Properties properties = new Properties();
			properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
			properties.put("java.naming.provider.url", "jnp://" + getServer() + ":1099");
			ctx = new InitialContext(properties);
			
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
		return ctx;
	}

	/**
	 * This method returns a remote BaseDao from JNDI. Following assumptions are made:
	 * <ul>
	 * <li>DAO interface is remote</li>
	 * <li>It follows naming convention of remote interface <code>SomeDao</code>
	 * with implementation <code>SomeDaoImpl</code></li>
	 * </ul>
	 * 
	 * @param daoClass
	 * @return the concrete BaseDao instance
	 */
	public BaseDao<?> lookupBean(Class<? extends BaseDao<?>> daoClass) {
		try {

			Object dao = getJndiContext().lookup(Globals.EAR_NAME + "/" + daoClass.getSimpleName() + "Impl/remote");
			
			return (BaseDao<?>)dao;

		} catch (NamingException ne) {
			throw new RuntimeException(ne);
		}
	}

	public String getServer() {
		return server;
	}

	/**
	 * Use this method to override the default JNDI server of "localhost". E.g.
	 * the IP address "192.168.16.183" or domain name
	 * <p>
	 * 
	 * @param server
	 *            the new server name
	 */
	public void setServer(String server) {
		this.server = server;
	}


	/**
	 * Example usage
	 */
	public static void main(String[] args) {

		JndiInvoker invoker = new JndiInvoker();

		UserDao dao = (UserDao) invoker.lookupBean(UserDao.class);

		System.out.println(dao);
		System.out.println(dao.list());

		// // create
		// User u = new User();
		// u.setFirstName("First name");
		// u.setLastName("Last name");
		// u.setBirthday(new Date());
		// System.out.println(dao.create(u));

	}
}