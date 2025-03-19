package no.hvl.dat109.group3.model;

/**
 * An entity class for registering and saving users
 */
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "user_schema")
public class Users {
	
	@Id
	@Pattern(regexp = "^[0-9]{8}$", message="Phone number must be exactly 8 digits")
	@NotNull(message="Phone number is obligatory")
	private String phone;
	
	private String salt;
	
	private String passwordhash;
	
	public Users() {}
	
	public Users(String phone) {
		this.phone = phone;
	}
	
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

	
	
	
}
