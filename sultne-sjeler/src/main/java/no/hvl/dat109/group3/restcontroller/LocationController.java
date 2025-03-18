package no.hvl.dat109.group3.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:8080") // Allow frontend calls
public class LocationController {

	
	@GetMapping
	public ResponseEntity<Map<String, String>> searchNearby(@RequestParam double lat, @RequestParam double lon) {
		Map<String, String> response = new HashMap<>();
	    response.put("message", "Received location");
	    response.put("latitude", String.valueOf(lat));
	    response.put("longitude", String.valueOf(lon));

	    return ResponseEntity.ok(response);
	}
}
