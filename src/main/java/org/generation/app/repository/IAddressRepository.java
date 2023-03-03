package org.generation.app.repository;

import java.util.List;

import org.generation.app.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long>{
	//Devuelve el idCustomer de un Customer de fkIdCustomer
	List<Address> findAllByFkIdCustomerIdCustomer(long idCusotmer);
	List<Address> findAllByFkIdCustomerEmail(String email);
	List<Address> findAllByZipCode(String zipCode);
}
