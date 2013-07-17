package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserProjectMapper;

import domain.UserProject;

@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectService {
	@Autowired
	UserProjectMapper userProjectMapper;
	
	public UserProject getUserProjectByUserProjectId(Integer userProjectId) throws Exception {
		return userProjectMapper.getUserProjectByUserProjectId(userProjectId);
	}
	
	public UserProject getUserProjectByUserProject(UserProject userProject) throws Exception {
		return userProjectMapper.getUserProjectByUserProject(userProject);
	}
	
	@Transactional
	public List<UserProject> userProjectIdsByProjectId(Integer projectId) throws Exception {
		return userProjectMapper.userProjectIdsByProjectId(projectId);
	}
	
	@Transactional
	public List<UserProject> userProjectListByProjectId(Integer projectId) throws Exception {
		return userProjectMapper.userProjectListByProjectId(projectId);
	}
	
	@Transactional
	public void addUserProject(UserProject userProject) throws Exception {
		userProjectMapper.addUserProject(userProject);
	}

	@Transactional
	public void deleteUserProject(Integer userProjectId) throws Exception {
		userProjectMapper.deleteUserProject(userProjectId);
	}
}
