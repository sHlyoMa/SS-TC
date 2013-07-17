package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRoleAction {
	RoleAction roleAction = new RoleAction();

	@Before
	public void setUp() throws Exception {
		roleAction.setActionId(1);
		roleAction.setRoleId(2);
	}

	@After
	public void tearDown() throws Exception {
		roleAction = null;
	}

	@Test
	public void testGetActionId() {
		assertEquals(new Integer(1), roleAction.getActionId());
	}
	
	@Test
	public void testGetRoleId() {
		assertEquals(new Integer(2), roleAction.getRoleId());
	}

}
