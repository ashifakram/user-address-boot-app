package org.jsp.useraddressbootapp.controller;

import java.util.List;

import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address, @PathVariable int user_id) {
		
		return addressService.saveAddress(address, user_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
		
		return addressService.updateAddress(address);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		
		return addressService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAddress(@PathVariable int id) {
		
		return addressService.deleteAddress(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Address>>> findAllAddress() {
		
		return addressService.findAllAddress();
	}
	
	@GetMapping("/find-by-user-id/{id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findAddressByUserId(@PathVariable int id) {
		
		return addressService.findAddressByUserId(id);
	}
	
	@PostMapping("/find-by-user-phone-password")
	public ResponseEntity<ResponseStructure<List<Address>>> findAddressByUserPhoneAndPassword(@RequestParam(name = "phone") long phone, @RequestParam(name = "password") String password) {
		
		return addressService.findAddressByUserPhoneAndPassword(phone, password);
	}
}
