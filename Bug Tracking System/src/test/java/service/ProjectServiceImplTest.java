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

import dao.ProjectMapper;
import dao.UserMapper;
import dao.UserInfoMapper;
import domain.Project;
import domain.User;
import domain.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class ProjectServiceImplTest {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserMapper userMapper;
	
	private static final Logger LOGGER = Logger.getLogger(ProjectServiceImplTest.class);
	
	private Project project;
	private static User pmUser = new User();
	private static UserInfo pmUserInfo = new UserInfo();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.info("Test ProjectService class");
		
		pmUser.setLogin("pm-login-test");
		pmUser.setPassword("pm-pass-test");
		pmUser.setRoleId(2);
		pmUser.setUserInfo(pmUserInfo);
		pmUserInfo.setFirstName("pm-FirstName");
		pmUserInfo.setSecondName("pm-SecondName");
		pmUserInfo.setEmail("pm-email@mail.com");
		
		Authentication auth = new UsernamePasswordAuthenticationToken("login-test","pass-test", AuthorityUtils.createAuthorityList("ROLE_PM"));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Before
	public void setUp() throws Exception {
		project = new Project();	
		project.setName("Test-Name");
		project.setDescription("Test-Description");
			
		try {
			userMapper.addUser(pmUser);
			userInfoMapper.addUserInfo(pmUser);
			pmUser.setUserId(userMapper.getUserId(pmUser));
			project.setPmUserId(pmUser.getUserId());
			projectMapper.addProject(project);
			project.setProjectId(projectMapper.getProjectIdByProject(project));			
		} catch (Exception e) {
			LOGGER.error("Can not create testing data in db. ", e);
		}
	}

	@After
	public void tearDown() throws Exception {
		try {
			projectMapper.deleteProject(project.getProjectId());
			userInfoMapper.deleteUserInfo(pmUser.getUserId());
			userMapper.removeUser(pmUser.getUserId());
			project = null;
		} catch (Exception e) {
			LOGGER.error("Can not delete testing data. ");
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pmUser = null;
		pmUserInfo = null;
	}
	
	
	@Test
	public void testGetProjectById() {
		try {
			Project projectTest = projectService.getProjectById(project.getProjectId());
			assertNotNull(projectTest);
		} catch (Exception e) {
			LOGGER.error("Can not get project by id from db. ", e);
		}
	}
	
	@Test
	public void testGetProjectNameByProjectId() {
		try {
			String projectNameTest = projectService.getProjectNameByProjectId(project.getProjectId());
			assertNotNull(projectNameTest);
		} catch (Exception e) {
			LOGGER.error("Can not get project name by project id from db. ", e);
		}
	}
	
	@Test
	public void testGetProjectIdByProject() {
		try {
			Integer projectIdTest = projectService.getProjectIdByProject(project);
			assertNotNull(projectIdTest);
		} catch (Exception e) {
			LOGGER.error("Can not get project id by project from db. ", e);
		}
	}
	
	@Test
	public void projectList() {
		try {
			List<Project> projectListTest = projectService.projectList();
			assertNotNull(projectListTest);
			assertEquals(project.getProjectId(), projectListTest.get(0).getProjectId());
		} catch (Exception e) {
			LOGGER.error("Can not get project by project id. ", e);
		}
	
	}
	
	@Test
	public void testDeleteProject() {
		try {
			Project projectTest = projectService.getProjectById(project.getProjectId());
			assertNotNull(projectTest);
			projectService.deleteProject(project.getProjectId());
			Project deletedProject = projectService.getProjectById(project.getProjectId());
			assertNull(deletedProject);
		} catch (Exception e) {
			LOGGER.error("Can not remove project by id. ", e);
		}
	}

}
