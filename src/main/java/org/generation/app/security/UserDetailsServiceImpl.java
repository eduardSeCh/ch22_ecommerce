package org.generation.app.security;

import org.generation.app.model.Customer;
import org.generation.app.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		//Leer de la base de datos para encotrar al cliente
		Customer customer = customerRepository.findByEmail(userEmail)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+userEmail));
		return new UserDetailsImpl(customer);
	}

}
