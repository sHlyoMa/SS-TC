package dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import domain.Issue;
import domain.Priority;

@Component
public interface PriorityMapper {

	@Results(value={
			@Result(javaType=Priority.class),
			@Result(property="priorityId", column="PRIORITY_ID"),
			@Result(property="priority", column="PRIORITY"),
	})
	@Select("select * from ISSUE_PRIORITY")
	public List<Priority> statusList();
	
	@Results(value={
			@Result(javaType=Priority.class),
			@Result(property="priorityId", column="PRIORITY_ID"),
			@Result(property="priority", column="PRIORITY"),
			@Result(property="priorityList", column="PRIORITY_ID", javaType=List.class, many=@Many(select="selectIssueByPriority"))
	})
	@Select("select * from ISSUES where PRIORITY_ID=#{priorityId}")
	public List<Issue> selectIssueByPriority(Integer priorityId);
}
