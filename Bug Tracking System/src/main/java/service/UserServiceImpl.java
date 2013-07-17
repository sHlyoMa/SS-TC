package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserMapper;
import domain.Action;
import domain.Role;
import domain.User;
import domain.UserInfo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleService roleService;

	@Transactional
	public List<User> listUser(Integer page, Integer columnNumber,
			Integer direction, Integer limit) {
		Integer pageNumber = page * limit - limit;
		return userMapper.listUser(pageNumber, columnNumber, direction, limit);
	}
	
	public List<User> getUsersByRole(Integer roleId, Integer page, Integer columnNumber,
			Integer direction, Integer limit){
		Integer pageNumber = page * limit - limit;
		return  userMapper.getUsersByRole(roleId, pageNumber, columnNumber, direction, limit);
	}

	@Transactional
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Transactional
	public void removeUser(Integer userId) {
		userMapper.removeUser(userId);
	}

	@Transactional
	public void editUser(User user) {
		userMapper.editUser(user);
	}

	@Transactional
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Transactional
	public UserInfo getUserInfo(Integer id) {
		return userMapper.getUserInfo(id);
	}

	@Transactional
	public Role getUserRole(Integer id) {
		return userMapper.getUserRole(id);
	}

	@Transactional
	public Integer getUserId(User user) {
		return userMapper.getUserId(user);
	}

	@Transactional
	public Integer getUserRoleByName(String name) {
		return userMapper.getUserRoleByName(name);
	}

	@Transactional
	public User getUserByLogin() {
		User user = userMapper.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());		
		user.setRole(roleService.getRoleById(user.getRoleId()));		
		return user;
	}

	@Transactional
	public Integer getUserIdByName(String name) {
		return userMapper.getUserIdByName(name);
	}

	@Transactional
	public String uniqueLogin(String name) {
		return userMapper.uniqueLogin(name);
	}

	@Transactional
	public String getLoginById(Integer id) {
		return userMapper.getLoginById(id);
	}

	@Transactional
	public String authorize(String userName, String password) {
		return userMapper.authorize(userName, password);
	}

	@Transactional
	public Integer getUserCount() {
		return userMapper.getUserCount();
	}

	@Transactional
	public void editUserSamePass(User user) {
		userMapper.editUserSamePass(user);

	}

	@Transactional
	public String getDateByName(String name) {
		return userMapper.getDateByName(name);
	}

	@Transactional
	public Integer getPmProjectCount(Integer id) {
		return userMapper.getPmProjectCount(id);
	}

	@Transactional
	public Integer isUserActive(Integer id) {
		return userMapper.isUserActive(id);
	}

	@Transactional
	public void banUserById(Integer id) {
		userMapper.banUserById(id);
	}

	@Transactional
	public void unBanUserById(Integer id) {
		userMapper.unBanUserById(id);
	}

	@Transactional
	public String router() {
		User user;
		user = getUserByLogin();
		List<Action> actions = user.getRole().getActionList();
		List<String> haveActions = new ArrayList<String>();
		for (int i = 0; i < actions.size(); i++)
			if ((actions.get(i).getAction().equals("View issues")
					|| (actions.get(i).getAction().equals("View users")))
					|| (actions.get(i).getAction().equals("View roles"))
					|| (actions.get(i).getAction().equals("View projects"))) {
				haveActions.add(actions.get(i).getAction());
			}
		if (haveActions.contains("View issues")){
			return "redirect:/issue";
		}
		if (haveActions.contains("View users")){
			return "redirect:/user";
		}
		if (haveActions.contains("View roles")){			
			return "redirect:/role";
		}
		if (haveActions.contains("View projects")){			
			return "redirect:/project";
		}		
		return "accessdenied";
	}
	
	public Integer getCountOfRoleUsers(Integer roleId){
		return userMapper.getCountOfRoleUsers(roleId);
	}
	
	public Integer getCountOfInvolvedTeam (Integer userId){
		return userMapper.getCountOfInvolvedTeam(userId);
	}
}
