package com.Shadows.SpringZ.api.auth;

/**
 * Authentication response.
 */
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;

    public JwtResponse(String token, Long id, String email) {
        this.token = token;
        this.id = id;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
