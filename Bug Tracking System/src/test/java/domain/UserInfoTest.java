package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest {
	UserInfo userInfo = new UserInfo();
	
	@Before
	public void setUp() throws Exception {
		userInfo.setUserInfoId(1);
		userInfo.setEmail("test@email.com");
		userInfo.setFirstName("TestFirstName");
		userInfo.setSecondName("TestSecondName");
		userInfo.setRoleId(2);
	}

	@After
	public void tearDown() throws Exception {
		userInfo = null;
	}

	@Test
	public void testGetUserInfoId(){
		assertEquals(new Integer(1), userInfo.getUserInfoId());
	}
	
	@Test
	public void testGetEmail(){
		assertEquals("test@email.com", userInfo.getEmail());
	}
	
	@Test
	public void testGetFirstName(){
		assertEquals("TestFirstName", userInfo.getFirstName());
	}
	
	@Test
	public void testGetSecondtName(){
		assertEquals("TestSecondName", userInfo.getSecondName());
	}
	
	@Test
	public void testGetRoleId(){
		assertEquals(new Integer(2), userInfo.getRoleId());
	}

}
