package no.hvl.dat109.group3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import no.hvl.dat109.group3.model.Users;
import no.hvl.dat109.group3.repository.UserRepository;
import no.hvl.dat109.group3.service.PasswordService;
import no.hvl.dat109.group3.util.InputValidator;
import no.hvl.dat109.group3.util.LoginUtil;

@Controller
public class RegisterController {
	
	
	/**
	 * A custom binder that sets password as an exception to the databinding
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("password");
	} //end initBinder
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordService passwordService;
	
	
	/**
	 * A GET-mapping to show the form for registering new users
	 * @return "registrer" the view for the register form
	 */
	@GetMapping("/register")
	public String showRegisterForm() {
		
		return "registrer";
	} //end showRegisterForm
	
	/**
	 * A GET-mapping to show the confirmation for a successfully registered user
	 * @return "confirmation" the view for the confirmation
	 */
	@GetMapping("/confirmation")
	public String showConfirmation() {
		return "confirmation";
	}
	
	@PostMapping("/submitregistration")
	public String submitRegistration(@Valid @ModelAttribute("users") Users user, BindingResult bindingResult,
			@RequestParam String password, @RequestParam String passwordRepeated, RedirectAttributes ra, HttpServletRequest request) {
		
		// Validates the user object
		if(bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			
			ra.addFlashAttribute("RAerrorMessages", errorMessages);
			return "redirect:register";
		}
		
		// Validates the password
		if(!InputValidator.isValidPassword(password)) {
			ra.addFlashAttribute("RAerrorMessages", "Password is compulsory");
			return "redirect:register";
		}
		
		if(!InputValidator.isValidPasswordRepeated(password,  passwordRepeated)) {
			ra.addFlashAttribute("RAerrorMessages", "Repeated password must be identical to the first password");
		}
		
		if(userRepo.existsByPhone(user.getPhone())) {
			ra.addFlashAttribute("RAerrorMessages", "There already exists a user with the given phone number");
			return "redirect:register";
		}
		
		ra.addFlashAttribute("RAuser", user); // Adds the user to the session for the confirmation view
		
		user.setSalt(passwordService.genererTilfeldigSalt());
		user.setPasswordhash(passwordService.hashMedSalt(password, user.getSalt()));
		
		userRepo.save(user);
		LoginUtil.loggInnBruker(request, user.getPhone(), user.getPasswordhash());
		
		return "redirect:confirmation";
		
	} //end submitRegistration
	
	

}
