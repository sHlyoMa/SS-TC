package validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import service.UserService;
import domain.User;

@Component
public class UserValidator implements Validator {
	Pattern onlyEng = Pattern.compile("[a-zA-z]");

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		if (isValidLength(user.getLogin(), 6, 20)) {
			errors.rejectValue("login", "label.login.value");
		}

		if (!isNull(user.getLogin())) {
			if (!onlyEng.matcher(user.getLogin()).find()) {
				errors.rejectValue("login", "label.login.onlyEng");
			}
		}
		if (!isValidLength(user.getLogin(), 6, 20)
				&& onlyEng.matcher(user.getLogin()).find()) {
			if (userService.uniqueLogin(user.getLogin()) != null) {
				errors.rejectValue("login", "label.user.notcreated");
			}
		}
		if (isValidLength(user.getPassword(), 6, 100)) {
			errors.rejectValue("password", "label.login.passwordNotCorrect");
		}

		if (!isNull(user.getPassword())) {
			if (!onlyEng.matcher(user.getPassword()).find()) {
				errors.rejectValue("password", "label.login.onlyEngPwd");
			}
		}

		if (isNull(user.getUserInfo().getFirstName())) {
			errors.rejectValue("userInfo.firstName", "label.login.fName");
		}

		if (user.getUserInfo().getFirstName().toString().length() > 20) {
			errors.rejectValue("userInfo.firstName", "label.login.tooBig");
		}

		if (isNull(user.getUserInfo().getSecondName())) {
			errors.rejectValue("userInfo.secondName", "label.login.sName");
		}

		if (user.getUserInfo().getSecondName().toString().length() > 20) {
			errors.rejectValue("userInfo.secondName", "label.login.tooBig");
		}

		if (isNull(user.getUserInfo().getEmail())) {
			errors.rejectValue("userInfo.email", "label.login.emailnull");
		}

		if (user.getUserInfo().getEmail().toString().length() > 50) {
			errors.rejectValue("userInfo.email", "label.login.email");
		}

		if (!isNull(user.getUserInfo().getEmail())) {
			Pattern p = Pattern.compile(".+@.+\\.[a-zA-z]+");
			if (!p.matcher(user.getUserInfo().getEmail()).find()) {
				errors.rejectValue("userInfo.email", "label.login.email");
			}
		}
	}

	private boolean isNull(String testString) {
		return (testString == null || testString.equals(""));
	}

	private boolean isValidLength(String string, Integer low, Integer hi) {
		try {
			return (string.length() < low || string.length() > hi);
		} catch (Exception ex) {
			return true;
		}
	}
}
