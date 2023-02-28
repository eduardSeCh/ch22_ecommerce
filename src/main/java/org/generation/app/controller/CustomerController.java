package org.generation.app.controller;

import java.util.List;

import org.generation.app.model.Customer;
import org.generation.app.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping()
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("active")
	public List<Customer> getAllActioveCustomers(){
		return customerService.getAllActiveCustomers();
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") long idCustomer) {
		try {
			return new ResponseEntity<Customer>(
					customerService.getCustomerById(idCustomer),
					HttpStatus.OK
					);
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public Customer setNewCustomer(@RequestBody Customer customer) {
		return customerService.setCustomer(customer);
	}
}
