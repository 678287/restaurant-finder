package no.hvl.dat109.group3.restservice;

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

    private final String TEXT_URL = "https://places.googleapis.com/v1/places:searchText";
    private final String NEARBY_URL = "https://places.googleapis.com/v1/places:searchNearby";

    private final String FIELD_MASK = "places.displayName,places.formattedAddress,places.rating,places.priceRange,places.primaryType";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PlacesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

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
    }

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
    }

    public Place getRandom(String latitude, String longitude, String radius) {
        List<Place> places = searchNearby(latitude, longitude, radius);
        int range = places.size() - 0 + 1;
        int i = (int) (Math.random() * range) + 1;
        return places.get(i);
    }

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
    }
}