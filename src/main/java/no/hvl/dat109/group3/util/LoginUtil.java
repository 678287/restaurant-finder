package no.hvl.dat109.group3.util;

/**
 * En klasse for å gi utilities til innlogging og utlogging av brukeren
 * @author Jonatan
 * Sist oppdatert 17.03.2025
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginUtil {

	/**
	 * En util for å logge ut brukeren. Dersom det er en aktiv session, så invalideres denne.
	 * @param session
	 */
	public static void loggUtBruker(HttpSession session) {
		
		if(session != null) {
			session.invalidate();
		}
	} //end loggUtBruker
	
	
	/**
	 * En util for å logge inn brukeren. 
	 * @param request
	 * @param username
	 * @param password
	 */
	public static void loggInnBruker(HttpServletRequest request, String username, String password) {
		
		loggUtBruker(request.getSession()); // Dersom bruker allerede er innlogget, logges bruker ut før hen logges inn igjen
		
		HttpSession sesjon = request.getSession();
		sesjon.setAttribute("username", username); // Lagrer brukernavnet
		
		
		
		sesjon.setMaxInactiveInterval(120); // Bruker logges ut etter 2 minutter med inaktivitet
	} //end loggInnBruker
	
	
	/**
	 * En utility for å sjekke om brukeren er innlogget. Dersom bruker ikke har session eller brukernavn, så kan vi anta at de ikke er innlogget.
	 * @param session
	 * @return boolean True dersom innlogget, false dersom ikke innlogget
	 */
	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("username") != null;
	} //end erBrukerInnlogget

}
