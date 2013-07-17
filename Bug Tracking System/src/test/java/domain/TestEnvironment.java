package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEnvironment {
	Environment environment = new Environment();

	@Before
	public void setUp() throws Exception {
		environment.setEnvironmentId(1);
		environment.setEnvironment("TestEnvironment");	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEnvironmentId() {
		assertEquals(new Integer(1), environment.getEnvironmentId());
	}
	
	@Test
	public void testGetEnvironment() {
		assertEquals("TestEnvironment", environment.getEnvironment());
	}

}
