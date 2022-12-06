package com.miniurl.plainurl.intake.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.miniurl.plainurl.intake.model.PlainURL;

@Component(value = "shortenURLValidator")
public class ShortenURLValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return PlainURL.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullURL", "field.required");

	}
}
