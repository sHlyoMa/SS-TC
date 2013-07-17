package validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Comment;

@Component
public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Comment comment = (Comment) object;

		if (isNotNull(comment.getValue())) {
			errors.rejectValue("value", "label.comment.value.required");
			if (isValidLength(comment.getValue())) {
				errors.rejectValue("value", "label.comment.value.size");
			}
		}
	}

	private boolean isNotNull(String testString) {
		return (testString == null || testString.equals(""));
	}

	private boolean isValidLength(String string) {
		return (string.length() > 4) && (string.length() < 255);
	}

}
