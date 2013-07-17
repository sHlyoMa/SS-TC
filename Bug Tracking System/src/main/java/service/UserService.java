package service;

import java.util.List;

import domain.Role;
import domain.User;
import domain.UserInfo;

public interface UserService {

	public List<User> listUser(Integer page, Integer columnNumber,
			Integer direction, Integer limit);

	List<User> getUsersByRole(Integer roleId, Integer page, Integer columnNumber,
			Integer direction, Integer limit);
	
	public void addUser(User user);

	public void removeUser(Integer userId);

	public void editUser(User user);

	public User getUserById(Integer userId);

	public UserInfo getUserInfo(Integer id);

	public Role getUserRole(Integer id);

	public Integer getUserId(User user);

	public Integer getUserRoleByName(String name);

	public User getUserByLogin();

	public Integer getUserIdByName(String name);

	public String uniqueLogin(String name);

	public String getLoginById(Integer id);

	public String authorize(String userName, String password);

	public Integer getUserCount();

	public void editUserSamePass(User user);

	public String getDateByName(String name);
	
	public Integer getPmProjectCount(Integer id);
	
	public Integer isUserActive (Integer id);
	
	public void banUserById (Integer id);
	
	public void unBanUserById (Integer id);
	
	public String router ();
	
	public Integer getCountOfRoleUsers(Integer roleId); 
	
	public Integer getCountOfInvolvedTeam (Integer userId);
}
