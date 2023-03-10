package com.vivek.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> handleException(UserException p,WebRequest r){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),p.getMessage(),r.getDescription(false));
		return new ResponseEntity<> (err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> secondException(Exception e,WebRequest w){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> thirdException(NoHandlerFoundException e,WebRequest w){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> fourthException(MethodArgumentNotValidException e){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error !",e.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
}
