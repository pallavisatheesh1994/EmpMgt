package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> badRequestException(BadRequestException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setStatusCode("400");
		response.setStatus("Failure");
		response.setMessage(ex.getMessage());
		 return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode("500");
        response.setStatus("Failure");
        response.setMessage("There is some issue at server side. Please check the log.");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> employeeNotFoundException(EmployeeNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode("404");
        response.setStatus("Failure");
        response.setMessage("Employee Not Found");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
 }
    
}
