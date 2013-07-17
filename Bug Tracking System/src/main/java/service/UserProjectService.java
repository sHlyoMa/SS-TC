package service;

import java.util.List;

import domain.UserProject;

public interface UserProjectService {
	public UserProject getUserProjectByUserProjectId(Integer userProjectId) throws Exception;
	public UserProject getUserProjectByUserProject(UserProject userProject) throws Exception;
	public List<UserProject> userProjectIdsByProjectId(Integer projectId) throws Exception;
	public List<UserProject> userProjectListByProjectId(Integer projectId) throws Exception;
	public void addUserProject(UserProject userProject) throws Exception;
	public void deleteUserProject(Integer userProjectId) throws Exception;
}
