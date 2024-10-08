package org.jsp.useraddressbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.useraddressbootapp.dao.AddressDao;
import org.jsp.useraddressbootapp.dao.UserDao;
import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.exception.AddressNotFoundException;
import org.jsp.useraddressbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
		
		ResponseStructure<Address> structure = new ResponseStructure<>();
		
		Optional<User> recUser = userDao.findById(user_id);
		
		if( recUser.isPresent()) {
			
			User user = recUser.get();
			
			address.setUser(user); // Assigning user to address
			user.getAddress().add(address); // Assigning address to user
			
			userDao.saveUser(user); // updating user
			
			structure.setMessage("Address added");
			structure.setData(addressDao.saveAddress(address));  // saving address
			structure.setStatusCode(HttpStatus.CREATED.value());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
 		throw new UserNotFoundException("Cannot save address as User id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		
		ResponseStructure<Address> structure = new ResponseStructure<>();
		
		Optional<Address> recAddress = addressDao.findById(address.getId());
		
		if( recAddress.isPresent()) {
			
			Address dbAddress = recAddress.get();
			
			dbAddress.setHouse_number(address.getHouse_number());
			dbAddress.setLandmark(address.getLandmark());
			dbAddress.setArea(address.getArea());
			dbAddress.setCity(address.getCity());
			dbAddress.setState(address.getState());
			dbAddress.setCountry(address.getCountry());
			dbAddress.setPincode(address.getPincode());
			
			structure.setData(addressDao.saveAddress(dbAddress));
			structure.setMessage("Address updated with id : " + address.getId());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AddressNotFoundException("Cannot update Address as id is Invalid");
	} 
	
	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		
		ResponseStructure<Address> structure = new ResponseStructure<>();
		
		Optional<Address> recAddress = addressDao.findById(id);
		
		if( recAddress.isPresent()) {
			
			structure.setData(recAddress.get());
			structure.setMessage("Address Found");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AddressNotFoundException("No Address found for the entered id");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAddress(int id) {
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		Optional<Address> recAddress = addressDao.findById(id);
		
		if( recAddress.isPresent()) {
			
			addressDao.deleteAddress(id);
			
			structure.setData("Address deleted");
			structure.setMessage("Address Found");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AddressNotFoundException("Cannot delete Address as Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<List<Address>>> findAllAddress() {
		
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		
		List<Address> addresses = addressDao.findAllAddress();
		
		if( addresses.isEmpty()) {
			throw new AddressNotFoundException("Database does not contains any address");
		}
		
		structure.setData(addresses);
		structure.setMessage("List of All Address");
		structure.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Address>>> findAddressByUserId(int id) {
		
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		
		List<Address> addresses = addressDao.findAddressByUserId(id);
		
		if( addresses.isEmpty()) {
			throw new AddressNotFoundException("No Address found as User id is Invalid");
		}
		
		structure.setData(addresses);
		structure.setMessage("List of Address for entered User id " + id);
		structure.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Address>>> findAddressByUserPhoneAndPassword(long phone, String password) {
		
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		
		List<Address> addresses = addressDao.findAddressByUserPhoneAndPassword(phone, password);
		
		if( addresses.isEmpty()) {
			throw new AddressNotFoundException("No Address found as entered User Phone number or password is Invalid");
		}
		
		structure.setData(addresses);
		structure.setMessage("List of Address for the entered User phone number and password");
		structure.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
}

