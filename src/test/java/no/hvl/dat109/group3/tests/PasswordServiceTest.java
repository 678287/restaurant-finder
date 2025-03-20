package no.hvl.dat109.group3.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.group3.service.PasswordService;

public class PasswordServiceTest {

    private PasswordService passwordService;

    @BeforeEach
    public void setup() {
        passwordService = new PasswordService();
    }

    @Test
    public void testGenererTilfeldigSalt() {
        String salt = passwordService.genererTilfeldigSalt();
        assertNotNull(salt);
        assertEquals(32, salt.length()); 
    }

    @Test
    public void testHashMedSalt() {
        String salt = passwordService.genererTilfeldigSalt();
        String hash = passwordService.hashMedSalt("password", salt);
        assertNotNull(hash);
        assertEquals(64, hash.length()); 
    }

    @Test
    public void testErKorrektPassord() {
        String salt = passwordService.genererTilfeldigSalt();
        String hash = passwordService.hashMedSalt("password", salt);
        assertTrue(passwordService.erKorrektPassord("password", salt, hash));
        assertFalse(passwordService.erKorrektPassord("wrongpassword", salt, hash));
    }
}