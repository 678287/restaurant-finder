package no.hvl.dat109.group3.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import no.hvl.dat109.group3.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	/**
	 * Finds a user by searching for the phone number
	 * @param phone the phone number the user is registered by
	 * @return The user registered in the database
	 */
	Users findByPhone(String phone);
	
	boolean existsByPhone(String phone);
}
