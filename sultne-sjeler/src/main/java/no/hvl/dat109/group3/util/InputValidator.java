package no.hvl.dat109.group3.util;

public class InputValidator {
	/**
	 * En metode som sjekker om det er gitt et gyldig brukernavn (mobilnummer)
	 * @param mobil Mobilnummeret som skal sjekkes
	 * @return boolean true dersom brukernavnet er gyldig, false dersom det er ugyldig.
	 */
	public static boolean isValidUsername(String mobil) {
		return mobil != null && mobil.matches("^[0-9]{8}$");
	} //end isValidUsername
	
	/**
	 * En metode som sjekker om det er gitt et gyldig passord
	 * @param passord passordet som skal sjekkes 
	 * @return boolean true dersom passordet er gyldig, false dersom det er ugyldig
	 */
	public static boolean isValidPassword(String passord) {
		return passord != null && passord.matches("^.{7,}$");
	} //end isValidPassword
	
	/**
	 * En metode som sjekker om det gjentatte passordet er gyldig
	 * @param passord Det opprinnelige passordet
	 * @param passordRepetert Det gjentatte passordet
	 * @return boolean true dersom passordene er like, false dersom de er ulike
	 */
	public static boolean isValidPasswordRepeated(String passord, String passordRepetert) {
		return passordRepetert.equals(passord);
	} //end isValidPasswordRepeated
}
