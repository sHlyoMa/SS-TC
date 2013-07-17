package service;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.UserMapper;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class UserServiceImplTest {
	@Autowired	
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImplTest.class);
	private static User user = new User();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.info("Test UserService class");
		user.setLogin("login-test1");
		user.setPassword("pass-test1");
		
	}

	@Before
	public void setUp() throws Exception {
		try{
		userMapper.addUser(user);
		}catch (Exception e){
			LOGGER.error("Can`t create testing data in db", e);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		userMapper.removeUser(user.getUserId());
		user=null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	@Ignore
	@Test
	public void getId() {
		try{
			
			Integer id = userService.getUserIdByName("admin1");
			assertNotNull(id);
			assertEquals(new Integer(17), id);
			assertNotNull (userService.getUserIdByName("login-test1"));
		}catch (Exception e){
			LOGGER.error("Can`t get ID from db", e);
		}
	}

}
