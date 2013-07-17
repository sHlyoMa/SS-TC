package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User user = new User();
	Role role = new Role();

	@Before
	public void setUp() throws Exception {
		user.setLogin("LoginUserTest");
		user.setPassword("pass1234test");
		user.setRoleId(1);	
		user.setUserId(1);
		
	}

	@After
	public void tearDown() throws Exception {
		user=null;
	}

	@Test
	public void testGetRole() {
		assertEquals (null, user.getRole());
	}

	@Test
	public void testSetRole() {
		assertEquals (null, user.getRole());
	}

	@Test
	public void testGetUserId() {
		assertEquals (new Integer (1), user.getUserId());
	}

	@Test
	public void testSetUserId() {
		assertEquals (new Integer (1) ,user.getUserId());
	}

	@Test
	public void testGetLogin() {
		assertEquals("LoginUserTest", user.getLogin());
	}

	@Test
	public void testSetLogin() {
		assertEquals("LoginUserTest", user.getLogin());
	}

	@Test
	public void testGetPassword() {
		assertEquals ("pass1234test", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		assertEquals ("pass1234test", user.getPassword());
	}

	@Test
	public void testGetRoleId() {
		assertEquals(new Integer(1), user.getRoleId());
	}

	@Test
	public void testSetRoleId() {
		assertEquals(new Integer(1), user.getRoleId());
	}

	@Test
	public void testGetUserInfo() {
		assertEquals(null, user.getUserInfo());
	}

	@Test
	public void testSetUserInfo() {
		assertEquals(null, user.getUserInfo());
	}

}
