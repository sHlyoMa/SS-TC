package session;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
	private static HashMap<String, HttpSession> usersOnline = new HashMap<String, HttpSession>();

	
	public static int getActiveSessions() {
		return usersOnline.size();
	}

	public static HashMap<String, HttpSession> getUsersOnline() {
		return usersOnline;
	}

	public static void setUsersOnline(HashMap<String, HttpSession> usersOnline) {
		SessionCounter.usersOnline = usersOnline;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
}