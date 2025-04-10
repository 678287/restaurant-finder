package no.hvl.dat109.group3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.group3.model.DisplayName;
import no.hvl.dat109.group3.model.Location;
import no.hvl.dat109.group3.model.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteController {


    /**
     * Endpoint to add a restaurant to favorites.
     * Expects parameters from form.
     */
    @PostMapping("/favorite/add")
    public String addFavorite(@RequestParam("restaurantId") String restaurantId,
                              @RequestParam("displayName") String displayName,
                              @RequestParam("address") String address,
                              @RequestParam("rating") double rating,
                              @RequestParam("latitude") double latitude,
                              @RequestParam("longitude") double longitude,
                              HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        // Check if user is logged in
        if (session == null || session.getAttribute("username") == null) {
            model.addAttribute("error", "Please log in first to add favorites.");
            return "login";  // redirect user to login page
        }
        
        // Create a Place object and populate its fields
        Place favoritePlace = new Place();
        favoritePlace.setId(restaurantId); 

        
        DisplayName dn = new DisplayName();
        dn.setText(displayName);
        favoritePlace.setDisplayName(dn);
        favoritePlace.setAddress(address);
        favoritePlace.setRating(rating);
        
        // Create and set a Location instance
        Location loc = new Location();
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);
        favoritePlace.setLocation(loc);
        
        // Retrieve current favorites from session
        List<Place> favorites = (List<Place>) session.getAttribute("favorites");
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        favorites.add(favoritePlace);
        session.setAttribute("favorites", favorites);
        
        // Optionally, add a flash message (if using RedirectAttributes) or simply redirect
        return "redirect:/favorite/list";
    }
    
    /**
     * Endpoint to list all favorite restaurants.
     * Retrieves the favorites stored in session and forwards to favoriteList.jsp.
     */
    @GetMapping("/favorite/list")
    public String listFavorites(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            model.addAttribute("error", "Please log in to view favorites.");
            return "login";
        }
        
        List<Place> favorites = (List<Place>) session.getAttribute("favorites");
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        model.addAttribute("favorites", favorites);
        return "favoritesList";  // This corresponds to favoriteList.jsp
    }
}
