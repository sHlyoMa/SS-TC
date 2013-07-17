package web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import domain.Role;

import service.ActionService;
import service.RoleService;
import service.UserService;
import validator.RoleValidator;

@SessionAttributes("info")
@RequestMapping(value="/role")
@Controller
public class RoleController {

	private List<Integer> selectedActionId;
	
	@Autowired
	RoleService roleService;
	@Autowired
	ActionService actionService;
	@Autowired
	RoleValidator roleValidator;
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasPermission(#user, 'View roles')")
	@RequestMapping("")
	public String roleList(Model model, HttpServletRequest request){
		model.addAttribute("info", userService.getUserByLogin());
		model.addAttribute("role", new Role());
		model.addAttribute("roleList",roleService.roleList());
		return "/role/list";
	}

	@PreAuthorize("hasPermission(#user, 'Add role')")
	@RequestMapping("/add")
	public String addRole( Model model){
		model.addAttribute("role", new Role());
		model.addAttribute("actionList", actionService.actionList());
		model.addAttribute("roleList",roleService.roleList());
		return "/role/add";
	}
	
	@RequestMapping(value="/actions", method = RequestMethod.POST)
	public @ResponseBody void getAction(@RequestParam(value="actionsId[]") Integer[] actionsId){
		selectedActionId = Arrays.asList(actionsId);
	}
	
	@PreAuthorize("hasPermission(#user, 'Add role')")
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addingRole(Model model, @ModelAttribute Role role, 
			BindingResult result){
		roleValidator.validate(role, result);
		if(result.hasErrors()){
			model.addAttribute("roleList",roleService.roleList());
			model.addAttribute("actionList", actionService.actionList());
			return "/role/add";
		}
		roleService.addRole(role,selectedActionId);
		return "redirect:/role";
	}
	
	@PreAuthorize("hasPermission(#user, 'Delete role')")
	@RequestMapping("/delete/{roleId}")
	public String deleteRole(@PathVariable Integer roleId){
		roleService.deleteRole(roleId);
		return "redirect:/role";
	}
	
	@PreAuthorize("hasPermission(#user, 'Edit role')")
	@RequestMapping("/edit/{roleId}")
	public String editRole(@PathVariable Integer roleId, Model model){
		model.addAttribute("addOrEdit", "edit");
		model.addAttribute("roleList",roleService.roleList());
		model.addAttribute("role",  roleService.getRoleById(roleId));
		model.addAttribute("actionList", actionService.actionList());
		return "/role/add";
	}
	
	@PreAuthorize("hasPermission(#user, 'Edit role')")
	@RequestMapping(value="/edit/{roleId}", method = RequestMethod.POST )
	public String edditingRole(@ModelAttribute Role role, @PathVariable Integer roleId, BindingResult result, Model model){
		role.setRoleId(roleId);
		roleValidator.validate(role, result);
		if(result.hasErrors()){
			model.addAttribute("addOrEdit", "edit");
			model.addAttribute("roleList",roleService.roleList());
			model.addAttribute("actionList", actionService.actionList());
			return "/role/add";
		}
		roleService.updateRole(role, selectedActionId);
		return "redirect:/role";
	}
	
	@PreAuthorize("hasPermission(#user, 'View roles')")
	@RequestMapping("/details/{roleId}")
	public String detailsRole(Model model, @PathVariable Integer roleId){
		model.addAttribute("roleList",roleService.roleList());
		model.addAttribute("roleId", roleId);
		model.addAttribute("role", roleService.getRoleById(roleId));
		return "/role/details";
	}
}
