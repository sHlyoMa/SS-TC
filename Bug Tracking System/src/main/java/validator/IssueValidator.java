package validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import domain.Issue;

@Component
public class IssueValidator extends AbstractValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Issue.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Issue issue = (Issue) object;

		if (isNotNull(issue.getName())) {
			errors.rejectValue("name", "label.issue.name.required");
		}
		if (isNotValidLength(issue.getName(),2,50)) {
			errors.rejectValue("name", "label.issue.name.size");
		}

		if (isNotNull(issue.getDescription())) {
			errors.rejectValue("description", "label.issue.description.required");
		} 
		if (isNotValidLength(issue.getDescription(),10,240)) {
			errors.rejectValue("description", "label.issue.description.size");
		}
		
	}

}
