package com.revature.validation;

import com.revature.validation.exceptions.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerValidationHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String processValidationConstraintViolation(ConstraintViolationException ex) {
		return "Constraint Violation: " + ex.getMessage();
	  // return status code
	}
  
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String processValidationUnreadable(HttpMessageNotReadableException ex) {
		return ex.getMessage();
	  // return status code
	}

    @ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String NotFoundError(NotFoundException ex) {
		return ex.getMessage();
	  // return status code
	}

}
