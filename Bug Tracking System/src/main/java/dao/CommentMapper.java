package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import domain.Comment;
import domain.UserInfo;

@Component
public interface CommentMapper {
	
	String GET_COMMENT = "select * from COMMENTS where COMMENT_ID = #{commentId}";
	String GET_LIST_OF_COMMENTS = "SELECT * FROM COMMENTS where ISSUE_ID=#{issueId}";
	String CREATE_COMMENT = "INSERT INTO COMMENTS (ISSUE_ID, USER_ID, CREATING_DATE, VALUE) values (#{issueId}, #{userId}, (select now()), #{value})";
	String UPDATE_COMMENT = "UPDATE COMMENTS SET  VALUE = #{value} WHERE COMMENT_ID = #{commentId}" ;
	String DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COMMENT_ID = #{commentId}";
	String USER_FULL_NAME = "select FIRST_NAME, SECOND_NAME from USER_INFO where USER_INFO_ID=#{userId}";
	
@Results(value = {
		@Result(javaType = Comment.class),
		@Result(property = "commentId", column = "COMMENT_ID"),
		@Result(property = "issueId", column = "ISSUE_ID"),
		@Result(property = "userId", column = "USER_ID"),
		@Result(property = "creatingDate", column = "CREATING_DATE"),
		@Result(property = "value", column = "VALUE"),
		@Result(property = "userName", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserName")),
		})
@Options(useGeneratedKeys = true, keyProperty = "commentId")
@Select(GET_LIST_OF_COMMENTS)
public List<Comment> commentList(Integer issueId);

@Insert(CREATE_COMMENT)
@Options(useGeneratedKeys = true, keyProperty = "commentId")
public void createComment(Comment comment) throws Exception;

@Results(value = {		
		@Result(property = "commentId", column = "COMMENT_ID"),
		@Result(property = "issueId", column = "ISSUE_ID"),
		@Result(property = "userId", column = "USER_ID"),
		@Result(property = "creatingDate", column = "CREATING_DATE"),
		@Result(property = "value", column = "VALUE"), })
@Update(UPDATE_COMMENT)
public void updateComment(Comment comment) throws Exception;

@Delete(DELETE_COMMENT)
public void deleteComment(Integer commentId);

@Results(value = {
	@Result(javaType = Comment.class),
	@Result(property = "commentId", column = "COMMENT_ID"),
	@Result(property = "issueId", column = "ISSUE_ID"),
	@Result(property = "userId", column = "USER_ID"),
	@Result(property = "creatingDate", column = "CREATING_DATE"),
	@Result(property = "value", column = "VALUE"),
	@Result(property = "userName", column = "USER_ID", javaType = UserInfo.class, one = @One(select = "getUserName"))
})
@Select(GET_COMMENT)
public Comment getCommentById (Integer commentId);

@Results(value={
		@Result(property="firstName", column="FIRST_NAME"),
		@Result(property="secondName", column="SECOND_NAME"),
})
@Select(USER_FULL_NAME)
public UserInfo getUserName (Integer userId);
}
