package org.jsp.useraddressbootapp.exception;

import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserAddressBootAppExceptionHandler extends ResponseEntityExceptionHandler{
		
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUNFE(UserNotFoundException exception) {
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setData("User Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleANFE(AddressNotFoundException exception) {
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setData("Address Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
}
