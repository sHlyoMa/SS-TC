package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAction {
	Action action = new Action();
	
	@Before
	public void setUp() throws Exception {
		action.setActionId(1);
		action.setAction("TestAction");
	}
	
	@After
	public void tearDown() throws Exception {
		action = null;
	}

	@Test
	public void testGetActionId() {
		assertEquals(new Integer(1), action.getActionId());
	}
	
	@Test
	public void testGetAction() {
		assertEquals("TestAction", action.getAction());
	}

}
