package domain;

import java.util.List;

public class Role {
	private Integer roleId;
	private String roleName;
	private String description;
	private List<Role> roleList;
	private List<Action> actionList;
	private List<Integer> selectedActionList;
	

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Integer> getSelectedActionList() {
		return selectedActionList;
	}

	public void setSelectedActionList(List<Integer> selectedActionList) {
		this.selectedActionList = selectedActionList;
	}


}
