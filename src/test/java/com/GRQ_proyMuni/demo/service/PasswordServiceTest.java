package com.GRQ_proyMuni.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordServiceTest {

    private final PasswordService passwordService = new PasswordService();

    @Test
    void hashesAndVerifiesPasswords() {
        String firstHash = passwordService.hash("contrasena-segura");
        String secondHash = passwordService.hash("contrasena-segura");

        assertNotEquals(firstHash, secondHash);
        assertTrue(passwordService.matches("contrasena-segura", firstHash));
        assertFalse(passwordService.matches("otra-contrasena", firstHash));
        assertTrue(passwordService.isEncoded(firstHash));
    }

    @Test
    void acceptsLegacyPlainPasswordsForMigration() {
        assertTrue(passwordService.matches("contrasena-anterior", "contrasena-anterior"));
        assertFalse(passwordService.matches("incorrecta", "contrasena-anterior"));
        assertFalse(passwordService.isEncoded("contrasena-anterior"));
    }
}
