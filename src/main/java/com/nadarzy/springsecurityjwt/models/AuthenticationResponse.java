package com.nadarzy.springsecurityjwt.models;

/**
 * Created by Julian Nadarzy on 17/09/2021
 */
public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
