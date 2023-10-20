package me.dio.controller.exception;

import java.util.NoSuchElementException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ch.qos.logback.classic.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleBussinessException(IllegalArgumentException bussinessException){
		return new ResponseEntity<>(bussinessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){
		var message = "Resource ID not found";
		logger.error(message, notFoundException);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
		var message = "Unexpected server error";
		logger.error(message, unexpectedException);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
