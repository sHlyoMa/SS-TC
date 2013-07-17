package service;

import java.util.List;

import domain.Project;

public interface ProjectService {
	public Project getProjectById(Integer projectId) throws Exception;
	public List<Project> projectList() throws Exception;
	public List<Project> littleProjectList() throws Exception;
	public Integer getProjectIdByProject(Project project) throws Exception;
	public void addProject(Project project) throws Exception;
	public void editProject(Project project, Integer projectId) throws Exception;
	public void deleteProject(Integer projectId) throws Exception;
	public Integer deleteProjectAjax(Integer projectId) throws Exception;
	public String getProjectNameByProjectId(Integer projectId);
	public String checkIfUniqueName(String name);
}
