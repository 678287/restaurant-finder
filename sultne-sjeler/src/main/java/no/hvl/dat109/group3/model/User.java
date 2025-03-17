package no.hvl.dat109.group3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(schema = "user_schema")
public class User {
	
	private String phone;
	
	private String salt;
	
	private String passwordhash;
	
	public User() {}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	public User(String phone) {
		this.phone = phone;
	}
	
	
}
