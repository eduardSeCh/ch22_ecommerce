package org.generation.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.generation.app.dto.CustomerDto;
import org.generation.app.model.Customer;
import org.generation.app.repository.ICustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor //Crea el metodo costructor para injeccion de dependcias con lombok
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	private CustomerDto customerDto;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();
		return allCustomers;
	}
	
	@Override
	public List<CustomerDto> getAllCustomersDto() {
		List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();
		List<CustomerDto> customersDto =  allCustomers.stream()
				.map( customer -> modelMapper.map(customer, CustomerDto.class) )
				.collect(Collectors.toList());
		return customersDto;
	}

	@Override
	public List<Customer> getAllActiveCustomers() {
		List<Customer> allActiveCustomers = customerRepository.findAllByActive(true);
		return allActiveCustomers;
	}

	@Override
	public Customer getCustomerById(long idCustomer) {
		 Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow(() -> new IllegalStateException("The user is not found..."));
		 
		 return customer;
	}
	
	@Override
	public CustomerDto getCustomerDtoById(long idCustomer) {		
		Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow( ()-> 
				new IllegalStateException("User does not exist with id: " + idCustomer));		
		//CustomerDto customerDto = new CustomerDto();
		/* ESto sin modelMapper
		 * customerDto.setIdCustomer( customer.getIdCustomer() );
		 * customerDto.setFirstName( customer.getFistName() ); customerDto.setLastName(
		 * customer.getLastName() ); customerDto.setEmail( customer.getEmail() );
		 * customerDto.setAvatar( customer.getAvatar() );
		 */
		 customerDto = modelMapper.map(customer, CustomerDto.class); //toma la clase de CustomerDto
		 return customerDto;
	}
	
	@Override
	public Customer setCustomer(Customer customer) {
		if(existCustomerByEmail(customer.getEmail())) 
			throw new IllegalStateException("The user with email exist");
		else if(customer.getEmail().length() > Customer.MAX_LENGTH)
			throw new  IllegalStateException("Email length is greater than: "+ Customer.MAX_LENGTH);
		
		Customer newCustomer = customer;
		newCustomer.setIdCustomer(0);
		newCustomer.setActive(true);
		return customerRepository.save(newCustomer);
	}
	
	@Override
	public boolean existCustomerByEmail(String email) {
		return customerRepository.existsByEmail(email);
	}

	@Override
	public Customer updateCustomer(Customer updateCustomer) {
		if(!existCustomerByEmail(updateCustomer.getEmail())) 
			throw new IllegalStateException("The user with email not exist");
		else if(updateCustomer.getEmail().length() > Customer.MAX_LENGTH)
			throw new  IllegalStateException("Email length is greater than: "+ Customer.MAX_LENGTH);
		
		//Obtener datos actuales de customer
		Customer customer = getCustomerById(updateCustomer.getIdCustomer());
		
		//Actualizar datos permitidos, menos email
		customer.setFistName(updateCustomer.getFistName());
		customer.setLastName(updateCustomer.getLastName());
		customer.setAvatar(updateCustomer.getAvatar());
		customer.setPassword(updateCustomer.getPassword());
		
		return customerRepository.save(customer);
	}

	@Override
	public String deleteCustomerById(long idCustomer) {
		Customer customer = getCustomerById(idCustomer);
		//customerRepository.delete(customer); Borrado de berdad
		customer.setActive(false);
		customerRepository.save(customer);
		return "The user was deleted with id "+ idCustomer;
	}
}
