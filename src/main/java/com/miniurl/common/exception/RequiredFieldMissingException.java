package com.miniurl.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Required field missing or empty")
public class RequiredFieldMissingException extends RuntimeException {

}
