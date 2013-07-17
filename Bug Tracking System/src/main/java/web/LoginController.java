package web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;


import security.SaltSourceImpl;
import service.UserService;
import session.SessionCounter;

@Controller
@RequestMapping("/")
public class LoginController {
	private String setAuthorize(String userName, String password,
			HttpServletRequest request) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication auth = new UsernamePasswordAuthenticationToken(userName,
				password, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
		SessionCounter.getUsersOnline().put(userName, request.getSession());
		request.getSession().setMaxInactiveInterval(12000);
		return userService.router();
	}

	@Autowired
	private UserService userService;

	@Autowired
	private SaltSourceImpl saltSource;

	@RequestMapping(value = "/")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/login")
	public String getAuthorities(Model model, HttpServletRequest request)
			throws Exception {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		if (userName == null || userName.isEmpty()) {
			return "redirect:/";
		}
		password = saltSource.getSaltedPassword(userName, password);
		userName = userService.authorize(userName, password);
		if (userName == null) {
			request.getSession().setAttribute("login", "error");
			return "redirect:/";
		}
		if (SessionCounter.getUsersOnline().containsKey(userName)) {
			HttpSession session = SessionCounter.getUsersOnline().get(userName);
			try {
				session.invalidate();
			} catch (Exception ignore) {
			}
			SessionCounter.getUsersOnline().remove(userName);
		}
		return setAuthorize(userName, password, request);
	}

	@RequestMapping(value = "/logout")
	public String getPostLogout(HttpServletRequest request) {
		try {
			SessionCounter.getUsersOnline().remove(
					request.getUserPrincipal().getName());
			request.getSession().invalidate();
		} catch (Exception ignore) {
		}
		return "redirect:/";
	}
}
