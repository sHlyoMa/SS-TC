package domain;

import java.util.List;

public class Project {
	private Integer projectId;
	private Integer pmUserId;
	private UserInfo pmUserFullName;
	private List<UserProject> team;
	private String name;
	private String description;
	private UserInfo userProject;
	private UserInfo userProjectCurrent;
	
	private Integer[] userId;
	
	public UserInfo getUserProjectCurrent() {
		return userProjectCurrent;
	}

	public void setUserProjectCurrent(UserInfo userProjectCurrent) {
		this.userProjectCurrent = userProjectCurrent;
	}
	
	public Integer[] getUserId() {
		return userId;
	}

	public void setUserId(Integer[] userId) {
		this.userId = userId;
	}
	
	public UserInfo getUserProject() {
		return userProject;
	}

	public void setUserProject(UserInfo userProject) {
		this.userProject = userProject;
	}
	
	public List<UserProject> getTeam() {
		return team;
	}

	public void setTeam(List<UserProject> team) {
		this.team = team;
	}

	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public Integer getPmUserId() {
		return pmUserId;
	}
	
	public void setPmUserId(Integer pmUserId) {
		this.pmUserId = pmUserId;
	}
	
	public UserInfo getPmUserFullName() {
		return pmUserFullName;
	}

	public void setPmUserFullName(UserInfo pmUserFullName) {
		this.pmUserFullName = pmUserFullName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
