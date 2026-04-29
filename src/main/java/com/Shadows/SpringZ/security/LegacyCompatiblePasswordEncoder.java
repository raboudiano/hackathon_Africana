package com.Shadows.SpringZ.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password encoder that keeps the existing project usable.
 *
 * Background:
 * - SpringZ's existing CRUD screens store passwords as plain text.
 * - JWTProject uses BCrypt.
 *
 * Design choice:
 * - Encode NEW passwords using Spring's DelegatingPasswordEncoder (bcrypt by default).
 * - Accept legacy plain-text passwords during login so existing DB data continues to work.
 *
 * This is a pragmatic transition strategy; for production you should migrate all passwords
 * to bcrypt and remove the legacy fallback.
 */
public class LegacyCompatiblePasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder delegate = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null) {
            return false;
        }

        // If the stored value has an encoding id prefix (e.g., {bcrypt}), delegate to Spring.
        if (encodedPassword.startsWith("{")) {
            return delegate.matches(rawPassword, encodedPassword);
        }

        // Legacy plain-text support.
        return encodedPassword.contentEquals(rawPassword);
    }
}
