package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StatusTest {
	Status status = new Status();
	
	@Before
	public void setUp() throws Exception {
		status.setStatus("TestStatus");
		status.setStatusId(1);
	}

	@After
	public void tearDown() throws Exception {
		status = null;
	}

	@Test
	public void testGetStatusId() {
		assertEquals(new Integer(1), status.getStatusId());
	}
	
	@Test
	public void testGetStatus() {
		assertEquals("TestStatus", status.getStatus());
	}

}
