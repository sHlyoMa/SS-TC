package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import dao.IssueMapper;
import domain.Issue;
import domain.UserInfo;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueMapper issueMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private CommentService commentService;
	@Autowired
	private StringSplitter stringSplitter;

	enum IssueStatus {
		Open(1), InProgress(2), Resolved(3), Closed(4), Reopened(5);
		private Integer buttonNumber;

		private IssueStatus(Integer buttonNumber) {
			this.buttonNumber = buttonNumber;
		}

		public Integer getIssueStatusId() {
			return buttonNumber;
		}
	}
	
	@Override
	@Transactional
	public void addIssue(Issue issue) throws Exception {
		issue.setReporterId(userService.getUserByLogin().getUserId());
		Integer issueNumber = issueMapper.getCountOfProjectIssueByType(
				issue.getTypeId(), issue.getProjectId()) + 1;

		switch (issue.getTypeId()) {
		case 1:
			issue.setKey("T-" + issue.getProjectId() + "-" + issueNumber);
			break;
		case 2:
			issue.setKey("B-" + issue.getProjectId() + "-" + issueNumber);
			break;
		}
		issue.setStatusId(IssueStatus.Open.getIssueStatusId());
		issue.setName(stringSplitter.splitter(issue.getName(), 30));
		issue.setDescription(stringSplitter.splitter(issue.getDescription(), 30));
		issueMapper.addIssue(issue);
	}

	@Override
	@Transactional
	public void updateIssue(Issue issue, Integer issueId) throws Exception {
		issue.setIssueId(issueId);
		issue.setName(stringSplitter.splitter(issue.getName(), 30));
		issue.setDescription(stringSplitter.splitter(issue.getDescription(), 30));
		issueMapper.updateIssue(issue);
	}

	@Override
	@Transactional
	public void removeIssue(Integer issueId) {
		issueMapper.removeIssues(issueId);
	}
	
	@Override
	@Transactional
	public Issue getIssueById(Integer issueId) {
		return issueMapper.getIssueById(issueId);
	}

	@Override
	@Transactional
	public Issue getIssueDetails(Integer issueId) {
		return issueMapper.getIssueDetails(issueId);
	}

	@Override
	@Transactional
	public List<Issue> getMyIssueList(Integer typeId, Integer statusId,
			Integer priorityId, Integer environmentId, String blocked, String search, Integer pageNumber, Integer limit,
			Integer columnNumber, Integer direction) {
		Integer page = pageNumber * limit - limit;
		Integer myId = userService.getUserByLogin()
				.getUserId();
		if(search != null && search != ""){
			search = myBatisSspecialCharacter(search);
		}
		return issueMapper.getMyIssueList(myId, myId, typeId, statusId, priorityId, environmentId, blocked, "%"+search+"%",
				page, limit, columnNumber, direction);
	}

	@Override
	@Transactional
	public List<Issue> getIssueByProjectId(Integer projectId, Integer typeId,
			Integer statusId, Integer priorityId, Integer environmentId, String blocked, String search,
			Integer pageNumber, Integer limit, Integer columnNumber, Integer direction) {
		Integer page = pageNumber * limit - limit;
		if(search != null && search != ""){
			search = myBatisSspecialCharacter(search);
		}
		return issueMapper.getIssueByProjectId(projectId, typeId, statusId,
				priorityId, environmentId, blocked, "%"+search+"%", page, limit, columnNumber, direction);
	}

	@Override
	@Transactional
	public Integer getCountOfProjectIssue(Integer projectId, Integer typeId,
			Integer statusId, Integer priorityId, Integer environmentId, String blocked, String search) {
		if(search != null && search != ""){
			search = myBatisSspecialCharacter(search);
		}
		return issueMapper.getCountOfProjectIssue(projectId, typeId, statusId,
				priorityId, environmentId, blocked, "%"+search+"%");
	}

	@Override
	@Transactional
	public Integer getCountOfMyIssue(Integer typeId, Integer statusId,
			Integer priorityId, Integer environmentId, String blocked, String search) {
		Integer myId = userService.getUserByLogin()
				.getUserId();
		if(search != null && search != ""){
			search = myBatisSspecialCharacter(search);
		}
		return issueMapper.getCountOfMyIssue(myId, myId, typeId, statusId, priorityId, environmentId, blocked, "%"+search+"%");
	}
	
	private String myBatisSspecialCharacter(String string){
		return string.replace("\\", "\\\\").replace("_", "\\_").replace("%", "\\"+"%");
	}
	
	
	@Override
	@Transactional
	public void assignToSomeBody(final Integer issueId, Integer assignerId)
			throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setAssignerId(assignerId);
		issueMapper.updateIssue(issue);
		final UserInfo sender = userService.getUserByLogin().getUserInfo();
		new Thread(new Runnable() {
		    public void run() {
		    	doSendEmail(issueId, sender);
		    }
		}).start();
		
	}

	@Override
	@Transactional
	public void assignToMe(Integer issueId) throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setAssignerId(userService.getUserByLogin().getUserId());
		issueMapper.updateIssue(issue);
	}


	@Override
	@Transactional
	public void startProgress(Integer issueId) throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setStatusId(IssueStatus.InProgress.getIssueStatusId());
		issueMapper.updateIssue(issue);
	}

	@Override
	@Transactional
	public void resolveIssue(Integer issueId) throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setStatusId(IssueStatus.Resolved.getIssueStatusId());
		issueMapper.updateIssue(issue);
	}

	@Override
	@Transactional
	public void closeIssue(Integer issueId) throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setStatusId(IssueStatus.Closed.getIssueStatusId());
		issueMapper.updateIssue(issue);
		issueMapper.setCloseDate(issueId);
	}

	@Override
	@Transactional
	public void reopenIssue(Integer issueId) throws Exception {
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setStatusId(IssueStatus.Reopened.getIssueStatusId());
		issue.setAssignerId(null);
		issue.setCloseDate(null);
		issueMapper.updateIssue(issue);
	}
	
	@Override
	@Transactional
	public void resetIssue(Integer issueId) throws Exception{
		Issue issue = issueMapper.getIssueById(issueId);
		issue.setStatusId(IssueStatus.Open.getIssueStatusId());
		issue.setAssignerId(null);
		issueMapper.updateIssue(issue);
	}
	
	@Override
	@Transactional
	public void blockIssue(Integer issueId) throws Exception{
		Issue issue = issueMapper.getIssueById(issueId);
		if(issue.getBlocked()!=null) issue.setBlocked(null);
		else issue.setBlocked("(blocked)");
		issueMapper.updateIssue(issue);
	}
	

	public void doSendEmail(Integer issueId, UserInfo sender) {
		Issue issue = issueMapper.getIssueDetails(issueId);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(issue.getUserAssigner().getEmail());
		email.setSubject("Bug tracking system || new task");
		email.setText(sender.getFirstName() + " " + sender.getSecondName()
				+ " " + "assign to you new " + issue.getType().getType()
				+ " with priority: " + issue.getPriority().getPriority()
				+ " in the project: " + issue.getProject().getName() + "."
				+ " Please visit Bug tracking system to find the details.");
		mailSender.send(email);
	}

}

