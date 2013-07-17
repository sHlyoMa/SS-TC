package domain;

public class UserProject {
	private Integer userProjectId;
	private Integer userId;
	private Integer projectId;
	private Integer roleId;
	private UserInfo userFullName;
	private Role userRole;

	public Integer getUserProjectId() {
		return userProjectId;
	}

	public void setUserProjectId(Integer userProjectId) {
		this.userProjectId = userProjectId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public UserInfo getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(UserInfo userFullName) {
		this.userFullName = userFullName;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
