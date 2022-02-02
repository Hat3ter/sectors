package com.helmet.sectors.controllers;

import com.helmet.sectors.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler
 */
@Slf4j
@RestControllerAdvice
public class ControllerHandler {

	/**
	 * Handle {@link MethodArgumentNotValidException}
	 *
	 * @param e exception
	 * @return {@link ErrorResponse}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleConstraintViolationException(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
		Object rejectedValue = fieldError.getRejectedValue();
		String field = fieldError.getField();
		String reason = fieldError.getDefaultMessage();
		String errorMessage = String.format("Validation failed field={%s} {%s}, for value {%s}",
				field, reason, rejectedValue);
		log.info("Fetched exception {{}}", errorMessage);
		return new ErrorResponse(errorMessage);
	}

	/**
	 * To simplify one handler is used
	 *
	 * @param e exception
	 * @return {@link ErrorResponse}
	 */
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception e) {

		log.info("#EXCEPTION fetched {}", e.getMessage());
		return new ErrorResponse(e.getMessage());
	}
}
