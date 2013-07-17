package service;

import java.util.List;

import domain.Role;

public interface RoleService {

	List<Role> roleList();
	void addRole(Role role, List<Integer> selectedActions);
	void updateRole(Role role, List<Integer> selectedActions);
	void deleteRole(Integer roleId);
	Role getRoleById(Integer roleId);
	String isUniqname(String roleName);
	Role getMyRole();
	
	
}
