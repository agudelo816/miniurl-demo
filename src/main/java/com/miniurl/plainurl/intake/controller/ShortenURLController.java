package com.miniurl.plainurl.intake.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miniurl.plainurl.intake.model.HashedURL;
import com.miniurl.plainurl.intake.model.PlainURL;
import com.miniurl.plainurl.intake.service.ShortenURLService;

@RestController
public class ShortenURLController {

	@Autowired
	private ShortenURLService shortenURLService;

	//	@InitBinder
	//	protected void initBinder(WebDataBinder binder) {
	//		binder.setValidator(new ShortenURLValidator());
	//	}

	//	@PostMapping(path = "/shortURL", consumes = "application/json", produces = "application/json")
	@PostMapping(path = "/shortURL", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	//	private ResponseEntity<HashedURL> shortenURL(@Validated @RequestBody PlainURL plainURLRequestBody) {
	private ResponseEntity<HashedURL> shortenURL(@RequestBody String requestbody) {

		PlainURL plainURL = new PlainURL();
		plainURL.setFullURL(requestbody);


		return ResponseEntity.ok(this.shortenURLService.processPlainURL(plainURL));
	}

	private ResponseEntity<String> redirectShortURL() {
		return ResponseEntity.ok("");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
