package security;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import service.RoleService;
import service.UserService;

import domain.Action;
import domain.Role;
import domain.User;

public class BasePermissionEvaluator implements PermissionEvaluator {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		boolean hasPermission = false;   
		if ( authentication != null &&  permission instanceof String) {      
			User user = userService.getUserByLogin();   
			Role role = roleService.getRoleById(user.getRoleId());
			List<Action> actionList = role.getActionList();
			ListIterator<Action> iterator = actionList.listIterator();
			
			while (iterator.hasNext()) {
				Action action = iterator.next();
				if (action.getAction().equals(permission)) hasPermission = true;
			}
		} else {    
			hasPermission = false;    
		}   
		return hasPermission; 
	}

	@Override    
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Id and Class permissions are not supperted by " + this.getClass().toString());
    }
}
