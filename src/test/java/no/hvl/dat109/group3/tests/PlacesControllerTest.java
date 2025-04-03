package no.hvl.dat109.group3.tests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import no.hvl.dat109.group3.controller.PlacesController;
import no.hvl.dat109.group3.model.Place;
import no.hvl.dat109.group3.restservice.PlacesService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlacesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlacesService placesService;

    @Mock
    private Model model;

    @InjectMocks
    private PlacesController placesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(placesController).build();
    }

    @Test
    public void testSearchText() throws Exception {
        List<Place> places = Collections.singletonList(new Place());
        when(placesService.searchByText("query")).thenReturn(places);

        mockMvc.perform(get("/places/searchText").param("query", "query"))
               .andExpect(status().isOk())
               .andExpect(view().name("listeresultat"))
               .andExpect(model().attribute("places", places));

        verify(placesService, times(1)).searchByText("query");
        verifyNoMoreInteractions(placesService);
    }

    @Test
    public void testSearchNearby() throws Exception {
        List<Place> places = Collections.singletonList(new Place());
        when(placesService.searchNearby("lat", "lon", "radius")).thenReturn(places);

        mockMvc.perform(get("/places/searchNearby").param("lat", "lat").param("lon", "lon").param("radius", "radius"))
               .andExpect(status().isOk())
               .andExpect(view().name("listeresultat"))
               .andExpect(model().attribute("places", places));

        verify(placesService, times(1)).searchNearby("lat", "lon", "radius");
        verifyNoMoreInteractions(placesService);
    }

    @Test
    public void testGetRandom() throws Exception {
        Place place = new Place();
        when(placesService.getRandom("lat", "lon", "radius")).thenReturn(place);

        mockMvc.perform(get("/places/getRandom").param("lat", "lat").param("lon", "lon").param("radius", "radius"))
               .andExpect(status().isOk())
               .andExpect(view().name("randomresultat"))
               .andExpect(model().attribute("place", place));

        verify(placesService, times(1)).getRandom("lat", "lon", "radius");
        verifyNoMoreInteractions(placesService);
    }
    
    @Test
    public void testSearchWithFilters() throws Exception {
        // Arrange
        Place place1 = new Place();
        place1.setRating(4.2);
        place1.setPriceLevel("PRICE_LEVEL_MODERATE"); // price level 2
        
        Place place2 = new Place();
        place2.setRating(3.8);
        place2.setPriceLevel("PRICE_LEVEL_INEXPENSIVE"); // price level 1
        
        List<Place> allPlaces = Arrays.asList(place1, place2);
        
        when(placesService.searchByText("pizza")).thenReturn(allPlaces);

        // Act & Assert - Test with both filters
        mockMvc.perform(get("/places/searchWithFilters")
                .param("query", "pizza")
                .param("minRating", "4.0")
                .param("maxPriceLevel", "2"))
               .andExpect(status().isOk())
               .andExpect(view().name("listeresultat"))
               .andExpect(model().attribute("places", List.of(place1)));

        verify(placesService, times(1)).searchByText("pizza");
        verifyNoMoreInteractions(placesService);
    }
    
    
 
}