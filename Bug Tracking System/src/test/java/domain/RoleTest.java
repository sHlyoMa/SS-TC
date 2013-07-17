package domain;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoleTest {
	Role role = new Role();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		role.setRoleName("roleName");
		role.setRoleId(1);
		List<Role> roleList = new LinkedList<Role>();
		role.setRoleList(roleList);
	}

	@After
	public void tearDown() throws Exception {
		 role = null;
	}

	
	@Test
	public void testGetRoleList() {
		List<Role> roleList = new LinkedList<Role>();
		assertEquals (roleList, role.getRoleList());
	}

	@Test
	public void testSetRoleList() {
		List<Role> roleList = new LinkedList<Role>();
		assertEquals(roleList, role.getRoleList() );
	}

	@Test
	public void testGetId() {
		assertEquals (role.getRoleId(), new Integer (1));
	}

	@Test
	public void testSetId() {
		assertEquals (new Integer (1), role.getRoleId());
	}

	@Test
	public void testGetRoleName() {
		assertEquals (role.getRoleName(), "roleName");
	}

	@Test
	public void testSetRoleName() {
		assertEquals ("roleName",role.getRoleName());
	}

}
