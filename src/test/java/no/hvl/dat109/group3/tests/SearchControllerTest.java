package no.hvl.dat109.group3.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test class for SearchController.
 * Verifies the basic functionality of the search controller,
 * specifically testing the display of the home page.
 * 
 * Uses Spring Boot's testing support with MockMvc.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowStartingPage() throws Exception {
        mockMvc.perform(get("/hjemmeside"))
               .andExpect(status().isOk())
               .andExpect(view().name("hjemmeside"));
    }
}
