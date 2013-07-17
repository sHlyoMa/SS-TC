package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommentTest {
	Comment comment = new Comment();
	//UserInfo userName = new UserInfo();
	
	@Before
	public void setUp() throws Exception{
		comment.setCommentId(1);
		comment.setIssueId(2);
		comment.setUserId(3);
		comment.setValue("Test comment value");
		//comment.setUserName(userName);
	}
	
	@After
	public void tearDown() throws Exception {
		comment = null;
	}
	
	@Test
	public void testUserId() {
		assertEquals(new Integer(1), comment.getCommentId());
	}
	
	@Test
	public void testGetIssueId() {
		assertEquals(new Integer(2), comment.getIssueId());
	}
	
	@Test
	public void testGetUserId() {
		assertEquals(new Integer(3), comment.getUserId());
	}
	
	@Test
	public void testGetValue() {
		assertEquals("Test comment value", comment.getValue());
	}

}
