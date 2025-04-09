package no.hvl.dat109.group3.service;

/**
 * A service class to handle API calls to Places API.
 */

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.hvl.dat109.group3.model.Place;

@Service
public class PlacesService {

    private final String API_KEY = "AIzaSyAmRg7cpF8lrgX8jxDu56ZQ_QFSJe8rPLw";
    private final String SM_API_KEY = "AIzaSyB2zPFMedluwV8FV3m2kqf8HldPGCQywDo";
    private final String JS_MAPS_API_KEY = "AIzaSyAMrl0FSl-JZUbQKxM83dy6eLj4dqHlA4A";

    private final String TEXT_URL = "https://places.googleapis.com/v1/places:searchText";
    private final String NEARBY_URL = "https://places.googleapis.com/v1/places:searchNearby";

    private final String FIELD_MASK = "places.displayName,places.formattedAddress,places.rating,places.priceRange,places.primaryType,places.location";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PlacesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
	 * A method for searching by text input
	 * @param query The text input by the user
	 * @return A list of places
	 */
    public List<Place> searchByText(String query) {
        String requestBody = "{ " + "\"textQuery\": \"" + query + "\", " + "\"includedType\": \"restaurant\", "
                + "\"maxResultCount\": 20 " + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-Goog-Api-Key", API_KEY);
        headers.set("X-Goog-FieldMask", FIELD_MASK);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(TEXT_URL, HttpMethod.POST, entity, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return convertJsonToPlaces(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }
    }// end searchByText
	
	
	
	/**
	 * A method for showing all nearby restaurants
	 * @param latitude The latitude of the user location
	 * @param longitude The longitude of the user location
	 * @param radius The radius for the search
	 * @return A list of places
	 */
    public List<Place> searchNearby(String latitude, String longitude, String radius) {
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);

        String requestBody = "{ " + "\"includedTypes\": [\"restaurant\"], " + "\"maxResultCount\": 20, " + "\"locationRestriction\": { "
                + "\"circle\": { " + "\"center\": { " + "\"latitude\": " + lat + ", " + "\"longitude\": " + lon + "}, "
                + "\"radius\": " + radius + "} " + "} " + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-Goog-Api-Key", API_KEY);
        headers.set("X-Goog-FieldMask", FIELD_MASK);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(NEARBY_URL, HttpMethod.POST, entity, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return convertJsonToPlaces(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }
    }//end searchNearby
	
	
	
	/**
	 * A method to generate a random suggestion for a restaurant near the user
	  * @param latitude The latitude of the user location
	 * @param longitude The longitude of the user location
	 * @param radius The radius for the search
	 * @return A random place
	 */
    public Place getRandom(String latitude, String longitude, String radius) {
        List<Place> places = searchNearby(latitude, longitude, radius);
        int range = places.size() - 0 + 1;
        int i = (int) (Math.random() * range) + 1;
        return places.get(i);
    } //end getRandom
    

    /*public String getDynamicMapUrl(String query, String radius, String lat, String lon) {
    	String baseUrl = "https://www.google.com/maps/embed/v1/MAP_MODE?";
    	StringBuilder markers = new StringBuilder();
    	
    	if(query == "") {
    		markers.append("&search")
    	} else {
    		
    	}
    	
    	
    	return baseUrl + "key=" + DM_API_KEY + markers;
    }
    */
    /**
     * A method to get the URL for the static map view
     * @param places The list of places to be pinned on the map
     * @return A string containing the URL
     */
    public String getStaticMapUrl(List<Place> places) {
    	String baseUrl = "https://maps.googleapis.com/maps/api/staticmap?size=600x400";
    	StringBuilder markers = new StringBuilder();
    	
    	if (places != null && !places.isEmpty()) {
    		for (Place place : places) {
            
            markers.append("&markers=color:red%7C")
                   .append(place.getLatitude())
                   .append(",")
                   .append(place.getLongitude());
    		}
    	}
    
    	return baseUrl + markers.toString() + "&key=" + SM_API_KEY;
    } //end getStaticMapUrl


	/**
	 * A utility method for convering from a JsonNode to a list of POJOs
	 * @param jsonNode
	 * @return A list of Places
	 */
    private List<Place> convertJsonToPlaces(JsonNode jsonNode) {
        if (jsonNode.has("places")) {
            try {
                return objectMapper.readValue(jsonNode.get("places").toString(), new TypeReference<List<Place>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Error converting JSON to POJO", e);
            }
        }
        return List.of();
    } //end convertJsonToPlaces
}