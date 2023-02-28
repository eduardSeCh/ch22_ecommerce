package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Customer;
import org.generation.app.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();
		return allCustomers;
	}

	@Override
	public List<Customer> getAllActiveCustomers() {
		List<Customer> allActiveCustomers = customerRepository.findAllByActive(true);
		return allActiveCustomers;
	}

	@Override
	public Customer getCustomerById(long idCustomer) {
		return customerRepository.findById(idCustomer)
				.orElseThrow(() -> new IllegalStateException("The user is no found..."));
	}
	
	@Override
	public Customer setCustomer(Customer customer) {
		Customer newCustomer = customer;
		newCustomer.setActive(true);
		return customerRepository.save(newCustomer);
	}

}