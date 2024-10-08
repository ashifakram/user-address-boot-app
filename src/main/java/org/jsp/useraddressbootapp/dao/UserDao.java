package org.jsp.useraddressbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}
	
	public Optional<User> findById(int id) {
		
		return userRepository.findById(id);
	}
	
	public List<User> findByName(String name){
		
		return userRepository.findByName(name);
	}
	
	public Optional<User> findByPhoneAndPassword(long phone, String password) {
		
		return userRepository.findByPhoneAndPassword(phone, password);
	}
	
	public Optional<User> findByEmailAndPassword(String email, String password) {
		
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public List<User> findAllUser() {
		
		return userRepository.findAll();
	}
}
