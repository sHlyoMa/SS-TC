package dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import domain.Issue;
import domain.Status;

@Component
public interface StatusMapper {

	@Results(value = {
			@Result(javaType=Status.class),
			@Result(property = "statusId", column="STATUS_ID"),
			@Result(property = "status", column="STATUS"),
	})
	@Select("select * from ISSUE_STATUS")
	public List<Status> listOfStatus();
	
	@Results(value = {
			@Result(javaType=Status.class),
			@Result(property = "statusId", column="STATUS_ID"),
			@Result(property = "status", column="STATUS"),
			@Result(property = "statuslist", column="STATUS_ID", javaType=List.class, many=@Many(select="selectIssueByStatus"))
	})
	@Select("select * from ISSUES where STATUS_ID = #{statusId}")
	public List<Issue> selectIssueByStatus(Integer statusId);
	
}
