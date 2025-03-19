package no.hvl.dat109.group3.controller;

/**
 * A controller class for controlling the service handling the "Places API"
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import no.hvl.dat109.group3.model.Place;
import no.hvl.dat109.group3.restservice.PlacesService;

@Controller
@RequestMapping("/places")
@CrossOrigin(origins = "http://localhost:8080") // Allow frontend calls
public class PlacesController {
	
	@Autowired
	private final PlacesService placesService;
	
	public PlacesController(PlacesService placesService) {
		this.placesService = placesService;
	}
	
	
	/**
	 * A GET-mapping to handle textbased searches
	 * @param query The text input by the user
	 * @param model
	 * @return "listeresultat.jsp" - The view containing a list of results
	 */
	@GetMapping("/searchText")
	public String searchText(@RequestParam String query, Model model) {
		List<Place> places = placesService.searchByText(query);
		
		model.addAttribute("places", places);
		
		return "listeresultat";
	} //end searchText
	
	
	/**
	 * A GET-mapping to search for all nearby restaurants
	 * @param lat The latitude of the user location
	 * @param lon The longitude of the user location
	 * @param radius The radius for the search
	 * @param model
	 * @return "listeresultat.jsp" - The view containing a list of results
	 */
	@GetMapping("/searchNearby")
	public String searchNearby(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius, Model model){
		
		List<Place> places = placesService.searchNearby(lat, lon, radius);
		
	    model.addAttribute("places", places);
	    
	    return "listeresultat";
	} //end searchNearby
	
	
	/**
	 * A GET-mapping to generate a random suggestion for the user
	 * @param lat The latitude of the user location
	 * @param lon The longitude of the user location
	 * @param radius The radius for the search
	 * @param model
	 * @return "listeresultat.jsp" - The view containing a list of results
	 */
	@GetMapping("/getRandom")
	public String getRandom(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius, Model model) {
		
		Place randomPlace = placesService.getRandom(lat, lon, radius);
		
		model.addAttribute("place", randomPlace);
		
		return "randomresultat";
	} //end getRandom

}
