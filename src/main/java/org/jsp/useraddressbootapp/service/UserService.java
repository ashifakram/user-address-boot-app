package org.jsp.useraddressbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.useraddressbootapp.dao.UserDao;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		ResponseStructure<User> structure = new ResponseStructure<>();

		structure.setMessage("User saved with id : " + userDao.saveUser(user).getId());
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());

		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {

		ResponseStructure<User> structure = new ResponseStructure<>();

		Optional<User> recUser = userDao.findById(user.getId());

		if (recUser.isPresent()) {

			User dbUser = recUser.get();

			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());

			structure.setMessage("User updated with id : " + user.getId());
			structure.setData(userDao.saveUser(dbUser));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("Cannot update User as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		
		ResponseStructure<User> structure = new ResponseStructure<>();

		Optional<User> recUser = userDao.findById(id);
		
		if(recUser.isPresent()) {
			
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Id");
	}
	
	public ResponseEntity<ResponseStructure<User>> findByPhoneAndPassword(long phone, String password) {
			
		ResponseStructure<User> structure = new ResponseStructure<>();
		
		Optional<User> recUser = userDao.findByPhoneAndPassword(phone, password);
		
		if(recUser.isPresent()) {
			
			structure.setData(recUser.get());
			structure.setMessage("Verification Successful");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Phone Number or password");	
	}
	
	public ResponseEntity<ResponseStructure<User>> findByEmailAndPassword(String email, String password) {
		
		ResponseStructure<User> structure = new ResponseStructure<>();
		
		Optional<User> recUser = userDao.findByEmailAndPassword(email, password);
		
		if(recUser.isPresent()) {
			
			structure.setData(recUser.get());
			structure.setMessage("Verification Successful");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Email Id or password");
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> findByName(String name) {
		
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		
		List<User> users = userDao.findByName(name);
		structure.setData(users);
		
		if( users.isEmpty()) {
			throw new UserNotFoundException("No User Found for the entered name");
		}
		
		structure.setMessage("List of users with entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
		
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		
		List<User> users = userDao.findAllUser();
		
		if( users.isEmpty()) {
			throw new UserNotFoundException("Database does not contains any Users");
		}
		
		structure.setMessage("List of All Users");
		structure.setData(users);
		structure.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

}
