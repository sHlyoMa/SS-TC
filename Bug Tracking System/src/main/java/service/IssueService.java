package service;

import java.util.List;

import domain.Issue;
import domain.UserInfo;

public interface IssueService {

	void addIssue(Issue issue) throws Exception;

	void removeIssue(Integer id);
	
	void updateIssue(Issue issue, Integer issueId) throws Exception;

	Issue getIssueById(Integer id);

	Issue getIssueDetails(Integer id);

	List<Issue> getMyIssueList(Integer typeId, Integer statusId,
			Integer priorityId, Integer environmentId, String blocked, String search, Integer pageNumber,
			Integer limit, Integer columnNumber, Integer direction);

	List<Issue> getIssueByProjectId(Integer projectId, Integer typeId,
			Integer statusId, Integer priorityId, Integer environmentId, String blocked, String search,
			Integer pageNumber, Integer limit, Integer columnNumber,
			Integer direction);

	Integer getCountOfProjectIssue(Integer projectId, Integer typeId,
			Integer statusId, Integer priorityId, Integer environmentId, String blocked, String search);

	Integer getCountOfMyIssue(Integer typeId, Integer statusId,
			Integer priorityId, Integer environmentId, String blocked, String search);

	public void assignToSomeBody(Integer issueId, Integer assignerId)
			throws Exception;

	void assignToMe(Integer issueId) throws Exception;

	void startProgress(Integer issueId) throws Exception;

	void resolveIssue(Integer issueId) throws Exception;

	void closeIssue(Integer issueId) throws Exception;

	void reopenIssue(Integer issueId) throws Exception;

	void resetIssue(Integer issueId) throws Exception;

	void blockIssue(Integer issueId) throws Exception;

	void doSendEmail(Integer issueId, UserInfo sender);

}
