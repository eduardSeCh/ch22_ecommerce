package org.generation.app.repository;

import java.util.List;
import java.util.Optional;

import org.generation.app.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer,Long>{
	//Regresar clientes activos
	List<Customer> findAllByActive(boolean stateOfActive);
	//Buscar si esta el cliente por email
	boolean existsByEmail(String email);
	//Buscar por email
	Optional<Customer> findByEmail(String email);
}
