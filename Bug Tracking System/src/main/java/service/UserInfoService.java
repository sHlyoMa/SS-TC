package service;

import java.util.List;

import domain.User;
import domain.UserInfo;




public interface UserInfoService {

	public void addUserInfo(User user);
	public void deleteUserInfo(Integer id);
	public void updateUserInfo(User user);
	public List<UserInfo> assignerUserList();
	public List<UserInfo> userTeamList(Integer pmUserId);
	public List<UserInfo> userTeamListWithoutLeaderUser();
	public List<UserInfo> userTeamListByProjectId(Integer projectId);
}
