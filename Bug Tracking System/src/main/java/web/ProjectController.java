package web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ProjectService;
import service.UserProjectService;
import service.UserInfoService;
import service.RoleService;
import service.UserService;

import domain.Project;
import validator.ProjectValidator;

@Controller
@SessionAttributes("info")
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
    private ProjectService projectService;
	@Autowired 
	private UserInfoService userInfoService;
	@Autowired 
	private UserProjectService userProjectService;
	@Autowired 
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("projectValidator")
    private ProjectValidator projectValidator;
	
	private StringBuffer redirectPath;

	private void setRedirectPath(StringBuffer redirectPath){
		this.redirectPath=redirectPath;
	}
	
	@PreAuthorize("hasPermission(#user, 'View projects')")
	@RequestMapping(value={""})
    public String pmProjectList(HttpServletRequest request, Model model) throws Exception {

		model.addAttribute("project", new Project());
		model.addAttribute("projectList", projectService.projectList());
		model.addAttribute("info", userService.getUserByLogin());
		setRedirectPath(request.getRequestURL());
		
        return "/project/list";
    }

	@PreAuthorize("hasPermission(#user, 'View projects')")
    @RequestMapping("/details/{projectId}")
    public String getProjectById(HttpServletRequest request, Model model, @PathVariable("projectId") Integer projectId) throws Exception {
        
    	model.addAttribute("project", projectService.getProjectById(projectId));
    	model.addAttribute("team", userProjectService.userProjectListByProjectId(projectId));
    	model.addAttribute("projectList", projectService.projectList());
    	setRedirectPath(request.getRequestURL());
        
        return "/project/details";
    }
    
    @PreAuthorize("hasPermission(#user, 'Add project')") 
    @RequestMapping(value = "/add", method = RequestMethod.GET)
	public String createAddForm(Model model) throws Exception{
    	
    	model.addAttribute("projectList", projectService.projectList());
	    model.addAttribute("project", new Project());
	    model.addAttribute("userProject", userInfoService.userTeamListWithoutLeaderUser());
	    
	    return "/project/addAndEdit";
	}
    
    @PreAuthorize("hasPermission(#user, 'Add project')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProject(Model model, @ModelAttribute("project") Project project, BindingResult result) throws Exception {
    	
    	model.addAttribute("projectList", projectService.projectList());
    	model.addAttribute("userProject", userInfoService.userTeamListWithoutLeaderUser());
    	
    	projectValidator.validate(project, result);
    	
        if (result.hasErrors()) {
    		return "/project/addAndEdit";
        }

        projectService.addProject(project);
        
        return "redirect:/project";
    }
    
    @PreAuthorize("hasPermission(#user, 'Edit project')")
    @RequestMapping(value="/edit/{projectId}", method = RequestMethod.GET)
	public String getProjectId(Model model, @PathVariable("projectId") Integer projectId) throws Exception {
		
    	Project project = projectService.getProjectById(projectId);
    	model.addAttribute("projectList", projectService.projectList());
    	model.addAttribute("project", project);
    	model.addAttribute("userProject", userInfoService.userTeamList(project.getPmUserId()));
    	model.addAttribute("userProjectCurrent", userInfoService.userTeamListByProjectId(projectId));
    	model.addAttribute("showEditView", "yes");
    	
		return "/project/addAndEdit";
	}
	
    @PreAuthorize("hasPermission(#user, 'Edit project')")
	@RequestMapping(value="/edit/{projectId}", method = RequestMethod.POST)
	public String editProject(Model model, @ModelAttribute("project") Project project, @PathVariable("projectId") Integer projectId, BindingResult result) throws Exception {
		
    	model.addAttribute("projectList", projectService.projectList());
    	model.addAttribute("userProject", userInfoService.userTeamList(project.getPmUserId()));
    	model.addAttribute("userProjectCurrent", userInfoService.userTeamListByProjectId(projectId));
    	model.addAttribute("showEditView", "yes");
    	
        projectValidator.validate(project, result);
        
    	if (result.hasErrors()) {
    		return "/project/addAndEdit";
        }
    	
		projectService.editProject(project, projectId);
		return "redirect:" + redirectPath;
	}

    @PreAuthorize("hasPermission(#user, 'Delete project')") 
    @RequestMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable("projectId") Integer projectId) throws Exception {
    	
        projectService.deleteProject(projectId);
        return "redirect:/project";
    }
    
    @PreAuthorize("hasPermission(#user, 'Delete project')") 
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody Integer deleteProjectAjax(@RequestParam("projectId") Integer projectId) throws Exception {
           
        return projectService.deleteProjectAjax(projectId);
    }
}
