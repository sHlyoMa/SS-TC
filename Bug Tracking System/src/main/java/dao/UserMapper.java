package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.One;

import domain.Role;
import domain.User;
import domain.UserInfo;

@Component
public interface UserMapper {
	String GET_LIST_OF_USERS = "select * from USER as i left join USER_INFO as u on i.USER_ID=u.USER_INFO_ID "
			+ "order by "
			+ " <if test=\"column ==4 and direction ==1\"> LOGIN asc</if>"
			+ " <if test=\"column ==4 and direction == 2\"> LOGIN desc</if>"
			+ " <if test=\"column ==3 and direction ==1\"> ROLE_ID asc</if>"
			+ " <if test=\"column ==3 and direction == 2\"> ROLE_ID desc</if>"
			+ " <if test=\"column ==2 and direction ==1\"> FIRST_NAME asc</if>"
			+ " <if test=\"column ==2 and direction == 2\"> FIRST_NAME desc</if>"
			+ " <if test=\"column ==1 and direction ==1\"> SECOND_NAME asc</if>"
			+ " <if test=\"column ==1 and direction == 2\"> SECOND_NAME desc</if>"
			+ " <if test=\"column ==6 and direction ==1\"> ENABLED asc</if>"
			+ " <if test=\"column ==6 and direction == 2\"> ENABLED desc</if>"
			+ " <if test=\"column ==5 and direction ==1\"> EMAIL asc</if>"
			+ " <if test=\"column ==5 and direction == 2\"> EMAIL desc</if>"
			+ "LIMIT #{page}, #{limit}  ";
	final static String GET_USERS_BY_ROLE = "select * from USER as i left join " +
			"USER_INFO as u on i.USER_ID=u.USER_INFO_ID where ROLE_ID=#{roleId} "
			+ "order by "
			+ " <if test=\"column ==4 and direction ==1\"> LOGIN asc</if>"
			+ " <if test=\"column ==4 and direction == 2\"> LOGIN desc</if>"
			+ " <if test=\"column ==3 and direction ==1\"> ROLE_ID asc</if>"
			+ " <if test=\"column ==3 and direction == 2\"> ROLE_ID desc</if>"
			+ " <if test=\"column ==2 and direction ==1\"> FIRST_NAME asc</if>"
			+ " <if test=\"column ==2 and direction == 2\"> FIRST_NAME desc</if>"
			+ " <if test=\"column ==1 and direction ==1\"> SECOND_NAME asc</if>"
			+ " <if test=\"column ==1 and direction == 2\"> SECOND_NAME desc</if>"
			+ " <if test=\"column ==6 and direction ==1\"> ENABLED asc</if>"
			+ " <if test=\"column ==6 and direction == 2\"> ENABLED desc</if>"
			+ " <if test=\"column ==5 and direction ==1\"> EMAIL asc</if>"
			+ " <if test=\"column ==5 and direction == 2\"> EMAIL desc</if>"
			+ " LIMIT #{page}, #{limit} ";
	String CREATE_USER = "insert into USER (LOGIN, PASSWORD, ROLE_ID, CR_DATE) values (#{login},#{password},#{roleId}, (select now()))";
	String DELETE_USER = "delete from USER where USER_ID=#{userId}";
	String EDIT_USER = "update USER set LOGIN=#{login}, PASSWORD=#{password}, ROLE_ID=#{roleId} where USER_ID =#{userId}";
	String EDIT_USER_SAME_PASS = "update USER set LOGIN=#{login}, ROLE_ID=#{roleId} where USER_ID =#{userId}";
	final static String GET_COUNT_OF_ROLE_USERS = "select count(*) from USER where ROLE_ID=#{roleId}";
	
	@Results(value = {
			@Result(javaType = User.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "login", column = "LOGIN"),
			@Result(property = "password", column = "PASSWORD"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "enabled", column = "ENABLED"),
			@Result(property = "userInfo", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserInfo")),
			@Result(property = "role", column = "ROLE_ID", javaType = Role.class, one = @One(select = "getUserRole")) })
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	@Select(GET_LIST_OF_USERS)
	public List<User> listUser(@Param("page") Integer page,
			@Param("column") Integer column,
			@Param("direction") Integer direction,
			@Param("limit") Integer limit);

	@Results(value = {
	@Result(javaType = User.class),
	@Result(property = "userId", column = "USER_ID"),
	@Result(property = "login", column = "LOGIN"),
	@Result(property = "password", column = "PASSWORD"),
	@Result(property = "roleId", column = "ROLE_ID"),
	@Result(property = "enabled", column = "ENABLED"),
	@Result(property = "userInfo", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserInfo")),
	@Result(property = "role", column = "ROLE_ID", javaType = Role.class, one = @One(select = "getUserRole"))
	})
	@Select(GET_USERS_BY_ROLE)
	public List<User> getUsersByRole(@Param("roleId") Integer roleId,
			@Param("page") Integer page,
			@Param("column") Integer column,
			@Param("direction") Integer direction,
			@Param("limit") Integer limit);
	
	@Results(value = {
			@Result(javaType = User.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "login", column = "LOGIN"),
			@Result(property = "password", column = "PASSWORD"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userInfo", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "setUserInfo")) })
	@Insert(CREATE_USER)
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	public void addUser(User user);

	@Delete(DELETE_USER)
	public void removeUser(Integer userId);

	@Results(value = { @Result(property = "login", column = "LOGIN"),
			@Result(property = "password", column = "PASSWORD"),
			@Result(property = "roleId", column = "ROLE_ID") })
	@Update(EDIT_USER)
	public void editUser(User user);

	@Results(value = {
			@Result(property = "userInfoId", column = "USER_INFO_ID"),
			@Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "secondName", column = "SECOND_NAME"),
			@Result(property = "email", column = "EMAIL"), })
	@Select("select FIRST_NAME, SECOND_NAME, EMAIL from USER_INFO where USER_INFO_ID = #{userId}")
	public UserInfo getUserInfo(Integer id);

	@Results(value = { @Result(property = "roleName", column = "ROLE_NAME") })
	@Select("select ROLE_NAME from ROLE where ID=#{roleId}")
	public Role getUserRole(Integer id);

	@Results(value = {
			@Result(javaType = User.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "login", column = "LOGIN"),
			@Result(property = "password", column = "PASSWORD"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userInfo", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserInfo")) })
	@Select("select * from USER where USER_ID=#{id}")
	User getUserById(Integer id);

	@Insert("insert into USER_INFO (FIRST_NAME, SECOND_NAME, EMAIL) value (#{userInfo.firstName}, #{userInfo.secondName},#{userInfo.email})")
	void setUserInfo(UserInfo userInfo);

	@Select("select USER_ID from USER where LOGIN=#{login} and PASSWORD=#{password}")
	Integer getUserId(User user);

	@Select("select ROLE_ID from USER where LOGIN=#{name}")
	public Integer getUserRoleByName(String name);

	@Results(value = {
			@Result(javaType = User.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "login", column = "LOGIN"),
			@Result(property = "password", column = "PASSWORD"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userInfo", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserInfo")),
			@Result(property = "role", column = "ROLE_ID", javaType = Role.class, one = @One(select = "getUserRole")) })
	@Select("select * from USER where LOGIN=#{login}")
	public User getUserByLogin(String login);

	@Select("select USER_ID from USER where LOGIN=#{name}")
	public Integer getUserIdByName(String name);

	@Select("select LOGIN from USER where LOGIN=#{name}")
	public String uniqueLogin(String name);

	@Select("select LOGIN from USER where USER_ID=#{id}")
	public String getLoginById(Integer id);

	@Select("select count(*) from USER")
	public Integer getUserCount();

	@Select("select LOGIN from USER where LOGIN=#{name} and PASSWORD=#{pass} and enabled='1'")
	public String authorize(@Param("name") String name,
			@Param("pass") String pass);

	@Results(value = { @Result(property = "login", column = "LOGIN"),
			@Result(property = "roleId", column = "ROLE_ID") })
	@Update(EDIT_USER_SAME_PASS)
	public void editUserSamePass(User user);

	@Select("select CR_DATE from USER where LOGIN=#{name}")
	public String getDateByName(String name);
	
	@Select("select count(*) from PROJECTS where PM_USER_ID=#{id}")
	public Integer getPmProjectCount(Integer id);	
	
	@Select("select ENABLED from USER where USER_ID=#{id}")
	public Integer isUserActive (Integer id);	
	
	@Update("update USER set ENABLED='0' where USER_ID =#{id} ")
	public void banUserById (Integer id);
	
	@Update("update USER set ENABLED='1' where USER_ID =#{id} ")
	public void unBanUserById (Integer id);
	
	@Select(GET_COUNT_OF_ROLE_USERS)
	public Integer getCountOfRoleUsers(Integer roleId); 
	
	@Select("select count(*) from USER_PROJECT where USER_ID=#{userId}")
	public Integer getCountOfInvolvedTeam (Integer userId);
	
}
