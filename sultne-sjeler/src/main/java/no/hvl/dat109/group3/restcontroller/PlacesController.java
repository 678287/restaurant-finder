package no.hvl.dat109.group3.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import no.hvl.dat109.group3.restservice.PlacesService;

@RestController
@RequestMapping("/places")
@CrossOrigin(origins = "http://localhost:8080") // Allow frontend calls
public class PlacesController {
	
	@Autowired
	private final PlacesService placesService;
	
	public PlacesController(PlacesService placesService) {
		this.placesService = placesService;
	}
	
	
	@GetMapping("/searchText")
	public Object searchText(@RequestParam String query) {
		return placesService.searchByText(query);
	}
	
	@GetMapping("/searchNearby")
	public Object searchNearby(@RequestParam String lat, @RequestParam String lon){
		
        return placesService.searchNearby(lat, lon);
	}

}
