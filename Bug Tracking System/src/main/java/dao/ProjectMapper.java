package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import domain.Project;
import domain.UserProject;
import domain.UserInfo;

@Component
public interface ProjectMapper {
	String PROJECT             = "select * from PROJECTS where PROJECT_ID=#{projectId}";
	String PROJECT_LIST        = "select * from PROJECTS";
	String PM_PROJECT_LIST     = "select * from PROJECTS where PM_USER_ID=#{pmUserId}";
	String TEAM_PROJECT_LIST   = "select * from PROJECTS left join USER_PROJECT on (USER_PROJECT.PROJECT_ID=PROJECTS.PROJECT_ID) where USER_PROJECT.USER_ID=#{userId}";
	String LITTLE_PROJECT_LIST = "select PROJECT_ID, NAME from PROJECTS";
	String PM_USER_FULL_NAME   = "select FIRST_NAME, SECOND_NAME from USER_INFO where USER_INFO_ID=#{pmUserId}";
	String USER_PROJECT_LIST   = "select USER_PROJECT.USER_ID, USER.ROLE_ID from USER_PROJECT left join USER on (USER_PROJECT.USER_ID=USER.USER_ID) where USER.ROLE_ID!=1 and USER_PROJECT.PROJECT_ID=#{projectId} order by USER.ROLE_ID desc";
	String ADD_PROJECT         = "insert into PROJECTS (NAME, DESCRIPTION, PM_USER_ID) values (#{name},#{description},#{pmUserId})";
	String UPDATE_PROJECT      = "update PROJECTS set NAME=#{name}, DESCRIPTION=#{description} where PROJECT_ID=#{projectId}";
	String DELETE_PROJECT      = "delete from PROJECTS where PROJECT_ID=#{projectId}";
	String PROJECT_ID          = "select PROJECT_ID from PROJECTS where NAME=#{name} and PM_USER_ID=#{pmUserId}";
	String PROJECT_NAME_BY_PROJECT_ID = "select NAME from PROJECTS where PROJECT_ID=#{projectId}" ;
	
	@Select(PROJECT_ID)
	Integer getProjectIdByProject(Project project);
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "pmUserId", column = "PM_USER_ID"),
			@Result(property = "pmUserFullName", column = "PM_USER_ID", javaType = UserInfo.class, one = @One(select = "getPmUserFullName")),
			@Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "team", column="PROJECT_ID", javaType = List.class, many = @Many(select = "getUserProjectListByProjectId"))
	})
	@Select(PROJECT)
	public Project getProjectById(Integer projectId) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "pmUserId", column = "PM_USER_ID"),
			@Result(property = "pmUserFullName", column = "PM_USER_ID", javaType = UserInfo.class, one = @One(select = "getPmUserFullName")),
			@Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION")	
	})
	@Select(PROJECT_LIST)
	public List<Project> projectList() throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "pmUserId", column = "PM_USER_ID"),
			@Result(property = "pmUserFullName", column = "PM_USER_ID", javaType = UserInfo.class, one = @One(select = "getPmUserFullName")),
			@Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION")	
	})
	@Select(PM_PROJECT_LIST)
	public List<Project> pmProjectList(Integer pmUserId) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "pmUserId", column = "PM_USER_ID"),
			@Result(property = "pmUserFullName", column = "PM_USER_ID", javaType = UserInfo.class, one = @One(select = "getPmUserFullName")),
			@Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION")	
	})
	@Select(TEAM_PROJECT_LIST)
	public List<Project> teamProjectList(Integer userId) throws Exception;
	
	@Results(value = {
			@Result(javaType = Project.class),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "name", column = "NAME"),	
	})
	@Select(LITTLE_PROJECT_LIST)
	public List<Project> littleProjectList() throws Exception;
	
	@Results(value={
			@Result(property="firstName", column="FIRST_NAME"),
			@Result(property="secondName", column="SECOND_NAME"),
	})
	@Select(PM_USER_FULL_NAME)
	public UserInfo getPmUserFullName(Integer pmUserId) throws Exception;
	
	@Results(value={
			@Result(property="userId", column="USER_ID"),
			@Result(property="roleId", column="ROLE_ID")
	})
	@Select(USER_PROJECT_LIST)
	public List<UserProject> getUserProjectListByProjectId(Integer projectId) throws Exception;
	
	@Insert(ADD_PROJECT)
	@Options(keyProperty = "projectId")
	public void addProject(Project project) throws Exception;

	@Update(UPDATE_PROJECT)
	public void editProject(Project project) throws Exception;

	@Delete(DELETE_PROJECT)
	public void deleteProject(Integer projectId) throws Exception;
	
	@Select(PROJECT_NAME_BY_PROJECT_ID)
	public String getProjectNameByProjectId(Integer projectId);
	
	@Select("select NAME from PROJECTS where NAME=#{name}")
	public String checkIfUniqueName(String name);
}
