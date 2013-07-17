package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserProjectTest {
	UserProject userProject = new UserProject();
	
	@Before
	public void setUp() throws Exception{
		userProject.setUserProjectId(1);
		userProject.setUserId(2);
		userProject.setProjectId(3);
		//project.setUserProject(userProject);
	}
	
	@After
	public void tearDown() throws Exception {
		userProject = null;
	}
	
	@Test
	public void testGetUserProjectId() {
		assertEquals(new Integer(1), userProject.getUserProjectId());
	}
	
	@Test
	public void testGetUserId() {
		assertEquals(new Integer(2), userProject.getUserId());
	}
	
	@Test
	public void testGetProjectId() {
		assertEquals(new Integer(3), userProject.getProjectId());
	}

}
