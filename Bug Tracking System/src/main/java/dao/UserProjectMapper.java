package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import domain.UserInfo;
import domain.UserProject;
import domain.Role;
import domain.Project;

@Component
public interface UserProjectMapper {

	String USER_FULL_NAME               = "select FIRST_NAME, SECOND_NAME from USER_INFO where USER_INFO_ID=#{userId}";
	String USER_PROJECT_LIST_BY_PROJECT = "select USER_PROJECT.USER_ID, USER.ROLE_ID from USER_PROJECT left join USER on (USER_PROJECT.USER_ID=USER.USER_ID) left join USER_INFO on (USER_INFO.USER_INFO_ID=USER.USER_ID) where USER.ROLE_ID!=1 and USER_PROJECT.PROJECT_ID=#{projectId} order by USER.ROLE_ID desc";
	String USER_PROJECT_BY_USER_PROJECT_ID = "select USER_PROJECT.USER_ID, USER.ROLE_ID from USER_PROJECT left join USER on (USER_PROJECT.USER_ID=USER.USER_ID) where USER.ROLE_ID!=1 and USER_PROJECT.USER_PROJECT_ID=#{userProjectId} order by USER.ROLE_ID desc";
	String USER_PROJECT_BY_USER_PROJECT = "select USER_PROJECT.USER_ID, USER.ROLE_ID from USER_PROJECT left join USER on (USER_PROJECT.USER_ID=USER.USER_ID) where USER.ROLE_ID!=1 and USER_PROJECT.USER_PROJECT_ID=#{userProjectId} order by USER.ROLE_ID desc";
	String USER_PROJECT_IDS_BY_PROJECT  = "select * from USER_PROJECT left join USER on (USER_PROJECT.USER_ID=USER.USER_ID) where USER_PROJECT.PROJECT_ID=#{projectId} order by USER.ROLE_ID desc";
	String USER_PROJECT_ID_BY_USER_PROJECT = "select USER_PROJECT_ID from USER_PROJECT where USER_PROJECT.USER_ID=#{userId} and USER_PROJECT.PROJECT_ID=#{projectId}";
	String USER_ROLE                    = "select ROLE_NAME from ROLE where ID=#{roleId}";
	String ADD_USER_PROJECT             = "insert into USER_PROJECT (USER_ID, PROJECT_ID) values (#{userId}, #{projectId})";
	String UPDATE_USER_PROJECT          = "update USER_PROJECT set USER_ID=#{userId}, PROJECT_ID=#{projectId} where USER_PROJECT_ID=#{userProjectId}";
	String DELETE_USER_PROJECT          = "delete from USER_PROJECT where USER_PROJECT_ID=#{userProjectId}";
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "userFullName", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserFullName")),	
			@Result(property = "projectId", column = "PROJECT_ID")
	})
	@Select(USER_PROJECT_BY_USER_PROJECT_ID)
	public UserProject getUserProjectByUserProjectId(Integer userProjectId) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "userFullName", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserFullName")),	
			@Result(property = "projectId", column = "PROJECT_ID")
	})
	@Select(USER_PROJECT_BY_USER_PROJECT)
	public UserProject getUserProjectByUserProject(UserProject userProject) throws Exception;
	
	@Select(USER_PROJECT_ID_BY_USER_PROJECT)
	public Integer getUserProjectIdByUserProject(UserProject userProject) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "userProjectId", column = "USER_PROJECT_ID"),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "projectId", column = "PROJECT_ID")
	})
	@Select(USER_PROJECT_IDS_BY_PROJECT)
	public List<UserProject> userProjectIdsByProjectId(Integer projectId) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "userFullName", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserFullName")),
			@Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "userRole", column = "ROLE_ID", javaType = UserInfo.class, one = @One(select = "getUserRole"))
	})
	@Select(USER_PROJECT_LIST_BY_PROJECT)
	public List<UserProject> userProjectListByProjectId(Integer projectId) throws Exception;
	
	@Results(value={
			@Result(property = "firstName", column="FIRST_NAME"),
			@Result(property = "secondName", column="SECOND_NAME")
	})
	@Select(USER_FULL_NAME)
	public UserInfo getUserFullName(Integer userId) throws Exception;
	
	@Results(value={
			@Result(property = "roleName", column="ROLE_NAME")
	})
	@Select(USER_ROLE)
	public Role getUserRole(Integer roleId) throws Exception;
	
	@Insert(ADD_USER_PROJECT)
	@Options(keyProperty = "userProjectId")
	public void addUserProject(UserProject userProject) throws Exception;

	@Delete(DELETE_USER_PROJECT)
	public void deleteUserProject(Integer userProjectId) throws Exception;
}
