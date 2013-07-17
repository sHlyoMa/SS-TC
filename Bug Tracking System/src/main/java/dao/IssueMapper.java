package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.springframework.stereotype.Component;

import domain.Environment;
import domain.Issue;
import domain.Priority;
import domain.Project;
import domain.Status;
import domain.Type;
import domain.UserInfo;

@Component
public interface IssueMapper {

	final static String GET_LIST_OF_ISSUE = "select * from ISSUES LIMIT #{limit}, 10 ";
	final static String CREATE_ISSUE = "insert into ISSUES "
			+ "(NAME, I_KEY, ENVIRONMENT_ID, DESCRIPTION, REPORTER_ID, ASSIGNER_ID, "
			+ "TYPE_ID, PRIORITY_ID, STATUS_ID, PROJECT_ID, CREATED)"
			+ " values (#{name},#{key},#{environmentId},#{description},#{reporterId},"
			+ "#{assignerId},#{typeId}, #{priorityId}, #{statusId}, #{projectId}, "
			+ "(select now()))";
	final static String UPDATE_ISSUE = "update ISSUES "
			+ "set NAME=#{name}, ENVIRONMENT_ID=#{environmentId}, "
			+ "DESCRIPTION=#{description}, ASSIGNER_ID=#{assignerId}, "
			+ "PRIORITY_ID=#{priorityId}, STATUS_ID=#{statusId}, PROJECT_ID=#{projectId}, "
			+ "UPDATED=(select now()), CLOSE_DATE=#{closeDate}, BLOCKED=#{blocked} where ISSUE_ID=#{issueId}";
	final static String DELETE_ISSUE = "delete from ISSUES where ISSUE_ID=#{issueId}";
	final static String GET_MY_ISSUE_LIST = "select * from ISSUES as i left join USER_INFO as u on i.ASSIGNER_ID=u.USER_INFO_ID "
			+ "where (ASSIGNER_ID=#{assignerId} OR REPORTER_ID=#{reporterId})"
			+ " <if test=\"typeId != null\"> and TYPE_ID = #{typeId}</if> "
			+ " <if test=\"statusId != null\"> and STATUS_ID = #{statusId}</if> "
			+ " <if test=\"priorityId != null\"> and PRIORITY_ID=#{priorityId} </if> "
			+ " <if test=\"environmentId != null\"> and ENVIRONMENT_ID=#{environmentId} </if> "
			+ " <if test=\"blocked == '(blocked)'\"> and BLOCKED is not null </if> "
			+ " <if test=\"blocked == '(unblocked)'\"> and BLOCKED is null </if> "
			+ " <if test=\"search != '%null%' \"><if test=\"search != '%%' \"> "
			+ "and (Name like #{search} or Description like #{search} ) </if> </if>"
			+ "order by "
			+ " <if test=\"columnNumber ==1 and direction ==1\"> TYPE_ID asc</if>"
			+ " <if test=\"columnNumber ==1 and direction == 2\"> TYPE_ID desc</if>"
			+ " <if test=\"columnNumber ==2 and direction ==1\"> I_KEY asc</if>"
			+ " <if test=\"columnNumber ==2 and direction == 2\"> I_KEY desc</if>"
			+ " <if test=\"columnNumber ==3 and direction ==1\"> name asc</if>"
			+ " <if test=\"columnNumber ==3 and direction == 2\"> name desc</if>"
			+ " <if test=\"columnNumber ==4 and direction ==1\"> SECOND_NAME asc</if>"
			+ " <if test=\"columnNumber ==4 and direction == 2\"> SECOND_NAME desc</if>"
			+ " <if test=\"columnNumber ==5 and direction ==1\"> STATUS_ID asc</if>"
			+ " <if test=\"columnNumber ==5 and direction == 2\"> STATUS_ID desc</if>"
			+ " <if test=\"columnNumber ==6 and direction ==1\"> PRIORITY_ID asc</if>"
			+ " <if test=\"columnNumber ==6 and direction == 2\"> PRIORITY_ID desc</if>"
			+ " <if test=\"columnNumber ==7 and direction ==1\"> ENVIRONMENT_ID asc</if>"
			+ " <if test=\"columnNumber ==7 and direction == 2\"> ENVIRONMENT_ID desc</if>"
			+ " <if test=\"columnNumber ==8 and direction ==1\"> CREATED desc</if>"
			+ " <if test=\"columnNumber ==8 and direction == 2\"> CREATED asc</if>"
			+ "LIMIT #{page}, #{limit}  ";
	final static String GET_ISSUE_BY_PROJECT_ID = "select * from ISSUES as i left join USER_INFO as u on i.ASSIGNER_ID=u.USER_INFO_ID "
			+ "where PROJECT_ID=#{projectId}"
			+ " <if test=\"typeId != null\"> and TYPE_ID = #{typeId}</if>"
			+ " <if test=\"statusId != null\"> and STATUS_ID = #{statusId}</if>"
			+ " <if test=\"priorityId != null\"> and PRIORITY_ID=#{priorityId} </if>"
			+ " <if test=\"environmentId != null\"> and ENVIRONMENT_ID=#{environmentId}</if>"
			+ " <if test=\"blocked == '(blocked)'\"> and BLOCKED is not null </if> "
			+ " <if test=\"blocked == '(unblocked)'\"> and BLOCKED is null </if> "
			+ " <if test=\"search != '%null%' \"><if test=\"search != '%%' \"> " 
			+ "and (Name like #{search} or Description like #{search} ) </if> </if>"
			+ "order by "
			+ " <if test=\"columnNumber ==1 and direction ==1\"> TYPE_ID asc</if>"
			+ " <if test=\"columnNumber ==1 and direction == 2\"> TYPE_ID desc</if>"
			+ " <if test=\"columnNumber ==2 and direction ==1\"> I_KEY asc</if>"
			+ " <if test=\"columnNumber ==2 and direction == 2\"> I_KEY desc</if>"
			+ " <if test=\"columnNumber ==3 and direction ==1\"> name asc</if>"
			+ " <if test=\"columnNumber ==3 and direction == 2\"> name desc</if>"
			+ " <if test=\"columnNumber ==4 and direction ==1\"> SECOND_NAME asc</if>"
			+ " <if test=\"columnNumber ==4 and direction == 2\"> SECOND_NAME desc</if>"
			+ " <if test=\"columnNumber ==5 and direction ==1\"> STATUS_ID asc</if>"
			+ " <if test=\"columnNumber ==5 and direction == 2\"> STATUS_ID desc</if>"
			+ " <if test=\"columnNumber ==6 and direction ==1\"> PRIORITY_ID asc</if>"
			+ " <if test=\"columnNumber ==6 and direction == 2\"> PRIORITY_ID desc</if>"
			+ " <if test=\"columnNumber ==7 and direction ==1\"> ENVIRONMENT_ID asc</if>"
			+ " <if test=\"columnNumber ==7 and direction == 2\"> ENVIRONMENT_ID desc</if>"
			+ " <if test=\"columnNumber ==8 and direction ==1\"> CREATED desc</if>"
			+ " <if test=\"columnNumber ==8 and direction == 2\"> CREATED asc</if>"
			+ "LIMIT #{page}, #{limit} ";
	final static String GET_COUNT_MY_ISSUE = "select count(*) from ISSUES "
			+ "where (ASSIGNER_ID=#{assignerId} OR REPORTER_ID=#{reporterId})"
			+ " <if test=\"typeId != null\"> and TYPE_ID = #{typeId}</if> "
			+ " <if test=\"statusId != null\"> and STATUS_ID = #{statusId}</if> "
			+ " <if test=\"priorityId != null\"> and PRIORITY_ID=#{priorityId} </if> "
			+ " <if test=\"environmentId != null\"> and ENVIRONMENT_ID=#{environmentId} </if> "
			+ " <if test=\"blocked == '(blocked)'\"> and BLOCKED is not null </if> "
			+ " <if test=\"blocked == '(unblocked)'\"> and BLOCKED is null </if> "
			+ " <if test=\"search != '%null%' \"><if test=\"search != '%%' \"> "
			+ "and (Name like #{search} or Description like #{search} ) </if> </if>";
	final static String GET_COUNT_PROJECT_ISSUE = "select count(*) from ISSUES "
			+ "where PROJECT_ID=#{projectId}"
			+ " <if test=\"typeId != null\"> and TYPE_ID = #{typeId}</if>"
			+ " <if test=\"statusId != null\"> and STATUS_ID = #{statusId}</if>"
			+ " <if test=\"priorityId != null\"> and PRIORITY_ID=#{priorityId} </if>"
			+ " <if test=\"environmentId != null\"> and ENVIRONMENT_ID=#{environmentId}</if>"
			+ " <if test=\"blocked == '(blocked)'\"> and BLOCKED is not null </if> "
			+ " <if test=\"blocked == '(unblocked)'\"> and BLOCKED is null </if> "
			+ " <if test=\"search != '%null%' \"><if test=\"search != '%%' \"> "
			+ "and (Name like #{search} or Description like #{search} ) </if> </if>";
	final static String GET_COUNT_PROJECT_ISSUE_BY_TYPE = "select count(*) from ISSUES where "
			+ "PROJECT_ID=#{projectId} and TYPE_ID=#{typeId} ";
	final static String GET_ISSUE_DETAILS = "select * from ISSUES where ISSUE_ID=#{issueId}";
	final static String GET_ISSUE_BY_ID = "select * from ISSUES where ISSUE_ID=#{issueId}";
	final static String GET_REPORTER = "select FIRST_NAME, SECOND_NAME, EMAIL from USER_INFO"
			+ " where USER_INFO_ID=#{reporterId}";
	final static String GET_ASSIGNER = "select FIRST_NAME, SECOND_NAME, EMAIL from USER_INFO"
			+ " where USER_INFO_ID=#{assignerId}";
	final static String GET_TYPE = "select type from ISSUE_TYPE where type_id=#{typeId}";
	final static String GET_STATUS = "select status from ISSUE_STATUS where status_id = #{statusId}";
	final static String GET_PRIORITY = "select priority from ISSUE_PRIORITY"
			+ " where priority_id = #{priorityId}";
	final static String GET_ENVIRONMENT = "select * from ISSUE_ENVIRONMENT "
			+ "where ENVIRONMENT_ID=#{priorityId}";
	final static String GET_ISSUE_PROJECT = "select NAME from PROJECTS"
			+ " where PROJECT_ID=#{projectId}";
	final static String SET_ISSUE_CLOSE_DATE = "update ISSUES set CLOSE_DATE=(select now()) where ISSUE_ID=#{issueId}";
	final static String GET_ISSUE_ID = "select ISSUE_ID from ISSUES where NAME=#{name} and DESCRIPTION=#{description}";
	final static String DELETE_ISSUE_BY_PROJECT_ID= "delete from ISSUES where PROJECT_ID=#{projectId}";

	@Results(value = {
			@Result(javaType = Issue.class),
			@Result(property = "issueId", column = "ISSUE_ID"),
			@Result(property = "reporterId", column = "REPORTER_ID"),
			@Result(property = "assignerId", column = "ASSIGNER_ID"),
			@Result(property = "typeId", column = "TYPE_ID"),
			@Result(property = "statusId", column = "STATUS_ID"),
			@Result(property = "priorityId", column = "PRIORITY_ID"),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "environmentId", column = "ENVIRONMENT_ID"),
			@Result(property = "blocked", column = "BLOCKED"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "key", column = "I_KEY"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "created", column = "CREATED"),
			@Result(property = "updated", column = "UPDATED"),
			@Result(property = "closeDate", column = "CLOSE_DATE"),
			@Result(property = "userReporter", column = "REPORTER_ID", javaType = UserInfo.class, one = @One(select = "getReporterUser")),
			@Result(property = "userAssigner", column = "ASSIGNER_ID", javaType = UserInfo.class, one = @One(select = "getAssignerUser")),
			@Result(property = "type", column = "TYPE_ID", javaType = Type.class, one = @One(select = "getIssueType")),
			@Result(property = "status", column = "STATUS_ID", javaType = Status.class, one = @One(select = "getIssueStatus")),
			@Result(property = "priority", column = "PRIORITY_ID", javaType = Priority.class, one = @One(select = "getIssuePriority")),
			@Result(property = "environment", column = "ENVIRONMENT_ID", javaType = Environment.class, one = @One(select = "getIssueEnvironment")),
			@Result(property = "project", column = "PROJECT_ID", javaType = Project.class, one = @One(select = "getIssueProject")) })
	@Lang(XMLLanguageDriver.class)
	@Select(GET_MY_ISSUE_LIST)
	public List<Issue> getMyIssueList(@Param("assignerId") Integer assignerId,
			@Param("reporterId") Integer reporterId,
			@Param("typeId") Integer typeId,
			@Param("statusId") Integer statusId,
			@Param("priorityId") Integer priorityId,
			@Param("environmentId") Integer environmentId,
			@Param("blocked") String blocked, @Param("search") String search,
			@Param("page") Integer page, @Param("limit") Integer limit,
			@Param("columnNumber") Integer columnNumber,
			@Param("direction") Integer direction);

	@Results(value = {
			@Result(javaType = Issue.class),
			@Result(property = "issueId", column = "ISSUE_ID"),
			@Result(property = "reporterId", column = "REPORTER_ID"),
			@Result(property = "assignerId", column = "ASSIGNER_ID"),
			@Result(property = "typeId", column = "TYPE_ID"),
			@Result(property = "statusId", column = "STATUS_ID"),
			@Result(property = "priorityId", column = "PRIORITY_ID"),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "environmentId", column = "ENVIRONMENT_ID"),
			@Result(property = "blocked", column = "BLOCKED"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "key", column = "I_KEY"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "created", column = "CREATED"),
			@Result(property = "updated", column = "UPDATED"),
			@Result(property = "closeDate", column = "CLOSE_DATE"),
			@Result(property = "userReporter", column = "REPORTER_ID", javaType = UserInfo.class, one = @One(select = "getReporterUser")),
			@Result(property = "userAssigner", column = "ASSIGNER_ID", javaType = UserInfo.class, one = @One(select = "getAssignerUser")),
			@Result(property = "type", column = "TYPE_ID", javaType = Type.class, one = @One(select = "getIssueType")),
			@Result(property = "status", column = "STATUS_ID", javaType = Status.class, one = @One(select = "getIssueStatus")),
			@Result(property = "priority", column = "PRIORITY_ID", javaType = Priority.class, one = @One(select = "getIssuePriority")),
			@Result(property = "environment", column = "ENVIRONMENT_ID", javaType = Environment.class, one = @One(select = "getIssueEnvironment")),
			@Result(property = "project", column = "PROJECT_ID", javaType = Project.class, one = @One(select = "getIssueProject")) })
	@Lang(XMLLanguageDriver.class)
	@Select(GET_ISSUE_BY_PROJECT_ID)
	public List<Issue> getIssueByProjectId(
			@Param("projectId") Integer projectId,
			@Param("typeId") Integer typeId,
			@Param("statusId") Integer statusId,
			@Param("priorityId") Integer priorityId,
			@Param("environmentId") Integer environmentId,
			@Param("blocked") String blocked, @Param("search") String search,
			@Param("page") Integer page, @Param("limit") Integer limit,
			@Param("columnNumber") Integer columnNumber,
			@Param("direction") Integer direction);

	@Results(value = {
			@Result(property = "issueId", column = "ISSUE_ID"),
			@Result(property = "reporterId", column = "REPORTER_ID"),
			@Result(property = "assignerId", column = "ASSIGNER_ID"),
			@Result(property = "typeId", column = "TYPE_ID"),
			@Result(property = "statusId", column = "STATUS_ID"),
			@Result(property = "priorityId", column = "PRIORITY_ID"),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "environmentId", column = "ENVIRONMENT_ID"),
			@Result(property = "blocked", column = "BLOCKED"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "key", column = "I_KEY"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "created", column = "CREATED"),
			@Result(property = "updated", column = "UPDATED"),
			@Result(property = "closeDate", column = "CLOSE_DATE"),
			@Result(property = "userReporter", column = "REPORTER_ID", javaType = UserInfo.class, one = @One(select = "getReporterUser")),
			@Result(property = "userAssigner", column = "ASSIGNER_ID", javaType = UserInfo.class, one = @One(select = "getAssignerUser")),
			@Result(property = "type", column = "TYPE_ID", javaType = Type.class, one = @One(select = "getIssueType")),
			@Result(property = "status", column = "STATUS_ID", javaType = Status.class, one = @One(select = "getIssueStatus")),
			@Result(property = "priority", column = "PRIORITY_ID", javaType = Priority.class, one = @One(select = "getIssuePriority")),
			@Result(property = "environment", column = "ENVIRONMENT_ID", javaType = Environment.class, one = @One(select = "getIssueEnvironment")),
			@Result(property = "project", column = "PROJECT_ID", javaType = Project.class, one = @One(select = "getIssueProject")) })
	@Select(GET_ISSUE_DETAILS)
	public Issue getIssueDetails(Integer issueId);

	@Results(value = {
			@Result(property = "issueId", column = "ISSUE_ID"),
			@Result(property = "reporterId", column = "REPORTER_ID"),
			@Result(property = "assignerId", column = "ASSIGNER_ID"),
			@Result(property = "typeId", column = "TYPE_ID"),
			@Result(property = "statusId", column = "STATUS_ID"),
			@Result(property = "priorityId", column = "PRIORITY_ID"),
			@Result(property = "projectId", column = "PROJECT_ID"),
			@Result(property = "environmentId", column = "ENVIRONMENT_ID"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "key", column = "I_KEY"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "created", column = "CREATED"),
			@Result(property = "updated", column = "UPDATED"),
			@Result(property = "type", column = "TYPE_ID", javaType = Type.class, one = @One(select = "getIssueType")),
			@Result(property = "closeDate", column = "CLOSE_DATE") })
	@Select(GET_ISSUE_BY_ID)
	public Issue getIssueById(Integer issueId);

	@Select(GET_COUNT_MY_ISSUE)
	Integer getCountOfMyIssue(@Param("assignerId") Integer assignerId,
			@Param("reporterId") Integer reporterId,
			@Param("typeId") Integer typeId,
			@Param("statusId") Integer statusId,
			@Param("priorityId") Integer priorityId,
			@Param("environmentId") Integer environmentId,
			@Param("blocked") String blocked, 
			@Param("search") String search);

	@Select(GET_COUNT_PROJECT_ISSUE)
	Integer getCountOfProjectIssue(@Param("projectId") Integer projectId,
			@Param("typeId") Integer typeId,
			@Param("statusId") Integer statusId,
			@Param("priorityId") Integer priorityId,
			@Param("environmentId") Integer environmentId,
			@Param("blocked") String blocked, 
			@Param("search") String search);

	@Select(GET_COUNT_PROJECT_ISSUE_BY_TYPE)
	Integer getCountOfProjectIssueByType(@Param("typeId") Integer typeId,
			@Param("projectId") Integer projectId);

	@Insert(CREATE_ISSUE)
	@Options(useGeneratedKeys = true, keyProperty = "issueId")
	public void addIssue(Issue issue) throws Exception;

	@Update(UPDATE_ISSUE)
	public void updateIssue(Issue issue) throws Exception;

	@Delete(DELETE_ISSUE)
	public void removeIssues(Integer issueId);
	
	@Results(value = { @Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "secondName", column = "SECOND_NAME"),
			@Result(property = "email", column = "EMAIL") })
	@Select(GET_REPORTER)
	public UserInfo getReporterUser(Integer reporterId);

	@Results(value = { @Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "secondName", column = "SECOND_NAME"),
			@Result(property = "email", column = "EMAIL") })
	@Select(GET_ASSIGNER)
	public UserInfo getAssignerUser(Integer assignerId);

	@Update(SET_ISSUE_CLOSE_DATE)
	void setCloseDate(Integer issueId);

	@Select(GET_TYPE)
	Type getIssueType(Integer id);

	@Select(GET_STATUS)
	Status getIssueStatus(Integer id);

	@Select(GET_PRIORITY)
	Priority getIssuePriority(Integer id);

	@Select(GET_ENVIRONMENT)
	Environment getIssueEnvironment(Integer id);

	@Select(GET_ISSUE_PROJECT)
	Project getIssueProject(Integer id);

	@Select(GET_ISSUE_ID)
	Integer getIssueIdByIssue(Issue issue);

}
