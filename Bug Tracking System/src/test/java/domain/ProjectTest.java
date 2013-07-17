package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	Project project = new Project();
	
	@Before
	public void setUp() throws Exception{
		project.setProjectId(1);
		project.setName("Test project name");
		project.setDescription("Test project description");
		project.setPmUserId(2);
	}
	
	@After
	public void tearDown() throws Exception {
		project = null;
	}
	
	@Test
	public void testGetProjectId() {
		assertEquals(new Integer(1), project.getProjectId());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Test project name", project.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("Test project description", project.getDescription());
	}
	
	@Test
	public void testGetPmUserID() {
		assertEquals(new Integer(2), project.getPmUserId());
	}

}
