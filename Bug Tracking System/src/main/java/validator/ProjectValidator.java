package validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import service.ProjectService;

import domain.Project;

@Component(value = "projectValidator")
public class ProjectValidator implements Validator {
		
	@Resource(name = "projectService")
	ProjectService projectService;
	
	@Override
    public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Project project = (Project) target;

    	if (project.getProjectId() == null) {
	    	if((project.getName() == null) || (project.getName().equals(""))) {
	    		errors.rejectValue("name", "project.name.required");
			} else if (project.getName().length() > 45) {
				errors.rejectValue("name", "project.name.size");
			} else if (projectService.checkIfUniqueName(project.getName()) != null) errors.rejectValue("name", "project.name.unique");
	    	
	    if ((project.getDescription() == null) || (project.getDescription().equals(""))) {
	    		errors.rejectValue("description", "project.description.required");
			} else if (project.getDescription().length() > 240) {
		    	errors.rejectValue("description", "project.description.size");
			}
	    	
    	} else {
    		try {
    			Project oldProject = projectService.getProjectById(project.getProjectId());
    			if((project.getName() == null) || (project.getName().equals(""))) {
    	    		errors.rejectValue("name", "project.name.required");
    			} else if (project.getName().length() > 240) {
    				errors.rejectValue("name", "project.name.size");
    			} else if ((!oldProject.getName().equals(project.getName())) && (projectService.checkIfUniqueName(project.getName()) != null)) {
    				errors.rejectValue("name", "project.name.unique");
    			}
    		} catch (Exception e) {
    			System.out.println(e);
    			if((project.getName() == null) || (project.getName().equals(""))) {
    	    		errors.rejectValue("name", "project.name.required");
    			} else if (project.getName().length() > 45) {
    				errors.rejectValue("name", "project.name.size");
    			}
    		}
    	}
    }

}
