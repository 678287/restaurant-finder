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
    
 
}