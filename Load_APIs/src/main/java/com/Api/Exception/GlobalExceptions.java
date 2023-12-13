package com.Api.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.Api.Payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptions {
@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce>resourceNotFoundhandler(ResourceNotFoundException ex){
	String message=ex.getMessage();
	ApiResponce apiresponce=new ApiResponce(message,false);
	return new ResponseEntity<ApiResponce>(apiresponce,HttpStatus.NOT_FOUND);
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleMethodArug(MethodArgumentNotValidException ex){
		Map<String, String > resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			 String fieldName=((FieldError) error).getField();
			 String defaultMessage=error.getDefaultMessage();
			 resp.put(fieldName, defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
}
}
