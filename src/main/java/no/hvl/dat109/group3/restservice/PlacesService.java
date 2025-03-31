package no.hvl.dat109.group3.restservice;

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

	// The API call URL for textbased searches
	private final String TEXT_URL = "https://places.googleapis.com/v1/places:searchText";

	// The API call URL for nearby searches
	private final String NEARBY_URL = "https://places.googleapis.com/v1/places:searchNearby";

	// The url for included fields in search results
	private final String FIELD_MASK = "places.displayName,places.formattedAddress,places.rating,places.priceRange,places.primaryType";

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public PlacesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	/**
	 * A method for searching by text input
	 * 
	 * @param query The text input by the user
	 * @return A list of places
	 */
	public List<Place> searchByText(String query) {

		// Sets up the JSON request body dynamically with the query input, filtering to
		// only include restaurants
		String requestBody = "{ " + "\"textQuery\": \"" + query + "\", " + "\"includedType\": \"restaurant\", "
				+ "\"maxResultCount\": 20 " + "}";

		// Sets up headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json"); // Sets content type to JSON, as Places API expects JSON
		headers.set("X-Goog-Api-Key", API_KEY); // Submits our API key
		headers.set("X-Goog-FieldMask", FIELD_MASK); // Specifies which fields we want in the response: change after
														// things work

		HttpEntity<String> entity = new HttpEntity<>(requestBody, headers); // Wraps the request body and headers
		ResponseEntity<String> response = restTemplate.exchange(TEXT_URL, HttpMethod.POST, entity, String.class); // Sends
																													// the
																													// POST
																													// request
																													// and
																													// retrieves
																													// the
																													// API
																													// response

		// Attempts to use ObjectMapper.readTree() to parse the response body into a
		// JSON tree structure
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			return convertJsonToPlaces(jsonNode);
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse API response", e);
		}

	} // end searchByText

	/**
	 * A method for showing all nearby restaurants
	 * 
	 * @param latitude  The latitude of the user location
	 * @param longitude The longitude of the user location
	 * @param radius    The radius for the search
	 * @return A list of places
	 */
	public List<Place> searchNearby(String latitude, String longitude, String radius) {

		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);

		// Request body in JSON format
		String requestBody = "{ " + "\"includedTypes\": [\"restaurant\"], " + "\"maxResultCount\": 20, " + // Limit to
																											// 20 places
				"\"locationRestriction\": { " + "\"circle\": { " + "\"center\": { " + "\"latitude\": " + lat + ", "
				+ "\"longitude\": " + lon + "}, " + "\"radius\": " + radius + // Search radius in meters
				"} " + "} " + "}";

		// Sets up headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("X-Goog-Api-Key", API_KEY);
		headers.set("X-Goog-FieldMask", FIELD_MASK);

		HttpEntity<String> entity = new HttpEntity<>(requestBody, headers); // Wraps the request body and headers
		ResponseEntity<String> response = restTemplate.exchange(NEARBY_URL, HttpMethod.POST, entity, String.class); // Sends
																													// the
																													// POST
																													// request
																													// and
																													// retrieves
																													// the
																													// API
																													// response

		// Parse and return JSON response
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			return convertJsonToPlaces(jsonNode);
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse API response", e);
		}
	} // end searchNearby

	/**
	 * A method to generate a random suggestion for a restaurant near the user
	 * 
	 * @param latitude  The latitude of the user location
	 * @param longitude The longitude of the user location
	 * @param radius    The radius for the search
	 * @return A random place
	 */
	public Place getRandom(String latitude, String longitude, String radius) {

		List<Place> places = searchNearby(latitude, longitude, radius);

		int range = places.size() - 0 + 1;
		int i = (int) (Math.random() * range) + 1;

		return places.get(i);

	} // end getRandom

	/**
	 * A utility method for convering from a JsonNode to a list of POJOs
	 * 
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
	} // end convertJsonToPlaces

	public List<Place> searchByFilters(String query, String cost, String foodType, String visitorCount) {
		List<Place> places = searchByText(query);

		if (cost != null && !cost.isEmpty()) {
			places = places.stream().filter(place -> place.getCost().equalsIgnoreCase(cost))
					.collect(Collectors.toList());
		}

		if (foodType != null && !foodType.isEmpty()) {
			places = places.stream().filter(place -> place.getFoodType().equalsIgnoreCase(foodType))
					.collect(Collectors.toList());
		}

		if (visitorCount != null && !visitorCount.isEmpty()) {
			places = places.stream().filter(place -> place.getVisitorCount().equalsIgnoreCase(visitorCount))
					.collect(Collectors.toList());
		}

		return places;
	}

}
