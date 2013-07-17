package service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjectMapper;
import domain.Project;
import domain.UserProject;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private UserProjectService userProjectService;
	@Autowired
	private StringSplitter stringSplitter;

	@Transactional
	public Integer getProjectIdByProject(Project project) throws Exception {
		return projectMapper.getProjectIdByProject(project);
	}
	
	@Transactional
	public Project getProjectById(Integer projectId) throws Exception {
		return projectMapper.getProjectById(projectId);
	}

	@Transactional
	public List<Project> projectList() throws Exception {
		List<Project> leaderProjectList = projectMapper.pmProjectList(userService.getUserByLogin().getUserId());
		List<Project> inTeamProjectList = projectMapper.teamProjectList(userService.getUserByLogin().getUserId());
		leaderProjectList.addAll(inTeamProjectList);
		return leaderProjectList;
	}
	
	@Transactional
	public List<Project> littleProjectList() throws Exception {
		return projectMapper.littleProjectList();
	}
	
	@Transactional
	public void addProject(Project project) throws Exception {
		project.setPmUserId(userService.getUserByLogin().getUserId());
		project.setName(stringSplitter.splitter(project.getName(), 20));
		project.setDescription(stringSplitter.splitter(project.getDescription(), 20));
		projectMapper.addProject(project);
		
		project.setProjectId(getProjectIdByProject(project));
		        
        UserProject userProjectObject = new UserProject();

        for (int i = 0; i < project.getUserId().length; i++) {
        	userProjectObject.setUserId(project.getUserId()[i]);
        	userProjectObject.setProjectId(project.getProjectId());
        	userProjectService.addUserProject(userProjectObject);
        }
	}
	
	@Transactional
	public void editProject(Project project, Integer projectId) throws Exception {
		project.setProjectId(projectId);
		
		List<UserProject> userProjectList = userProjectService.userProjectIdsByProjectId(projectId);
		ListIterator<UserProject> iterator = userProjectList.listIterator();
		
		UserProject userProjectObjectToAdd = new UserProject();
		
		boolean checkIn = false;
		
		while (iterator.hasNext()) {
			UserProject userProjectObject = iterator.next();
			checkIn = true;
			for (int i = 0; i < project.getUserId().length; i++) {
	        	if (userProjectObject.getUserId() == project.getUserId()[i]) checkIn = false;
	        }
			if (checkIn) userProjectService.deleteUserProject(userProjectObject.getUserProjectId());
		}
		
		for (int i = 0; i < project.getUserId().length; i++) {
			checkIn = true;
			iterator = userProjectList.listIterator();
			while(iterator.hasNext()) {
				UserProject userProjectObject = iterator.next();
				if (userProjectObject.getUserId() == project.getUserId()[i]) checkIn = false;
			}
			if (checkIn) {
				userProjectObjectToAdd.setUserId(project.getUserId()[i]);
				userProjectObjectToAdd.setProjectId(project.getProjectId());
		    	userProjectService.addUserProject(userProjectObjectToAdd);
			}
        }
		
		project.setName(stringSplitter.splitter(project.getName(), 20));
		project.setDescription(stringSplitter.splitter(project.getDescription(), 20));
        
        projectMapper.editProject(project);
	}

	@Transactional
	public void deleteProject(Integer projectId) throws Exception {
		List<UserProject> userProjectList = userProjectService.userProjectIdsByProjectId(projectId);

		ListIterator<UserProject> iterator = userProjectList.listIterator();
		while(iterator.hasNext()){
			UserProject userProjectObject = iterator.next();
			userProjectService.deleteUserProject(userProjectObject.getUserProjectId());
		}
		
		projectMapper.deleteProject(projectId);
	}
	
	@Transactional
	public Integer deleteProjectAjax(Integer projectId) throws Exception {
		List<UserProject> userProjectList = userProjectService.userProjectIdsByProjectId(projectId);

		ListIterator<UserProject> iterator = userProjectList.listIterator();
		while(iterator.hasNext()){
			UserProject userProjectObject = iterator.next();
			userProjectService.deleteUserProject(userProjectObject.getUserProjectId());
		}
		
		projectMapper.deleteProject(projectId);
		return projectId;
	}
	
	public String getProjectNameByProjectId(Integer projectId) {
		return projectMapper.getProjectNameByProjectId(projectId);
	}
	
	@Transactional
	public String checkIfUniqueName(String name) {
		return projectMapper.checkIfUniqueName(name);
	}
	
}
