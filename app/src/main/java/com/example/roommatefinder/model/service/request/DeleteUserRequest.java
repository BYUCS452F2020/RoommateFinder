package com.example.roommatefinder.model.service.request;

public class DeleteUserRequest {
    private String email;
    private String authToken;

    public DeleteUserRequest(String email, String authToken) {
        this.email = email;
        this.authToken = authToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
