package org.jsp.useraddressbootapp.repository;

import java.util.Optional;

import org.jsp.useraddressbootapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByPhoneAndPassword(long phone, String password);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	List<User> findByName(String name);
}
