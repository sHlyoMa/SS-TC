package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RoleMapper;
import domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserService userService;
	@Autowired
	private StringSplitter stringSplitter;

	public List<Role> roleList() {
		return roleMapper.roleList();
	}

	public void addRole(Role role, List<Integer> selectedActions) {
		role.setRoleName(stringSplitter.splitter(role.getRoleName(), 30));
		role.setDescription(stringSplitter.splitter(role.getDescription(), 30));
		roleMapper.addRole(role);
		role.setSelectedActionList(selectedActions);
		roleMapper.addRoleActions(role);
	}

	public void updateRole(Role role, List<Integer> selectedActions) {
		role.setSelectedActionList(selectedActions);
		role.setRoleName(stringSplitter.splitter(role.getRoleName(), 30));
		role.setDescription(stringSplitter.splitter(role.getDescription(), 30));
		roleMapper.updateRole(role);
		roleMapper.deleteRoleAction(role.getRoleId());
		roleMapper.addRoleActions(role);
	}

	public void deleteRole(Integer roleId) {
		roleMapper.deleteRole(roleId);
	}

	public Role getRoleById(Integer roleId) {
		return roleMapper.getRoleByid(roleId);
	}

	public String isUniqname(String roleName) {
		return roleMapper.isUniqname(roleName);
	}

	public Role getMyRole() {
		return getRoleById(userService.getUserByLogin().getRoleId());
	}

}
