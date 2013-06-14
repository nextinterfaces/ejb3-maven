package org.roussev.persistence;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.roussev.app.entity.User;

/**
 * This class contains UserDao tests.
 * <p>
 * 
 * @author Atanas Roussev
 */
public class UserDaoTest extends BaseDaoTest {

	private User user = new User();

	@Override
	public void onSetUp() {
		user = userDao.create(user);
	}

	@Override
	public void onTearDown() {
		userDao.delete(user);
	}

	@Test
	public void list() {
		Collection<User> list = userDao.list();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void read() {
		User u = userDao.read(user.getId());
		Assert.assertTrue(u != null);
	}

	@Test
	public void update() {

		String sampleName = "test-name";

		User u = userDao.read(user.getId());
		u.setLastName(sampleName);

		userDao.update(u);

		User u2 = userDao.read(user.getId());

		Assert.assertTrue(sampleName.equals(u2.getLastName()));
	}

	@Test
	public void createDelete() {
		User u = new User();

		u = userDao.create(u);
		Assert.assertTrue(u != null);
		Assert.assertTrue(u.getId() != null);

		userDao.delete(u);
		u = userDao.read(u.getId());
		Assert.assertTrue(u == null);
	}

}