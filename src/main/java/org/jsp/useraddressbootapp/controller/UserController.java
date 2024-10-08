package org.jsp.useraddressbootapp.controller;

import java.util.List;

import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		
		return userService.saveUser(user);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		
		return userService.updateUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name ="id") int id) {
		
		return userService.findById(id);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<User>>> findByName(@PathVariable(name = "name") String name) {
		
		return userService.findByName(name);
	}
	
	@PostMapping("/verify-by-phone-and-password")
	public ResponseEntity<ResponseStructure<User>> verifyByPhoneAndPassword(@RequestParam(name = "phone") long phone,@RequestParam(name = "password") String password) {
		
		return userService.findByPhoneAndPassword(phone, password);
	}
	
	@PostMapping("/verify-by-email-and-password")
	public ResponseEntity<ResponseStructure<User>> verifyByEmailAndPassword(@RequestParam(name = "email") String email,@RequestParam(name = "password") String password) {
		
		return userService.findByEmailAndPassword(email, password);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
		
		return userService.findAllUser();
	}
}
