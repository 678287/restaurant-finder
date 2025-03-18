package no.hvl.dat109.group3.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat109.group3.controller.RegisterController;
import no.hvl.dat109.group3.model.Users;
import no.hvl.dat109.group3.repository.UserRepository;
import no.hvl.dat109.group3.service.PasswordService;
import no.hvl.dat109.group3.util.InputValidator;

public class RegisterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordService passwordService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private RedirectAttributes ra;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testShowRegisterForm() throws Exception {
        mockMvc.perform(get("/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("registrer"));
    }

    @Test
    public void testSubmitRegistrationSuccess() throws Exception {
        Users user = new Users();
        user.setPhone("12345678");

        when(userRepo.existsByPhone("12345678")).thenReturn(false);
        when(passwordService.genererTilfeldigSalt()).thenReturn("salt");
        when(passwordService.hashMedSalt("password", "salt")).thenReturn("hash");

        mockMvc.perform(post("/submitregistration")
                .param("phone", "12345678")
                .param("password", "password")
                .param("passwordRepeated", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("confirmation"));

        ArgumentCaptor<Users> userCaptor = ArgumentCaptor.forClass(Users.class);
        verify(userRepo, times(1)).save(userCaptor.capture());

        Users capturedUser = userCaptor.getValue();
        assertEquals("12345678", capturedUser.getPhone());
        assertEquals("salt", capturedUser.getSalt());
        assertEquals("hash", capturedUser.getPasswordhash());

        verify(userRepo, times(1)).existsByPhone("12345678");
        verify(passwordService, times(1)).genererTilfeldigSalt();
        verify(passwordService, times(1)).hashMedSalt("password", "salt");
        verifyNoMoreInteractions(userRepo, passwordService);
    }

    @Test
    public void testSubmitRegistrationFailure() throws Exception {
        Users user = new Users();
        user.setPhone("12345678");

        when(userRepo.existsByPhone("12345678")).thenReturn(true);

        mockMvc.perform(post("/submitregistration")
                .param("phone", "12345678")
                .param("password", "password")
                .param("passwordRepeated", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("register"));

        verify(userRepo, times(1)).existsByPhone("12345678");
        verifyNoMoreInteractions(userRepo, passwordService);
    }
}