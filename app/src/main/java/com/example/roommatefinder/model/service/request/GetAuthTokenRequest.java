package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.AuthToken;

public class GetAuthTokenRequest {
    private String email;
    private AuthToken token;
    public GetAuthTokenRequest(String email) {
        this.email = email;
    }
    public GetAuthTokenRequest(AuthToken token) {
        this.token = token;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
