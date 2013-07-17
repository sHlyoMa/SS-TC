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
import dao.UserProjectMapper;
import domain.Project;
import domain.User;
import domain.UserProject;
import domain.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class UserProjectServiceImplTest {
	@Autowired
	UserProjectService userProjectService;
	@Autowired
	UserProjectMapper userProjectMapper;
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserMapper userMapper;
	
	private static final Logger LOGGER = Logger.getLogger(UserProjectServiceImplTest.class);
		
	private UserProject devUserProject;
	private UserProject qcUserProject;
	private UserProject poUserProject;
	private static Project project = new Project();
	private static User devUser = new User();
	private static UserInfo devUserInfo = new UserInfo();
	private static User qcUser = new User();
	private static UserInfo qcUserInfo = new UserInfo();
	private static User pmUser = new User();
	private static UserInfo pmUserInfo = new UserInfo();
	private static User poUser = new User();
	private static UserInfo poUserInfo = new UserInfo();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.info("Test UserProjectService class");
		
		project.setName("Test-Name");
		project.setDescription("Test-Description");
		
		pmUser.setLogin("pm-login-test");
		pmUser.setPassword("pm-pass-test");
		pmUser.setRoleId(2);
		pmUser.setUserInfo(pmUserInfo);
		pmUserInfo.setFirstName("pm-FirstName");
		pmUserInfo.setSecondName("pm-SecondName");
		pmUserInfo.setEmail("pm-email@mail.com");
		
		devUser.setLogin("dev-login-test");
		devUser.setPassword("dev-pass-test");
		devUser.setRoleId(4);
		devUser.setUserInfo(devUserInfo);
		devUserInfo.setFirstName("dev-FirstName");
		devUserInfo.setSecondName("dev-SecondName");
		devUserInfo.setEmail("dev-email@mail.com");
		
		qcUser.setLogin("qc-login-test");
		qcUser.setPassword("qc-pass-test");
		qcUser.setRoleId(4);
		qcUser.setUserInfo(qcUserInfo);
		qcUserInfo.setFirstName("qc-FirstName");
		qcUserInfo.setSecondName("qc-SecondName");
		qcUserInfo.setEmail("qc-email@mail.com");
		
		poUser.setLogin("po-login-test");
		poUser.setPassword("po-pass-test");
		poUser.setRoleId(3);
		poUser.setUserInfo(poUserInfo);
		poUserInfo.setFirstName("po-FirstName");
		poUserInfo.setSecondName("po-SecondName");
		poUserInfo.setEmail("po-email@mail.com");
		
		Authentication auth = new UsernamePasswordAuthenticationToken("login-test","pass-test", AuthorityUtils.createAuthorityList("ROLE_PM"));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Before
	public void setUp() throws Exception {
		devUserProject = new UserProject();
		qcUserProject = new UserProject();
		poUserProject = new UserProject();
		
		try {
			userMapper.addUser(pmUser);
			userInfoMapper.addUserInfo(pmUser);
			pmUser.setUserId(userMapper.getUserId(pmUser));
			
			project.setPmUserId(pmUser.getUserId());
			projectMapper.addProject(project);
			project.setProjectId(projectMapper.getProjectIdByProject(project));
			
			
			userMapper.addUser(devUser);
			userInfoMapper.addUserInfo(devUser);
			devUser.setUserId(userMapper.getUserId(devUser));
			
			devUserProject.setUserId(devUser.getUserId());
			devUserProject.setProjectId(project.getProjectId());
			userProjectMapper.addUserProject(devUserProject);
			devUserProject.setUserProjectId(userProjectMapper.getUserProjectIdByUserProject(devUserProject));
			
			
			userMapper.addUser(qcUser);
			userInfoMapper.addUserInfo(qcUser);
			qcUser.setUserId(userMapper.getUserId(qcUser));
			
			qcUserProject.setUserId(qcUser.getUserId());
			qcUserProject.setProjectId(project.getProjectId());
			userProjectMapper.addUserProject(qcUserProject);
			qcUserProject.setUserProjectId(userProjectMapper.getUserProjectIdByUserProject(qcUserProject));
			
			
			userMapper.addUser(poUser);
			userInfoMapper.addUserInfo(poUser);
			poUser.setUserId(userMapper.getUserId(poUser));
			
			poUserProject.setUserId(poUser.getUserId());
			poUserProject.setProjectId(project.getProjectId());
			userProjectMapper.addUserProject(poUserProject);
			poUserProject.setUserProjectId(userProjectMapper.getUserProjectIdByUserProject(poUserProject));
			
		} catch (Exception e) {
			LOGGER.error("Can not create testing data in db. ", e);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		try {
			
			userProjectMapper.deleteUserProject(devUserProject.getUserProjectId());
			userProjectMapper.deleteUserProject(qcUserProject.getUserProjectId());
			userProjectMapper.deleteUserProject(poUserProject.getUserProjectId());
			
			projectMapper.deleteProject(project.getProjectId());
			
			userInfoMapper.deleteUserInfo(pmUser.getUserId());
			userMapper.removeUser(pmUser.getUserId());
			
			userInfoMapper.deleteUserInfo(devUser.getUserId());
			userMapper.removeUser(devUser.getUserId());
			
			userInfoMapper.deleteUserInfo(qcUser.getUserId());
			userMapper.removeUser(qcUser.getUserId());
			
			userInfoMapper.deleteUserInfo(poUser.getUserId());
			userMapper.removeUser(poUser.getUserId());
			
			devUserProject = null;
			qcUserProject = null;
			poUserProject = null;
		} catch (Exception e) {
			LOGGER.error("Can not delete testing data. ");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pmUser = null;
		pmUserInfo = null;
		devUser = null;
		devUserInfo = null;
		qcUser = null;
		qcUserInfo = null;
		poUser = null;
		poUserInfo = null;
		project = null;
	}

	@Test
	public void getUserProjectByUserProjectId() {
		try{
			UserProject userProjectTest = userProjectService.getUserProjectByUserProjectId(devUserProject.getUserProjectId());
			assertNotNull(userProjectTest);
		}catch (Exception e){
			LOGGER.error("Can not get userProject by id from db", e);
		}
	}
	
	@Test
	public void getUserProjectIdsByProjectId() {
		try{
			List<UserProject> userProjectIdListTest = userProjectService.userProjectIdsByProjectId(project.getProjectId());
			assertNotNull(userProjectIdListTest);
		}catch (Exception e){
			LOGGER.error("Can not get userProjectId list list by project id from db", e);
		}
	}
	
	@Test
	public void getUserProjectListByProjectId() {
		try{
			List<UserProject> userProjectListTest = userProjectService.userProjectListByProjectId(project.getProjectId());
			assertNotNull(userProjectListTest);
		}catch (Exception e){
			LOGGER.error("Can not get userProject list by project id from db", e);
		}
	}

	@Test
	public void testDeleteUserProject() {
		try {
			UserProject userProjectTest = userProjectService.getUserProjectByUserProject(qcUserProject);
			assertNotNull(userProjectTest);
			userProjectService.deleteUserProject(qcUserProject.getUserProjectId());
			UserProject deletedUserProject = userProjectService.getUserProjectByUserProject(qcUserProject);
			assertNull(deletedUserProject);
		}catch (Exception e){
			LOGGER.error("Can not remove userProject by id. ", e);
		}
	}

	@Test
	public void testAddUserProject() throws Exception {
		devUserProject.setUserId(1);
		devUserProject.setProjectId(2);
		try {
			userProjectService.addUserProject(devUserProject);
			Integer userProjectId = userProjectMapper.getUserProjectIdByUserProject(devUserProject);
			UserProject userProjectTest = userProjectService.getUserProjectByUserProjectId(userProjectId);
			assertNotNull(userProjectTest);
			assertNotNull(userProjectTest.getUserProjectId());
			assertNotNull(userProjectTest.getUserId());
			assertNotNull(userProjectTest.getProjectId());
			userProjectService.deleteUserProject(userProjectId);
		} catch (Exception e) {
			LOGGER.error("Can not add userProject to db", e);
		}
	}
	
}