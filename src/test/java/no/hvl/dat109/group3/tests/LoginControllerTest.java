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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat109.group3.controller.LoginController;
import no.hvl.dat109.group3.model.Users;
import no.hvl.dat109.group3.repository.UserRepository;
import no.hvl.dat109.group3.service.PasswordService;
import no.hvl.dat109.group3.util.InputValidator;
import no.hvl.dat109.group3.util.LoginUtil;

/**
 * Test class for LoginController.
 * Verifies the functionality of user login processes including:
 * - Successful login attempts
 * - Failed login attempts
 * 
 * Uses MockMvc for simulating HTTP requests and Mockito for mocking dependencies.
 */

public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PasswordService passwordService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private RedirectAttributes ra;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testTryLoginSuccess() throws Exception {
        Users user = new Users();
        user.setPhone("12345678");
        user.setSalt("salt");
        user.setPasswordhash("hash");

        when(userRepo.findByPhone("12345678")).thenReturn(user);
        when(passwordService.erKorrektPassord("password", "salt", "hash")).thenReturn(true);

        mockMvc.perform(post("/tryLogin")
                .param("phone", "12345678")
                .param("password", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("confirmation"));

        verify(userRepo, times(1)).findByPhone("12345678");
        verify(passwordService, times(1)).erKorrektPassord("password", "salt", "hash");
        verifyNoMoreInteractions(userRepo, passwordService);
    }

    @Test
    public void testTryLoginFailure() throws Exception {
        Users user = new Users();
        user.setPhone("12345678");
        user.setSalt("salt");
        user.setPasswordhash("hash");

        when(userRepo.findByPhone("12345678")).thenReturn(user);
        when(passwordService.erKorrektPassord("password", "salt", "hash")).thenReturn(false);

        mockMvc.perform(post("/tryLogin")
                .param("phone", "12345678")
                .param("password", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("login"));

        verify(userRepo, times(1)).findByPhone("12345678");
        verify(passwordService, times(1)).erKorrektPassord("password", "salt", "hash");
        verifyNoMoreInteractions(userRepo, passwordService);
    }
}