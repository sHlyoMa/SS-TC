package web;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.support.RequestContextUtils;

import domain.Comment;
import domain.Environment;
import domain.Issue;
import domain.Priority;
import domain.Project;
import domain.Status;
import domain.Type;
import service.CommentService;
import service.EnvironmentService;
import service.IssueService;
import service.PriorityService;
import service.ProjectService;
import service.RoleService;
import service.StatusService;
import service.TypeService;
import service.UserInfoService;
import service.UserProjectService;
import service.UserService;
import validator.CommentValidator;
import validator.IssueValidator;

@Controller
@SessionAttributes("info")
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private PriorityService priorityService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private EnvironmentService environmentService;
	@Autowired
	private UserProjectService userProjectService;
	@Autowired
	private IssueValidator issueValidator;
	@Autowired
	private CommentValidator commentValidator;
	@Autowired
	private RoleService roleService;

	private StringBuffer redirectPath;

	private void setRedirectPath(StringBuffer redirectPath) {
		this.redirectPath = redirectPath;
	}

	static class IssueFilter {
		private Integer typeId;
		Integer statusId;
		Integer priorityId;
		Integer environmentId;
		String blocked;
		String search;

		public Integer getTypeId() {
			return typeId;
		}

		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
		}

		public Integer getStatusId() {
			return statusId;
		}

		public void setStatusId(Integer statusId) {
			this.statusId = statusId;
		}

		public Integer getPriorityId() {
			return priorityId;
		}

		public void setPriorityId(Integer priorityId) {
			this.priorityId = priorityId;
		}

		public Integer getEnvironmentId() {
			return environmentId;
		}

		public void setEnvironmentId(Integer environmentId) {
			this.environmentId = environmentId;
		}

		public String getBlocked() {
			return blocked;
		}

		public void setBlocked(String blocked) {
			this.blocked = blocked;
		}

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}

		public static IssueFilter issueFilter(Integer typeId, Integer statusId,
				Integer priorityId, Integer environmentId, String blocked,
				String search) {
			IssueFilter issueFilter = new IssueFilter();
			issueFilter.setTypeId(typeId);
			issueFilter.setStatusId(statusId);
			issueFilter.setPriorityId(priorityId);
			issueFilter.setEnvironmentId(environmentId);
			issueFilter.setBlocked(blocked);
			issueFilter.setSearch(search);
			return issueFilter;
		}

	}

	enum IssueDetailsButton {
		AddAndSave(1), Add(2);
		private Integer buttonNumber;

		private IssueDetailsButton(Integer buttonNumber) {
			this.buttonNumber = buttonNumber;
		}

		public Integer getButtonNumber() {
			return buttonNumber;
		}
	}

	@PreAuthorize("hasPermission(#user, 'View issues')")
	@RequestMapping("")
	private String redirectIssue() {
		return "redirect:/issue/sort=8-1&page=1&limit=10";
	}

	@PreAuthorize("hasPermission(#user, 'View issues')")
	@RequestMapping(value = "/sort={column}-{direction}&page={pageNumber}&limit={recordsLimit}")
	public String getMyIssueList(
			Model model,
			HttpServletRequest request,
			@ModelAttribute("issue") Issue issue,
			@PathVariable("pageNumber") Integer pageNumber,
			@PathVariable("recordsLimit") Integer recordsLimit,
			@PathVariable("column") Integer columnNumber,
			@PathVariable("direction") Integer direction,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "statusId", required = false) Integer statusId,
			@RequestParam(value = "priorityId", required = false) Integer priorityId,
			@RequestParam(value = "environmentId", required = false) Integer environmentId,
			@RequestParam(value = "blocked", required = false) String blocked,
			@RequestParam(value = "search", required = false) String search)
			throws Exception {
		recordsLimit = Math.abs(recordsLimit);
		model.addAttribute("info", userService.getUserByLogin());
		model.addAttribute("issueFilter", IssueFilter.issueFilter(typeId,
				statusId, priorityId, environmentId, blocked, null));
		model.addAttribute("issueList", issueService.getMyIssueList(typeId,
				statusId, priorityId, environmentId, blocked, search,
				pageNumber, recordsLimit, columnNumber, direction));
		model.addAttribute("filterFormAction", "issue/sort=" + columnNumber
				+ "-" + direction + "&page=1&limit=" + recordsLimit);
		model.addAttribute("projectList", projectService.projectList());
		model.addAttribute("issueCount", issueService.getCountOfMyIssue(typeId,
				statusId, priorityId, environmentId, blocked, search));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("recordsLimit", recordsLimit);
		setRedirectPath(request.getRequestURL());
		return ("/issue/list");
	}

	@PreAuthorize("hasPermission(#user, 'View issues')")
	@RequestMapping(value = "/projectId={projectId}/sort={column}-{direction}&page={pageNumber}&limit={recordsLimit}")
	public String getIssueListByProjectId(
			Model model,
			HttpServletRequest request,
			@PathVariable("projectId") Integer projectId,
			@PathVariable("pageNumber") Integer pageNumber,
			@PathVariable("recordsLimit") Integer recordsLimit,
			@PathVariable("column") Integer columnNumber,
			@PathVariable("direction") Integer direction,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "statusId", required = false) Integer statusId,
			@RequestParam(value = "priorityId", required = false) Integer priorityId,
			@RequestParam(value = "environmentId", required = false) Integer environmentId,
			@RequestParam(value = "blocked", required = false) String blocked,
			@RequestParam(value = "search", required = false) String search)
			throws Exception {
		recordsLimit = Math.abs(recordsLimit);
		model.addAttribute("issueCount", issueService.getCountOfProjectIssue(
				projectId, typeId, statusId, priorityId, environmentId,
				blocked, search));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("recordsLimit", recordsLimit);
		model.addAttribute("projectId", projectId);
		model.addAttribute("filterFormAction", "issue/projectId=" + projectId
				+ "/sort=" + columnNumber + "-" + direction + "&page=1&limit="
				+ recordsLimit);
		model.addAttribute("projectList", projectService.projectList());
		model.addAttribute("issueFilter", IssueFilter.issueFilter(typeId,
				statusId, priorityId, environmentId, blocked, null));
		model.addAttribute("issueList", issueService.getIssueByProjectId(
				projectId, typeId, statusId, priorityId, environmentId,
				blocked, search, pageNumber, recordsLimit, columnNumber,
				direction));
		setRedirectPath(request.getRequestURL());
		return ("/issue/list");
	}

	@PreAuthorize("hasPermission(#user, 'Delete issue')")
	@RequestMapping(value = "/delete/{issueId}")
	public String deleteIssue(@PathVariable Integer issueId) throws Exception {
		issueService.removeIssue(issueId);
		return "redirect:" + redirectPath;
	}

	@PreAuthorize("hasPermission(#user, 'Add issue')")
	@RequestMapping(value = "/projectId={projectId}/add", method = RequestMethod.GET)
	public String createAddForm(Model model,
			@PathVariable("projectId") Integer projectId,
			HttpServletRequest request) throws Exception {
		Locale loc = RequestContextUtils.getLocale(request);
		model.addAttribute("locale", loc);
		model.addAttribute("projectName",
				projectService.getProjectNameByProjectId(projectId));
		model.addAttribute("issue", new Issue());
		model.addAttribute("team",
				userProjectService.userProjectListByProjectId(projectId));
		model.addAttribute("project", new Project());
		model.addAttribute("projectList", projectService.projectList());
		return "/issue/add";
	}

	@PreAuthorize("hasPermission(#user, 'Add issue')")
	@RequestMapping(value = "/projectId={projectId}/add", method = RequestMethod.POST)
	public String issueAdd(@ModelAttribute Issue issue, BindingResult result,
			Model model, @PathVariable Integer projectId,
			@RequestParam(value = "action", required = false) String action)
			throws Exception {
		model.addAttribute("errors", result.getAllErrors());
		issueValidator.validate(issue, result);
		if (result.hasErrors()) {
			model.addAttribute("projectName",
					projectService.getProjectNameByProjectId(projectId));
			model.addAttribute("team",
					userProjectService.userProjectListByProjectId(projectId));
			model.addAttribute("project", new Project());
			model.addAttribute("projectList", projectService.projectList());
			return "/issue/add";
		}
		issueService.addIssue(issue);
		if (IssueDetailsButton.valueOf(action).getButtonNumber() == 1)
			return "redirect: add";
		return "redirect:" + redirectPath;
	}

	@PreAuthorize("hasPermission(#user, 'Edit issue')")
	@RequestMapping(value = "/projectId={projectId}/edit/{issueId}", method = RequestMethod.GET)
	public String getIssueId(@PathVariable Integer issueId,
			@PathVariable Integer projectId, Model model,
			HttpServletRequest request) throws Exception {
		Locale loc = RequestContextUtils.getLocale(request);
		model.addAttribute("locale", loc);
		model.addAttribute("addOrEdit", "edit");
		model.addAttribute("projectName",
				projectService.getProjectNameByProjectId(projectId));
		model.addAttribute("issue", issueService.getIssueById(issueId));
		model.addAttribute("team",
				userProjectService.userProjectListByProjectId(projectId));
		model.addAttribute("project", new Project());
		model.addAttribute("projectList", projectService.projectList());
		return "/issue/add";
	}

	@PreAuthorize("hasPermission(#user, 'Edit issue')")
	@RequestMapping(value = "/projectId={projectId}/edit/{issueId}", method = RequestMethod.POST)
	public String updateIssue(@ModelAttribute Issue issue,
			@PathVariable Integer issueId, BindingResult result,
			@PathVariable Integer projectId, Model model) throws Exception {
		issueValidator.validate(issue, result);
		if (result.hasErrors()) {
			model.addAttribute("addOrEdit", "edit");
			model.addAttribute("projectName",
					projectService.getProjectNameByProjectId(projectId));
			model.addAttribute("issue", issueService.getIssueById(issueId));
			model.addAttribute("team",
					userProjectService.userProjectListByProjectId(projectId));
			model.addAttribute("projectList", projectService.projectList());
			return "/issue/add";
		}
		issueService.updateIssue(issue, issueId);
		return "redirect:" + redirectPath;
	}

	@PreAuthorize("hasPermission(#user, 'View issues')")
	@RequestMapping(value = "/details/{issueId}", method = RequestMethod.GET)
	public String getIssueDetails(@PathVariable Integer issueId, Model model,
			HttpServletRequest request) throws Exception {
		Locale loc = RequestContextUtils.getLocale(request);
		model.addAttribute("locale", loc);
		Issue issue = issueService.getIssueDetails(issueId);
		model.addAttribute("team", userProjectService
				.userProjectListByProjectId(issue.getProjectId()));
		model.addAttribute("sessionUserId", userService.getUserByLogin()
				.getUserId());
		model.addAttribute("issue", issue);
		model.addAttribute("comment", new Comment());
		model.addAttribute("commentList", commentService.commentList(issueId));
		model.addAttribute("project", new Project());
		model.addAttribute("projectList", projectService.projectList());
		model.addAttribute("backPath", redirectPath);
		return "/issue/details";
	}

	@PreAuthorize("hasPermission(#user, 'Add comment')")
	@RequestMapping(value = "/details/addcomment", method = RequestMethod.POST)
	public @ResponseBody
	Comment commentAdd(@RequestParam(value = "value") String value,
			@RequestParam(value = "issueId") Integer issueId) throws Exception {

		Comment comment = new Comment();
		comment.setValue(value);
		comment.setIssueId(issueId);
		return commentService.dynamicCreateComment(comment);
	}
	
	@PreAuthorize("hasPermission(#user, 'Assign to')")
	@RequestMapping("/details/{issueId}/assignTo")
	public String assignTo(@PathVariable Integer issueId, 
			@RequestParam(value = "assignId", required = false) Integer assignId) throws Exception{
		issueService.assignToSomeBody(issueId, assignId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Assign to me')")
	@RequestMapping("/details/{issueId}/assignToMe")
	public String assignToMe(@PathVariable Integer issueId) throws Exception{
		issueService.assignToMe(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Start progress')")
	@RequestMapping("/details/{issueId}/startProgress")
	public String startProgress(@PathVariable Integer issueId) throws Exception{
		issueService.startProgress(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Resolve issue')")
	@RequestMapping("/details/{issueId}/resolveIssue")
	public String resolveIssue(@PathVariable Integer issueId) throws Exception{
		issueService.resolveIssue(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Close issue')")
	@RequestMapping("/details/{issueId}/closeIssue")
	public String closeIssue(@PathVariable Integer issueId) throws Exception{
		issueService.closeIssue(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Reopen issue')")
	@RequestMapping("/details/{issueId}/reopenIssue")
	public String reopenIssue(@PathVariable Integer issueId) throws Exception{
		issueService.reopenIssue(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Reset issue')")
	@RequestMapping("/details/{issueId}/resetIssue")
	public String resetIssue(@PathVariable Integer issueId) throws Exception{
		issueService.resetIssue(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@PreAuthorize("hasPermission(#user, 'Blocked')")
	@RequestMapping("/details/{issueId}/blockIssue")
	public String blockIssue(@PathVariable Integer issueId) throws Exception{
		issueService.blockIssue(issueId);
		return "redirect:/issue/details/" + issueId;
	}
	
	@ModelAttribute("typeList")
	public List<Type> typeList() {
		return typeService.listOfTypes();
	}

	@ModelAttribute("statusList")
	public List<Status> statusList() {
		return statusService.listOfStatus();
	}

	@ModelAttribute("priorityList")
	public List<Priority> priorityList() {
		return priorityService.listOfPriority();
	}

	@ModelAttribute("environmentList")
	public List<Environment> environmentList() {
		return environmentService.getEnvironmentList();
	}

}
