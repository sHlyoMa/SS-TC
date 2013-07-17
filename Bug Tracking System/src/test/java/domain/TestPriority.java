package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPriority {
	Priority priority = new Priority();

	@Before
	public void setUp() throws Exception {
		priority.setPriorityId(1);
		priority.setPriority("TestPriority");
	}

	@After
	public void tearDown() throws Exception {
		priority = null;
	}

	@Test
	public void testGetPriorityId() {
		assertEquals(new Integer(1), priority.getPriorityId());
	}
	
	@Test
	public void testGetPriority() {
		assertEquals("TestPriority", priority.getPriority());
	}

}
