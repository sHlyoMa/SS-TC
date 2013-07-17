package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserInfoMapper;
import domain.User;
import domain.UserInfo;
//import service.UserService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	private UserService userService;
	
	@Transactional
	public void addUserInfo(User user){
		
		userInfoMapper.addUserInfo(user);
	}
	
	@Transactional
	public void deleteUserInfo(Integer id){
		userInfoMapper.deleteUserInfo(id);
	}
	
	@Transactional
	public void updateUserInfo(User user){
		userInfoMapper.updateUserInfo(user);
	}
	
	@Transactional
	public List<UserInfo> assignerUserList(){
		return userInfoMapper.assignerUserList();
	}
	
	@Transactional
	public List<UserInfo> userTeamList(Integer pmUserId){
		return userInfoMapper.userTeamList(pmUserId);
	}
	
	@Transactional
	public List<UserInfo> userTeamListWithoutLeaderUser(){
		return userInfoMapper.userTeamListWithoutLeaderUser(userService.getUserByLogin().getUserId());
	}
	
	@Transactional
	public List<UserInfo> userTeamListByProjectId(Integer projectId){
		return userInfoMapper.userTeamListByProjectId(projectId);
	}
}
