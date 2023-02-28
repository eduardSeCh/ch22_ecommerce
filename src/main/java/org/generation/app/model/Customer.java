package org.generation.app.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Table(name="customer") //nombre que esta en la base de datos para que la busque
public class Customer {
	public static final int MAX_LENGTH = 150;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_customer")
	private long idCustomer;
	@Column(name="fist_name",nullable = false, length = MAX_LENGTH)
	private String fistName;
	@Column(name="last_name", length = MAX_LENGTH)
	private String lastName;
	@Column(name="email",updatable = false,unique = true, length = MAX_LENGTH)
	private String email;
	@Column(name="password",nullable = false, length = MAX_LENGTH)
	private String password;
	@Column(name="avatar", length = MAX_LENGTH)
	private String avatar;
	@Column(name="is_active")
	private boolean active;
	
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
