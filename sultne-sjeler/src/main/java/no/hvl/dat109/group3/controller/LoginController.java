package no.hvl.dat109.group3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat109.group3.model.User;
import no.hvl.dat109.group3.repository.UserRepository;
import no.hvl.dat109.group3.service.PasswordService;
import no.hvl.dat109.group3.util.InputValidator;
import no.hvl.dat109.group3.util.LoginUtil;

@Controller
public class LoginController {
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private UserRepository userRepo;

	/**
	 * A GET-Mapping to show the form for user login
	 * @return login.jsp - the view for user login
	 */
	@GetMapping
	public String getLoginForm() {
		return "login";
	} //end getLoginForm
	
	
	/**
	 * A POST-mapping to check if username and password is valid, and logs the user in if valid. Redirects back to login page if invalid
	 * @param phone The username
	 * @param password The password
	 * @param request HttpServletRequest
	 * @param ra RedirectAttributes
	 * @return redirect:login if invalid data, redirect:hjemmeside if valid data
	 */
	@PostMapping
	public String tryLogin(@RequestParam String phone, @RequestParam String password,
			HttpServletRequest request, RedirectAttributes ra) {
		
		User user = userRepo.findByPhone(phone);
		
		if(!InputValidator.isValidUsername(phone) || !passwordService.erKorrektPassord(password, user.getSalt(), user.getPasswordhash())) {
			ra.addFlashAttribute("redirectMessage", "Username and/or password is not valid.");
			return "redirect:login";
		}
		
		LoginUtil.loggInnBruker(request, phone, password);
		
		return "redirect:hjemmeside";
	} //end tryLogin
}
