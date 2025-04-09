package no.hvl.dat109.group3.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import no.hvl.dat109.group3.controller.LoginController;
import no.hvl.dat109.group3.controller.PlacesController;
import no.hvl.dat109.group3.controller.RegisterController;
import no.hvl.dat109.group3.controller.SearchController;
import no.hvl.dat109.group3.service.PasswordService;
import no.hvl.dat109.group3.service.PlacesService;

/**
 * This test class verifies that the Spring Boot application context loads correctly
 * and ensures that all necessary beans are created. 
 */

@SpringBootTest
class SultneSjelerApplicationTests {

    @Autowired
    private LoginController loginController;

    @Autowired
    private PlacesController placesController;

    @Autowired
    private RegisterController registerController;

    @Autowired
    private SearchController searchController;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private PlacesService placesService;

    @Test
    void contextLoads() {
    }

    @Test
    void testLoginControllerLoaded() {
        assertNotNull(loginController);
    }

    @Test
    void testPlacesControllerLoaded() {
        assertNotNull(placesController);
    }

    @Test
    void testRegisterControllerLoaded() {
        assertNotNull(registerController);
    }

    @Test
    void testSearchControllerLoaded() {
        assertNotNull(searchController);
    }

    @Test
    void testPasswordServiceLoaded() {
        assertNotNull(passwordService);
    }

    @Test
    void testPlacesServiceLoaded() {
        assertNotNull(placesService);
    }
}