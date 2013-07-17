package dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import domain.Environment;

@Component
public interface EnvironmentMapper {

	
	@Results(value={
			@Result(javaType=Environment.class),
			@Result(property="environmentId", column="ENVIRONMENT_ID"),
			@Result(property="environment", column="ENVIRONMENT")
	})
	@Select("select * from ISSUE_ENVIRONMENT")
	public List<Environment> getEnvironmentList(); 

}
