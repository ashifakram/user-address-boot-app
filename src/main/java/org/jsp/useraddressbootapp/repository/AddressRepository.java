package org.jsp.useraddressbootapp.repository;

import java.util.List;

import org.jsp.useraddressbootapp.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Query("select u.address from User u where u.id = ?1")
	List<Address> findAddressByUserId(int id);
	
	@Query("select u.address from User u where u.phone = ?1 and u.password = ?2")
	List<Address> findAddressByUserPhoneAndPassword(long phone, String password);
}
