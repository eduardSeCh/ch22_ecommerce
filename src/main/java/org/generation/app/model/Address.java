package org.generation.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="address")
public class Address {
	public static final int MAX_LENGTH = 160;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ip_address")
	private long idAddress;
	@Column(name="zip_code",nullable = false, length = MAX_LENGTH)
	private String zipCode;
	@Column(name="address", length = MAX_LENGTH)
	private String address;
	@Column(name="city", length = MAX_LENGTH)
	private String city;
	
	//Foreign key
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_id_customer")
	private Customer fkIdCustomer;

	public long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(long idAddress) {
		this.idAddress = idAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String ziṕCode) {
		this.zipCode = ziṕCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Customer getFkIdCustomer() {
		return fkIdCustomer;
	}

	public void setFkIdCustomer(Customer fkIdCustomer) {
		this.fkIdCustomer = fkIdCustomer;
	}
	
	
}
