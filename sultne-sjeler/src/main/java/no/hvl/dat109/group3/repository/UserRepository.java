package no.hvl.dat109.group3.repository;
/**
 * A repository class for handling users in the database
 */
import org.springframework.data.jpa.repository.JpaRepository;


import no.hvl.dat109.group3.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	/**
	 * Finds a user by searching for the phone number
	 * @param phone the phone number the user is registered by
	 * @return The user registered in the database
	 */
	Users findByPhone(String phone);
	
	/**
	 * Checks if a user is already registered by the given phone number
	 * @param phone the phone number to check
	 * @return true if a user already exists with that number, false if not
	 */
	boolean existsByPhone(String phone);
}
