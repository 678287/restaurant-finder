package no.hvl.dat109.group3.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.hvl.dat109.group3.restservice.PlacesService;

@RestController
@RequestMapping("/places")
public class PlacesController {
	
	@Autowired
	private final PlacesService placesService;
	
	public PlacesController(PlacesService placesService) {
		this.placesService = placesService;
	}
	
	
	@GetMapping("/search")
	public Object searchRestaurants(@RequestParam String query) {
		return placesService.searchByText(query);
	}

}
