package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IssueTest {
	Issue issue = new Issue();
	Priority priority = new Priority();

	@Before
	public void setUp() throws Exception {
		issue.setIssueId(1);
		issue.setAssignerId(1);
		issue.setReporterId(2);
		issue.setStatusId(3);
		issue.setTypeId(4);
		issue.setPriorityId(2);
		issue.setEnvironmentId(2);
		issue.setProjectId(3);
		issue.setDescription("Description");
		issue.setKey("B-1-4");
		issue.setName("Name");
	}

	@After
	public void tearDown() throws Exception {
		issue = null;
	}

	@Test
	public void testGetIssueId() {
		assertEquals(new Integer(1), issue.getIssueId());
	}

	@Test
	public void testGetAssignerId() {
		assertEquals(new Integer(1), issue.getAssignerId());
	}

	@Test
	public void testGetReporterId() {
		assertEquals(new Integer(2), issue.getReporterId());
	}

	@Test
	public void testGetTypeId() {
		assertEquals(new Integer(4), issue.getTypeId());
	}

	@Test
	public void testGetStatusId() {
		assertEquals(new Integer(3), issue.getStatusId());
	}

	@Test
	public void testGetPriorityId() {
		assertEquals(new Integer(2), issue.getPriorityId());
	}

	@Test
	public void testGetProjectId() {
		assertEquals(new Integer(3), issue.getProjectId());
	}

	@Test
	public void testGetName() {
		assertEquals("Name", issue.getName());
	}

	@Test
	public void testGetKey() {
		assertEquals("B-1-4", issue.getKey());
	}

	@Test
	public void testGetDescription() {
		assertEquals("Description", issue.getDescription());
	}

	@Test
	public void testGetEnvironmentId() {
		assertEquals(new Integer(2), issue.getEnvironmentId());
	}

}
