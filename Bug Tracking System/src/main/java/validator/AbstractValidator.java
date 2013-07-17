package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public abstract class AbstractValidator implements Validator {

	@Override
	public abstract boolean supports(Class<?> clazz);

	@Override
	public abstract void validate(Object target, Errors errors);

	protected boolean isNotNull(String testString) {
		return (testString == null || testString.equals(""));
	}

	protected boolean isNotValidLength(String string, Integer minLength,
			Integer maxLength) {
		return (string.length() < minLength) || (string.length() > maxLength);
	}

	protected boolean isNotUniq(String testString, String existingString) {
		return (testString == existingString);
	}

}
