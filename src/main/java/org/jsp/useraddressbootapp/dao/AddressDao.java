package org.jsp.useraddressbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		
		return addressRepository.save(address);
	}
	
	public Optional<Address> findById(int id) {
		
		return addressRepository.findById(id);
	}
	
	public List<Address> findAllAddress() {
		
		return addressRepository.findAll();
	}
	
	public List<Address> findAddressByUserId(int id) {
		
		return addressRepository.findAddressByUserId(id);
	}
	
	public List<Address> findAddressByUserPhoneAndPassword(long phone, String password) {
		
		return addressRepository.findAddressByUserPhoneAndPassword(phone, password);
	}
	
	public void deleteAddress(int id) {
		
		addressRepository.deleteById(id);
	}
}
