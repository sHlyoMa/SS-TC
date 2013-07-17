package dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import domain.Action;

@Component
public interface ActionMapper {

	@Results(value={
			@Result(javaType=Action.class),
			@Result(property="actionId", column="ACTION_ID"),
			@Result(property="action", column="ACTION"),
	})
	@Select("select * from ACTION")
	public List<Action> actionList();
	
}
