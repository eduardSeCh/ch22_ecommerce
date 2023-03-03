package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Address;
import org.generation.app.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressCervice implements IAddressService{

	@Autowired
	IAddressRepository addressRespository;
	
	@Override
	public List<Address> getAllAddress() {
		List<Address> allAdresses = (List<Address>) addressRespository.findAll();
		return allAdresses;
	}

	@Override
	public List<Address> getAllActiveAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Address getAddressById(long idAddress) {
		// TODO Auto-generated method stub
		return addressRespository.findById(idAddress)
			.orElseThrow(() -> new IllegalStateException("The address is not found..."));
	}

	@Override
	public List<Address> getAllAddreessByFkIdCustomer(long idCusomer) {
		return addressRespository.findAllByFkIdCustomerIdCustomer(idCusomer);
	}
	
	@Override
	public Address setAddress(Address address) {
		return addressRespository.save(address);
	}

	@Override
	public Address updateAddress(Address updateAddress) {
		Address address = getAddressById(updateAddress.getIdAddress());
		address.setAddress(updateAddress.getAddress());
		address.setZipCode(updateAddress.getZipCode());
		address.setCity(updateAddress.getCity());
		return addressRespository.save(address);
	}

	@Override
	public String deleteAddressById(long idAddress) {
		Address address = getAddressById(idAddress);
		addressRespository.delete(address); //Borrar de verdad
		return "The address was deleted with id "+ idAddress;
	}


}
