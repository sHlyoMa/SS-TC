package dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Many;

import domain.Issue;
import domain.Type;


@Component
public interface TypeMapper {

	
	@Results(value = {
			@Result(javaType=Type.class),
			@Result(property = "typeId", column="TYPE_ID"),
			@Result(property = "type", column="TYPE"),
	})
	@Select("select * from ISSUE_TYPE")
	public List<Type> listOfTypes();
	
	@Results(value = {
			@Result(javaType=Type.class),
			@Result(property = "typeId", column="TYPE_ID"),
			@Result(property = "type", column="TYPE"),
			@Result(property = "typelist", column="TYPE_ID", javaType=List.class, many=@Many(select="selectIssueByType"))
	})
	@Select("select * from ISSUES where TYPE_ID = #{typeId}")
	public List<Issue> selectIssueByType(Integer typeId);
}
