package session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class AttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		Object value = event.getValue();
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		String date = formatter.format(now);
		System.out.println(date + " - date; Session Attribute Added: " + name + " = " + value);
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		Object value = event.getValue();
		if (value.toString().length() > 200) {
			String takeName = value.toString().substring(189, 201);
			Integer findSymbol = takeName.indexOf(";");
			SessionCounter.getUsersOnline().remove(
					(takeName.substring(0, findSymbol)));
			System.out.println("cleaned: " + takeName.substring(0, findSymbol));
		}
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		String date = formatter.format(now);
		System.out.println(date + " - date; Session Attribute Removed: " + name + " = " + value);
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		String name = event.getName();
		Object value = event.getValue();
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		String date = formatter.format(now);
		System.out.println(date + " - date; Session Attribute Replaced: " + name + " = " + value);
	}
}
