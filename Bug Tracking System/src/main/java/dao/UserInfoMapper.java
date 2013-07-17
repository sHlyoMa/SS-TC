package dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
public interface UserInfoMapper {
	
	String USER_INFO_LIST_BY_PROJECT_ID = "select USER_INFO.USER_INFO_ID, USER_INFO.SECOND_NAME, USER_INFO.FIRST_NAME, USER.ROLE_ID from USER_INFO " +
			"left join USER on (USER_INFO.USER_INFO_ID=USER.USER_ID) " +
			"left join USER_PROJECT on (USER_INFO.USER_INFO_ID=USER_PROJECT.USER_ID) " +
			"left join PROJECTS on (USER_PROJECT.PROJECT_ID=PROJECTS.PROJECT_ID) " +
			"where USER_PROJECT.PROJECT_ID=#{projectId} and USER.ROLE_ID!=1 order by USER.ROLE_ID desc";
	String USER_INFO_LIST_TO_TEAM       = "select USER_INFO.USER_INFO_ID, USER_INFO.SECOND_NAME, USER_INFO.FIRST_NAME, USER.ROLE_ID from USER_INFO " +
			"left join USER on (USER_INFO.USER_INFO_ID=USER.USER_ID) " +
			"where USER.ROLE_ID!=1 and USER.USER_ID!=#{pmUserId} order by USER.ROLE_ID desc";
	String USER_INFO_LIST_FOR_PROJECT   = "select USER_INFO.USER_INFO_ID, USER_INFO.SECOND_NAME, USER_INFO.FIRST_NAME, USER.ROLE_ID from USER_INFO " +
			"left join USER on (USER_INFO.USER_INFO_ID=USER.USER_ID) " +
			"where USER.ROLE_ID!=1 and USER.USER_ID!=#{currentUserId} order by USER.ROLE_ID desc";
	
	String USER_ROLE                    = "select ROLE_NAME from ROLE where ID=#{roleId}";
	
	@Results(value={
			@Result(javaType=UserInfo.class),
			@Result(property="user.userId", column="USER_INFO_ID"),
			@Result(property="firstName", column="FIRST_NAME"),
			@Result(property="secondName", column="SECOND_NAME"),
			@Result(property="email", column="EMAIL"),
			@Result(property="user", column="USER_INFO_ID", javaType=User.class, one=@One(select="getUser") )
	})
	@Select("select * from USER where USER_ID=#{USER_INFO_ID}")
	public User getUser(Integer id);
	
	@Results(value={
			@Result(javaType=UserInfo.class),
			@Result(property="userId", column="USER_INFO_ID"),
			@Result(property="userInfo.firstName", column="FIRST_NAME"),
			@Result(property="userInfo.secondName", column="SECOND_NAME"),
			@Result(property="UserInfo.email", column="EMAIL"),
	})
	@Insert("insert into USER_INFO (USER_INFO_ID, FIRST_NAME, SECOND_NAME, EMAIL) value (#{userId},#{userInfo.firstName}, #{userInfo.secondName}, #{userInfo.email})")
	public void addUserInfo(User user);
	
	@Delete("delete from USER_INFO where USER_INFO_ID = #{userInfoId}")
	public void deleteUserInfo(Integer id);
	
	@Results(value={
			@Result(property="firstName", column="FIRST_NAME"),
			@Result(property="secondName", column="SECOND_NAME"),
			@Result(property="email", column="EMAIL"),
	})
	@Update("update USER_INFO set FIRST_NAME=#{userInfo.firstName}, SECOND_NAME=#{userInfo.secondName}, EMAIL=#{userInfo.email} where USER_INFO_ID =#{userId}")
	public void updateUserInfo(User user);
	
	@Results(value={
			@Result(property="userInfoId", column="USER_INFO_ID"),
			@Result(property="secondName", column="SECOND_NAME"),
	})
	@Select("select USER_INFO_ID, SECOND_NAME from USER_INFO")
	public List<UserInfo> assignerUserList();
	
	@Results(value={
			@Result(property = "userInfoId", column="USER_INFO_ID"),
			@Result(property = "firstName", column="FIRST_NAME"),
			@Result(property = "secondName", column="SECOND_NAME"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userRole", column = "ROLE_ID", javaType = UserInfo.class, one = @One(select = "getUserRole"))
	})
	@Select(USER_INFO_LIST_TO_TEAM)
	public List<UserInfo> userTeamList(
			@Param("pmUserId") Integer pmUserId);
	
	@Results(value={
			@Result(property = "userInfoId", column="USER_INFO_ID"),
			@Result(property = "firstName", column="FIRST_NAME"),
			@Result(property = "secondName", column="SECOND_NAME"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userRole", column = "ROLE_ID", javaType = UserInfo.class, one = @One(select = "getUserRole"))
	})
	@Select(USER_INFO_LIST_FOR_PROJECT)
	public List<UserInfo> userTeamListWithoutLeaderUser(
			@Param("currentUserId") Integer currentUserId);
	
	@Results(value={
			@Result(property = "userInfoId", column="USER_INFO_ID"),
			@Result(property = "firstName", column="FIRST_NAME"),
			@Result(property = "secondName", column="SECOND_NAME"),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userRole", column = "ROLE_ID", javaType = UserInfo.class, one = @One(select = "getUserRole"))
	})
	@Select(USER_INFO_LIST_BY_PROJECT_ID)
	public List<UserInfo> userTeamListByProjectId(
			@Param("projectId") Integer projectId);
	
	@Results(value={
			@Result(property = "roleName", column="ROLE_NAME")
	})
	@Select(USER_ROLE)
	public Role getUserRole(Integer roleId) throws Exception;
	
}
