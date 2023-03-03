package org.generation.app.controller;

import java.util.List;

import org.generation.app.model.Address;
import org.generation.app.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address")
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	@GetMapping()
	public List<Address> getAlladdresss(){
		return addressService.getAllAddress();
	}
	
	@GetMapping("active")
	public List<Address> getAllActioveaddresss(){
		return addressService.getAllActiveAddress();
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<?> getaddressById(@PathVariable("id") long idaddress) {
		try {
			return new ResponseEntity<Address>(
					addressService.getAddressById(idaddress),
					HttpStatus.OK
					);
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> setNewaddress(@RequestBody Address address) {
		try {
			return new ResponseEntity<Address>(
					addressService.setAddress(address),
					HttpStatus.CREATED
					);
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateaddress(@RequestBody Address address){
		try {
			return new ResponseEntity<Address>(
					addressService.updateAddress(address), 
					HttpStatus.CREATED
					);
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteaddressById(@PathVariable("id") long idaddress) {
		try {
			return new ResponseEntity<String>(
					addressService.deleteAddressById(idaddress),
					HttpStatus.OK
					);
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}	
	}
	
	
	  @GetMapping("/customer/{id}") public List<Address>
	  getAllAdrressesByFkIdCustomer(@PathVariable("id") long idCustomer){
		  return (List<Address>) addressService.getAllAddreessByFkIdCustomer(idCustomer); 
	  }
	 
}
