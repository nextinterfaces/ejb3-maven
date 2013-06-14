package org.roussev.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.roussev.app.Globals;
import org.roussev.app.dao.UserDao;
import org.roussev.app.dao.UserDaoImpl;

/**
 * This class encapsulates common unit test behaviour.
 * <p>
 * 
 * @author Atanas Roussev
 *
 */
public abstract class BaseDaoTest {

	private static EntityManagerFactory emf;

	/**
	 * The JPA EntityManager to be reused from all derived tests
	 */
	EntityManager em;

	/**
	 * TODO evaluate this approach. The SesisonBeans should be accessed in more
	 * elegant manner from sub-classes
	 */
	UserDao userDao;

	/**
	 * Sub-tests should implement this method to inject custom behaviour
	 * executed from template method "setUp()"
	 */
	abstract protected void onSetUp();

	/**
	 * Sub-tests should implement this method to inject custom behaviour
	 * executed at template method "onTearDown()"
	 */
	abstract protected void onTearDown();

	/**
	 * One time class scope initialization.
	 */
	@BeforeClass
	public final static void oneTimeSetUp() {
		emf = Persistence
				.createEntityManagerFactory(Globals.LOCAL_PERSISTENT_UNIT);
	}

	/**
	 * One time class scope cleanup.
	 */
	@AfterClass
	public final static void oneTimeTearDown() {
		emf.close();
	}

	/**
	 * Invoked before each test method.
	 */
	@Before
	public final void setUp() {
		em = emf.createEntityManager();

		initSessionBeans();

		em.getTransaction().begin();
		// invoking the hook setup method
		onSetUp();
	}

	/**
	 * Invoked after each test method.
	 */
	@After
	public final void tearDown() {
		// invoking the hook tear down method
		onTearDown();

		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method wires all DAO classes with local EntityManager, so the latter
	 * can be within DAO methods
	 */
	private void initSessionBeans() {
		userDao = new UserDaoImpl();
		userDao.setEntityManager(em);
	}

}
