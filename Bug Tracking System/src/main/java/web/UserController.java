package web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import domain.User;

import security.SaltSourceImpl;
import service.RoleService;
import service.UserInfoService;
import service.UserService;
import session.SessionCounter;
import validator.UserEditValidator;
import validator.UserValidator;

@Controller
@SessionAttributes("info")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SaltSourceImpl saltSource;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	@Qualifier("userValidator")
	private UserValidator userValidator;
	@Autowired
	@Qualifier("userEditValidator")
	private UserEditValidator userEditValidator;

	@ModelAttribute("info")
	public User getMyInfo() {
		return userService.getUserByLogin();
	}

	@PreAuthorize("hasPermission(#user, 'View users')")
	@RequestMapping(value = "/sort={column}-{direction}/page={page}&limit={recordsLimit}")
	public String userList(Map<String, Object> map, HttpServletRequest request,
			Model model, @PathVariable("column") Integer column,
			@PathVariable("recordsLimit") Integer recordsLimit,
			@PathVariable("direction") Integer direction,
			@PathVariable("page") Integer page) throws Exception {
		map.put("sessionsCount", SessionCounter.getActiveSessions());
		map.put("count", userService.getUserCount());
		map.put("recordsLimit", recordsLimit);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("recordsLimit", recordsLimit);
		request.getSession().setAttribute("column", column);
		request.getSession().setAttribute("direction", direction);
		map.put("user", new User());
		map.put("userList",
				userService.listUser(page, column, direction, recordsLimit));
		model.addAttribute("roleList", roleService.roleList());
		return "/user/list";
	}

	@PreAuthorize("hasPermission(#user, 'View users')")
	@RequestMapping(value = "/roleId={roleId}/sort={column}-{direction}/page={page}&limit={recordsLimit}")
	public String userListByRole(HttpServletRequest request, Model model,
			@PathVariable Integer roleId,
			@PathVariable("column") Integer column,
			@PathVariable("recordsLimit") Integer recordsLimit,
			@PathVariable("direction") Integer direction,
			@PathVariable("page") Integer page) throws Exception {
		model.addAttribute("roleList", roleService.roleList());
		model.addAttribute("sessionsCount", SessionCounter.getActiveSessions());
		model.addAttribute("count", userService.getCountOfRoleUsers(roleId));
		model.addAttribute("recordsLimit", recordsLimit);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("recordsLimit", recordsLimit);
		model.addAttribute("userList", userService.getUsersByRole(roleId, page,
				column, direction, recordsLimit));
		return "/user/list";
	}

	@PreAuthorize("hasPermission(#user, 'View users')")
	@RequestMapping("")
	public String home() {
		return "redirect:/user/sort=1-1/page=1&limit=20";
	}

	@PreAuthorize("hasPermission(#user, 'Add user')")
	@RequestMapping("/add")
	public String addUserPage(Model model) throws Exception {
		model.addAttribute("roleList", roleService.roleList());
		model.addAttribute("user", new User());
		return "/user/add";
	}

	@PreAuthorize("hasPermission(#user, 'Add user')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("user") User user,
			BindingResult result, HttpServletRequest request) throws Exception {
		model.addAttribute("roleList", roleService.roleList());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "/user/add";
		}
		userService.addUser(user);
		user.setUserId(userService.getUserId(user));
		userInfoService.addUserInfo(user);
		System.out.println("salted"
				+ saltSource.getSaltedPassword(user.getLogin(),
						user.getPassword()));
		user.setPassword(saltSource.getSaltedPassword(user.getLogin(),
				user.getPassword()));
		userService.editUser(user);
		model.addAttribute("page", request.getSession().getAttribute("page"));
		model.addAttribute("recordsLimit",
				request.getSession().getAttribute("recordsLimit"));
		return "redirect:/user/sort=1-1/page={page}&limit={recordsLimit}";
	}

	@PreAuthorize("hasPermission(#user, 'Delete user')")
	@RequestMapping("/delete/{userId}")
	public String removeUser(@PathVariable("userId") Integer userId,
			Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("page", request.getSession().getAttribute("page"));
		model.addAttribute("recordsLimit",
				request.getSession().getAttribute("recordsLimit"));
		model.addAttribute("column", request.getSession()
				.getAttribute("column"));
		model.addAttribute("direction",
				request.getSession().getAttribute("direction"));
		if (userService.getCountOfInvolvedTeam(userId) > 0) {
			request.getSession().setAttribute("userInTeam", "true");
			return "redirect:/user/sort={column}-{direction}/page={page}&limit={recordsLimit}";
		}
		if (userService.getPmProjectCount(userId) > 0) {
			request.getSession().setAttribute("countOfPmProjects",
					userService.getPmProjectCount(userId));
			request.getSession().setAttribute("PmHaveProjects", "true");
			return "redirect:/user/sort={column}-{direction}/page={page}&limit={recordsLimit}";
		}
		userService.removeUser(userId);
		userInfoService.deleteUserInfo(userId);
		model.addAttribute("page", request.getSession().getAttribute("page"));
		model.addAttribute("recordsLimit",
				request.getSession().getAttribute("recordsLimit"));
		model.addAttribute("roleList", roleService.roleList());
		return "redirect:/user/sort={column}-{direction}/page={page}&limit={recordsLimit}";
	}

	@PreAuthorize("hasPermission(#user, 'Edit user')")
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("userId") Integer userId,
			HttpServletRequest request, User user, Model model) {
		model.addAttribute("roleList", roleService.roleList());
		user = userService.getUserById(userId);

		model.addAttribute("user", userService.getUserById(userId));
		model.addAttribute("password", user.getPassword());
		model.addAttribute("roleList", roleService.roleList());

		request.getSession().setAttribute("passwordTemp", user.getPassword());
		return "/user/edit";
	}

	@PreAuthorize("hasPermission(#user, 'Edit user')")
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, Model model,
			HttpServletRequest request, @PathVariable("userId") Integer userId,
			BindingResult result) throws Exception {
		model.addAttribute("roleList", roleService.roleList());
		userEditValidator.validate(user, result);
		if (result.hasErrors()) {
			return "/user/edit";
		}

		if (SessionCounter.getUsersOnline().containsKey(
				userService.getLoginById(userId))
				&& (userService.getLoginById(userId) != (request
						.getUserPrincipal().getName()))) {

			if (userService.getLoginById(userId).equals(
					request.getUserPrincipal().getName())) {
				return "redirect:/login/logout";
			}
			try {
				SessionCounter.getUsersOnline()
						.get(userService.getLoginById(userId)).invalidate();
				SessionCounter.getUsersOnline().remove(
						userService.getLoginById(userId));
			} catch (Exception ignore) {
			}

		}

		user.setUserId(userId);
		if (request.getParameter("password").equals(
				request.getSession().getAttribute("passwordTemp"))) {
			userService.editUserSamePass(user);
			userInfoService.updateUserInfo(user);
			model.addAttribute("page", request.getSession()
					.getAttribute("page"));
			model.addAttribute("column",
					request.getSession().getAttribute("column"));
			model.addAttribute("recordsLimit", request.getSession()
					.getAttribute("recordsLimit"));
			model.addAttribute("direction",
					request.getSession().getAttribute("direction"));
			return "redirect:/user/sort={column}-{direction}/page={page}&limit={recordsLimit}";
		} else {
			user.setPassword(saltSource.getSaltedPassword(user.getLogin(),
					user.getPassword()));
			userService.editUser(user);
			userInfoService.updateUserInfo(user);
			model.addAttribute("page", request.getSession()
					.getAttribute("page"));
			model.addAttribute("recordsLimit", request.getSession()
					.getAttribute("recordsLimit"));
			model.addAttribute("column",
					request.getSession().getAttribute("column"));
			model.addAttribute("direction",
					request.getSession().getAttribute("direction"));
			return "redirect:/user/sort={column}-{direction}/page={page}&limit={recordsLimit}";
		}
	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public @ResponseBody
	void ban(@RequestParam(value = "userId") Integer userId) throws Exception {
		userService.banUserById(userId);
	}

	@RequestMapping(value = "/unBan", method = RequestMethod.GET)
	public @ResponseBody
	void unBan(@RequestParam(value = "userId") Integer userId) throws Exception {
		userService.unBanUserById(userId);
	}
}
