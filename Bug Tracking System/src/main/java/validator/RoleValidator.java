package validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import service.RoleService;

import domain.Role;

@Component
public class RoleValidator extends AbstractValidator{

	@Autowired
	RoleService roleService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Role role = (Role) object;

		if (isNotNull(role.getRoleName())) {
			errors.rejectValue("roleName", "label.role.name.required");
		}
		if (isNotValidLength(role.getRoleName(), 2, 50)) {
			errors.rejectValue("roleName", "label.role.name.size");
		}
		if (role.getRoleId() == null) {
			if (roleService.isUniqname(role.getRoleName()) != null) {
				errors.rejectValue("roleName", "label.role.name.uniq");
			}
		}

		if (isNotNull(role.getDescription())) {
			errors.rejectValue("description", "label.role.description.required");
		}
		if (isNotValidLength(role.getDescription(), 2, 240)) {
			errors.rejectValue("description", "label.role.description.size");
		}
	}
}
