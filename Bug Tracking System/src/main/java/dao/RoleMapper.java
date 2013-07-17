package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import domain.Action;
import domain.Role;
import domain.RoleAction;

@Component
public interface RoleMapper {
	
	final static String CREATE_ROLE = "insert into ROLE (ID, ROLE_NAME, DESCRIPTION) value (#{roleId}, #{roleName}, #{description})";
	final static String GET_LIST_OF_USERS_BY_ROLE = "select * from ISSUES where PRIORITY_ID=#{priority_id}";
	final static String GET_ROLE_LIST = "select * from ROLE";
	final static String UPDATE_ROLE = "update ROLE set ROLE_NAME=#{roleName}, DESCRIPTION=#{description} where ID=#{roleId}";
	final static String DELETE_ROLE = "delete from ROLE where ID=#{roleId}";
	final static String GET_ROLE_BY_ID = "select * from ROLE where ID=#{roleId}";
	final static String CREATE_ROLE_ACTION = "insert into ROLE_ACTION (ROLE_ID, ACTION_ID) values "+ 
			" <foreach item=\"selectedActionList\" collection=\"role.selectedActionList\" index=\"index\" separator=\",\" >" +
					"(#{role.roleId}, #{selectedActionList})</foreach> ";
	final static String UPDATE_ROLE_ACTION = "update ROLE_ACTION set ACTION_ID="+ 
			" <foreach item=\"selectedActionList\" collection=\"role.selectedActionList\" index=\"index\" separator=\",\" >" +
					"#{selectedActionList}</foreach> where ROLE_ID=#{role.roleId}";
	final static String DELETE_ROLE_ACTION = "delete from ROLE_ACTION where ROLE_ID=#{roleId}";
	final static String GET_ACTION_BY_ROLE_ID = "select ROLE_ACTION.ROLE_ID, ROLE_ACTION.ACTION_ID, ACTION.ACTION from ROLE_ACTION left join ACTION on ROLE_ACTION.ACTION_ID=ACTION.ACTION_ID where ROLE_ACTION.ROLE_ID=#{roleId} ";
	final static String GET_ROLE_BY_NAME = "select ROLE_NAME from ROLE where ROLE_NAME=#{roleName}";
	
	@Results(value={
			@Result(javaType=Role.class),
			@Result(property="roleId", column="ID"),
			@Result(property="roleName", column="ROLE_NAME"),
			@Result(property="description", column="DESCRIPTION")
	})
	@Select(GET_ROLE_LIST)
	public List<Role> roleList();
	
	@Results(value={
			@Result(javaType=Role.class),
			@Result(property="roleId", column="ID"),
			@Result(property="roleName", column="ROLE_NAME"),
			@Result(property="description", column="DESCRIPTION"),
			@Result(property="actionList", column="ID", javaType=List.class, many=@Many(select="selectActionByRoleId"))
	})
	@Select(GET_ROLE_BY_ID)
	public Role getRoleByid(Integer roleId);
	
	@Results(value={
			@Result(javaType=Role.class),
			@Result(property="roleId", column="ID"),
			@Result(property="roleName", column="ROLE_NAME"),
			@Result(property="description", column="DESCRIPTION"),
	})
	@Insert(CREATE_ROLE)
	@Options(useGeneratedKeys = true, keyProperty = "roleId")
	public void addRole(Role role);
	
	@Results(value={
			@Result(javaType=Role.class),
			@Result(property="roleId", column="ID"),
			@Result(property="roleName", column="ROLE_NAME"),
			@Result(property="description", column="DESCRIPTION"),
	})
	@Update(UPDATE_ROLE)
	public void updateRole(Role role);
	
	@Delete(DELETE_ROLE)
	public void deleteRole(Integer roleId);
	
	@Delete(DELETE_ROLE_ACTION)
	public void deleteRoleAction(Integer roleId);
	
	@Results(value={
			@Result(javaType=RoleAction.class),
			@Result(property="roleId", column="ID"),
			@Result(property="actionId", column="ACTION_ID")
	})
	@Lang(XMLLanguageDriver.class)
	@Insert(CREATE_ROLE_ACTION)
	public void addRoleActions(@Param("role") Role role);
	
	@Results(value={
			@Result(javaType=RoleAction.class),
			@Result(property="roleId", column="ID"),
			@Result(property="actionId", column="ACTION_ID")
	})
	@Lang(XMLLanguageDriver.class)
	@Update(UPDATE_ROLE_ACTION)
	public void updateRoleActions(@Param("role") Role role);
	
	@Select(GET_ROLE_BY_NAME)
	public String isUniqname(String roleName);
	
	@Results(value = {
	@Result(property="actionId", column="action_id")		
	})
	@Select(GET_ACTION_BY_ROLE_ID)
	List<Action> selectActionByRoleId(Integer roleId);
	
}
