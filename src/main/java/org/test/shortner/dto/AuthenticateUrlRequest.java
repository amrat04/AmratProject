package org.test.shortner.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  AuthenticateUrlRequest : Request entity for Authentication URL
 */
public class AuthenticateUrlRequest {

    private String username;

    private String password;

    @JsonCreator
    public AuthenticateUrlRequest() { }

    @JsonCreator
    public AuthenticateUrlRequest(@JsonProperty("username") String username,
                                  @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
