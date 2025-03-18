package no.hvl.dat109.group3.restservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlacesService {

	private final String API_KEY = "AIzaSyAmRg7cpF8lrgX8jxDu56ZQ_QFSJe8rPLw";
	private final String TEXT_URL = "https://places.googleapis.com/v1/places:searchText";
	
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	
	public PlacesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}
	
	public JsonNode searchByText(String query) {
		
		// Sets up the JSON request body dynamically with the query input, filtering to only include restaurants
		String requestBody = "{ " +
                "\"textQuery\": \"" + query + "\", " +
                "\"includedType\": \"restaurant\", " +
                "\"maxResultCount\": 20 " +
                "}";
        
		// Sets up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json"); // Sets content type to JSON, as Places API expects JSON
        headers.set("X-Goog-Api-Key", API_KEY); // Submits our API key
        headers.set("X-Goog-FieldMask", "places.displayName,places.formattedAddress"); // Specifies which fields we want in the response: change after things work
        
        
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers); // Wraps the request body and headers
        ResponseEntity<String> response = restTemplate.exchange(TEXT_URL, HttpMethod.POST, entity, String.class); // Sends the POST request and retrieves the API response
        
        
        // Attempts to use ObjectMapper.readTree() to parse the response body into a JSON tree structure
        try {
        	return objectMapper.readTree(response.getBody());
        } catch (Exception e) {
        	throw new RuntimeException("Failed to parse API response", e);
        }

	}
	
}
