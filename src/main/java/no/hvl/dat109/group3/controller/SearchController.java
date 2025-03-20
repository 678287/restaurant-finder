package no.hvl.dat109.group3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
	
	@GetMapping("/hjemmeside")
	public String showStartingPage() {
		return "hjemmeside";
		
	}

}
