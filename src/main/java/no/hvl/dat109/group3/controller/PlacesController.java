package no.hvl.dat109.group3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
@CrossOrigin(origins = "http://localhost:8080")
public class PlacesController {

    @Autowired
    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/kartresultat")
    public String showMap(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius, Model model) {
        List<Place> places = placesService.searchNearby(lat, lon, radius);
        model.addAttribute("places", places);
        String staticMapUrl = placesService.getStaticMapUrl(places);
        model.addAttribute("staticMapUrl", staticMapUrl);
        return "kartresultat";
}

    @GetMapping("/searchText")
    public String searchText(@RequestParam String query, Model model) {
        List<Place> places = placesService.searchByText(query);
        model.addAttribute("places", places);
        model.addAttribute("query", query);
        return "listeresultat";
    }

    @GetMapping("/searchNearby")
    public String searchNearby(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius, Model model) {
        List<Place> places = placesService.searchNearby(lat, lon, radius);
        model.addAttribute("places", places);
        model.addAttribute("lon", lon);
        model.addAttribute("lat", lat);
        model.addAttribute("radius", radius);
        return "listeresultat";
    }

    @GetMapping("/getRandom")
    public String getRandom(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius, Model model) {
        Place randomPlace = placesService.getRandom(lat, lon, radius);
        model.addAttribute("place", randomPlace);
        return "randomresultat";
    }

    @GetMapping("/searchWithFilters")
    public String searchWithFilters(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String lat,
            @RequestParam(required = false) String lon,
            @RequestParam(required = false) String radius,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxPrice,
            Model model) {
        
        List<Place> places = new ArrayList<>();
        System.out.println(query);
        
        if(query == "") {
        	places = placesService.searchNearby(lat, lon, radius);
        } else {
        	places = placesService.searchByText(query);
        }
        
        
        if (minRating != null) {
            places = places.stream()
                    .filter(p -> p.getRating() >= minRating)
                    .collect(Collectors.toList());
        }
        
        if (maxPrice != null) {
            places = places.stream()
                    .filter(p -> p.getPriceRange() != null && p.getPriceRange().getMax() <= maxPrice)
                    .collect(Collectors.toList());
        }
        
        model.addAttribute("places", places);
        model.addAttribute("query", query);
        model.addAttribute("lon", lon);
        model.addAttribute("lat", lat);
        model.addAttribute("radius", radius);
        
        return "listeresultat";
    }
}