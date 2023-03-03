package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Address;

public interface IAddressService {
	public List<Address> getAllAddress();
	public List<Address> getAllActiveAddress();
	
	public Address getAddressById(long idAddress);
	public Address setAddress(Address Address);
	public Address updateAddress(Address Address);
	public String deleteAddressById(long idAddress);
	
	public List<Address> getAllAddreessByFkIdCustomer(long idCusomer);
}
