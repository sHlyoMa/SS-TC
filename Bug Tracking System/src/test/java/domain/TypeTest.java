package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TypeTest {
	Type type = new Type();

	@Before
	public void setUp() throws Exception {
		type.setTypeId(1);
		type.setType("TestType");
	}

	@After
	public void tearDown() throws Exception {
		type = null;
	}

	@Test
	public void testGetTypeId() {
		assertEquals(new Integer(1), type.getTypeId());
	}
	
	@Test
	public void testGetType() {
		assertEquals("TestType", type.getType());
	}

}
