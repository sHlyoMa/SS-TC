package service;

import static org.junit.Assert.*;

import java.util.List;


import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import dao.IssueMapper;
import dao.ProjectMapper;
import dao.UserInfoMapper;
import dao.UserMapper;
import domain.Issue;
import domain.Project;
import domain.User;
import domain.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class IssueServiceImplTest {
	
	@Autowired
	private IssueService issueService;
	@Autowired
	private UserService userService;
	@Autowired
	private IssueMapper issueMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	private static final Logger LOGGER = Logger.getLogger(IssueServiceImplTest.class);
	
	private Issue issue;
	private static Project project = new Project();
	private static User user = new User();
	private static UserInfo userInfo = new UserInfo();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.info("Test IssueService class");
		
		user.setLogin("login-test");
		user.setPassword("pass-test");
		user.setRoleId(1);
		user.setUserInfo(userInfo);
		
		userInfo.setFirstName("firstName");
		userInfo.setSecondName("SecondName");
		userInfo.setEmail("email@mail.com");
		
		project.setName("test project");
		project.setDescription("Test project Description");
		
		Authentication auth = new UsernamePasswordAuthenticationToken("login-test","pass-test", AuthorityUtils.createAuthorityList("ROLE_USER"));
		SecurityContextHolder.getContext().setAuthentication(auth);
		
	}
	
	@Before
	public void setUp(){
		issue = new Issue();	
		issue.setName("Test-Name");
		issue.setDescription("Test-Description");
		
		issue.setStatusId(1);
		issue.setPriorityId(1);
		issue.setTypeId(1);
		issue.setEnvironmentId(1);
		
		try{
			userMapper.addUser(user);
			userInfoMapper.addUserInfo(user);
			user.setUserId(userMapper.getUserId(user));
			project.setPmUserId(user.getUserId());
			projectMapper.addProject(project);
			project.setProjectId(projectMapper.getProjectIdByProject(project));
			issue.setReporterId(user.getUserId());
			issue.setProjectId(project.getProjectId());
			issue.setKey("T-"+issue.getProjectId()+"-"+1);
			issueMapper.addIssue(issue);
			issue.setIssueId(issueMapper.getIssueIdByIssue(issue));
			}catch (Exception e){
				LOGGER.error("Can`t create testing data in db", e);
			}
	}
	
	@After
	public void teardown(){
		try {
			projectMapper.deleteProject(project.getProjectId());
			issueMapper.removeIssues(issue.getIssueId());
			userInfoMapper.deleteUserInfo(user.getUserId());
			userMapper.removeUser(user.getUserId());
			issue=null;
		} catch (Exception e) {
			LOGGER.error("can`t delete testing data");
		}
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		user=null;
		userInfo=null;
		project=null;
	}
	
	@Test
	public void testGetCountOfProjectIssue() {
		
		try{
			Integer count = issueService.getCountOfProjectIssue(issue.getProjectId(), issue.getTypeId(), issue.getStatusId(), issue.getPriorityId(), issue.getEnvironmentId(), null, null);
			assertNotNull(count);
			assertEquals(new Integer(1), count);
		}catch (Exception e){
			LOGGER.error("Can`t get count of project issue from db", e);
		}
	}
	
	
	@Test
	public void testGetIssueById() {
		try{
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertNotNull(issueTest);
		}catch (Exception e){
			LOGGER.error("Can`t get issue by id from db", e);
		}
	}


	@Test
	public void testUpdateIssue() {
		
		issue.setName("Test-Name-1");
		issue.setDescription("Test-Description-1");
		issue.setAssignerId(user.getUserId());
		issue.setStatusId(2);
		issue.setPriorityId(2);
		issue.setTypeId(2);
		issue.setEnvironmentId(2);
		issue.setProjectId(project.getProjectId()+1);
		
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertNotNull(issueTest);
			assertNotNull(issueTest.getKey());
			assertNotNull(issueTest.getUpdated());
			assertNotNull(issueTest.getCreated());
			assertNull(issueTest.getCloseDate());
			assertEquals(issue.getName(), issueTest.getName());
			assertEquals(issue.getDescription(), issueTest.getDescription());
			assertEquals(issue.getAssignerId(), issueTest.getAssignerId());
			assertEquals(issue.getStatusId(), issueTest.getStatusId());
			assertNotEquals(issue.getTypeId(), issueTest.getTypeId());
			assertEquals(issue.getPriorityId(), issueTest.getPriorityId());
			assertEquals(issue.getProjectId(), issueTest.getProjectId());
		
		}catch (Exception e){
			LOGGER.error("Can`t update issue", e);
		}
	}

	@Test
	public void testGetIssueDetails() {
		
		issue.setName("Test-Name-1");
		issue.setDescription("Test-Description-1");
		issue.setAssignerId(user.getUserId());
		issue.setStatusId(2);
		issue.setPriorityId(2);
		issue.setTypeId(2);
		issue.setEnvironmentId(2);
		
		Issue issueTest = new Issue();
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			issueTest = issueService.getIssueDetails(issue.getIssueId());
			
		}catch (Exception e){
			LOGGER.error("Can`t get issue details", e);
		}
		
		assertNotNull(issueTest);
		assertNotNull(issueTest.getKey());
		assertNotNull(issueTest.getUpdated());
		assertNotNull(issueTest.getCreated());
		assertNull(issueTest.getCloseDate());
		assertEquals(issue.getName(), issueTest.getName());
		assertEquals(issue.getDescription(), issueTest.getDescription());
		assertEquals(issue.getReporterId(), issueTest.getReporterId());
		assertEquals(issue.getAssignerId(), issueTest.getAssignerId());
		assertEquals(issue.getStatusId(), issueTest.getStatusId());
		assertNotEquals(issue.getTypeId(), issueTest.getTypeId());
		assertEquals(issue.getPriorityId(), issueTest.getPriorityId());
		assertEquals(issue.getEnvironmentId(), issueTest.getEnvironmentId());
		assertEquals(issue.getProjectId(), issueTest.getProjectId());
		assertEquals("Task", issueTest.getType().getType());
		assertEquals("In Progress", issueTest.getStatus().getStatus());
		assertEquals("Blocker", issueTest.getPriority().getPriority());
		assertEquals("Development", issueTest.getEnvironment().getEnvironment());
		assertEquals(user.getUserInfo().getFirstName(), issueTest.getUserReporter().getFirstName());
		assertEquals(user.getUserInfo().getSecondName(), issueTest.getUserReporter().getSecondName());
		assertEquals(user.getUserInfo().getEmail(), issueTest.getUserReporter().getEmail());
		assertEquals(project.getName(), issueTest.getProject().getName());
		assertEquals(user.getUserInfo().getFirstName(), issueTest.getUserAssigner().getFirstName());
		assertEquals(user.getUserInfo().getSecondName(), issueTest.getUserAssigner().getSecondName());
		assertEquals(user.getUserInfo().getEmail(), issueTest.getUserAssigner().getEmail());
	}
	
	@Test
	public void testRemoveIssue() {
		try{
		Issue issueTest = issueService.getIssueById(issue.getIssueId());
		assertNotNull(issueTest);
		issueService.removeIssue(issue.getIssueId());
		Issue deletedIssue = issueService.getIssueById(issue.getIssueId());
		assertNull(deletedIssue);
		}catch (Exception e){
			LOGGER.error("Can`t remove issue by id", e);
		}
	}

	@Test
	public void testGetIssueByProjectId() {
			try{
				List<Issue> issueTest = issueService.getIssueByProjectId(issue.getProjectId(), issue.getTypeId(), issue.getStatusId(), issue.getPriorityId(), issue.getEnvironmentId(),null, null, 1, 1, 1, 1);
				assertNotNull(issueTest);
				assertEquals(issue.getProjectId(), issueTest.get(0).getProjectId());
			}catch (Exception e){
				LOGGER.error("Can`t get Issue by project id", e);
			}
	
	}
	
	@Test
	public void testAssignToSomeBody() {
		try{
			assertNull(issueService.getIssueById(issue.getIssueId()).getAssignerId());
			assertEquals(new Integer(1), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.assignToSomeBody(issue.getIssueId(), user.getUserId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertNotNull(issueTest.getAssignerId());
			assertEquals(user.getUserId(), issueTest.getAssignerId());
		}catch (Exception e){
			LOGGER.error("Can`t assign issue to somebody", e);
		}
	}
	
	@Test
	public void testStartProgress() {
		try{
			assertEquals(new Integer(1), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.startProgress(issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertEquals(new Integer(2), issueTest.getStatusId());
		}catch (Exception e){
			LOGGER.error("Can`t change status in startProgress method", e);
		}
	}
	
	@Test
	public void testResolveIssue() {
		issue.setStatusId(2);
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			assertEquals(new Integer(2), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.resolveIssue(issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertEquals(new Integer(3), issueTest.getStatusId());
		}catch (Exception e){
			LOGGER.error("Can`t change status in resolveIssue method", e);
		}
	}
	
	@Test
	public void testCloseIssue() {
		issue.setStatusId(3);
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			assertEquals(new Integer(3), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.closeIssue(issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertEquals(new Integer(4), issueTest.getStatusId());
			assertNotNull(issueTest.getCloseDate());
		}catch (Exception e){
			LOGGER.error("Can`t change status in closeIssue method", e);
		}
	}
	
	@Test
	public void testReopenIssue() {
		issue.setStatusId(4);
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			assertEquals(new Integer(4), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.reopenIssue(issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertEquals(new Integer(5), issueTest.getStatusId());
			assertNull(issueTest.getAssignerId());
			assertNull(issueTest.getCloseDate());
		}catch (Exception e){
			LOGGER.error("Can`t change status in reopenIssue method", e);
		}
	}
	
	@Test
	public void testGetCountOfMyIssue() {
		issue.setAssignerId(user.getUserId());
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			Integer count = issueService.getCountOfMyIssue(issue.getTypeId(), issue.getStatusId(), issue.getPriorityId(), issue.getEnvironmentId(),null,null);
			assertNotNull(count);
			assertEquals(new Integer(1), count);
		}catch (Exception e){
			LOGGER.error("Can`t get count of my issue from db", e);
		}
	}
	
	@Test
	public void testGetMyIssueList() {
		issue.setAssignerId(user.getUserId());
		try{
			issueService.updateIssue(issue, issue.getIssueId());
			List<Issue> issueTest = issueService.getMyIssueList(issue.getTypeId(), issue.getStatusId(), issue.getPriorityId(), issue.getEnvironmentId(),null,null, 1, 1, 1, 1);
			assertNotNull(issueTest);
			assertEquals(issue.getAssignerId(), issueTest.get(0).getAssignerId());
		}catch (Exception e){
			LOGGER.error("Can`t get my Issue list", e);
		}
	}
	
	@Test
	public void testAssignToMe() {
		try{
			assertNull(issueService.getIssueById(issue.getIssueId()).getAssignerId());
			assertEquals(new Integer(1), issueService.getIssueById(issue.getIssueId()).getStatusId());
			issueService.assignToMe(issue.getIssueId());
			Issue issueTest = issueService.getIssueById(issue.getIssueId());
			assertNotNull(issueTest.getAssignerId());
			assertEquals(user.getUserId(), issueTest.getAssignerId());
			assertEquals(userService.getUserByLogin().getUserId(), issueTest.getAssignerId());
		}catch (Exception e){
			LOGGER.error("Can`t assign issue to yourself", e);
		}
	}
	
	@Test
	public void testAddIssue() throws Exception {
		issue.setName("add-test");
		issue.setDescription("add-description-test");
		issue.setAssignerId(user.getUserId());
		try {
			issueService.addIssue(issue);
			Integer issueId = issueMapper.getIssueIdByIssue(issue);
			Issue issueTest = issueService.getIssueById(issueId);
			assertNotNull(issueTest);
			assertNotNull(issueTest.getIssueId());
			assertNotNull(issueTest.getName());
			assertNotNull(issueTest.getDescription());
			assertNotNull(issueTest.getKey());
			assertNotNull(issueTest.getReporterId());
			assertNotNull(issueTest.getTypeId());
			assertNotNull(issueTest.getStatusId());
			assertNotNull(issueTest.getPriorityId());
			assertNotNull(issueTest.getEnvironmentId());
			assertNotNull(issueTest.getProjectId());
			assertNotNull(issueTest.getCreated());
			assertNotNull(issueTest.getAssignerId());
			assertNull(issueTest.getUpdated());
			assertNull(issueTest.getCloseDate());
			issueService.removeIssue(issueId);
			
		} catch (Exception e) {
			LOGGER.error("Can`t add issue to db", e);
		}
	}
}
