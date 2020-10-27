package com.example.roommatefinder.model.service.request;

public class UpdateAuthTokenRequest {
    private String oldAuthToken;
    private String newAuthToken;

    public UpdateAuthTokenRequest(String oldAuthToken, String newAuthToken) {
        this.oldAuthToken = oldAuthToken;
        this.newAuthToken = newAuthToken;
    }

    public String getOldAuthToken() {
        return oldAuthToken;
    }

    public void setOldAuthToken(String oldAuthToken) {
        this.oldAuthToken = oldAuthToken;
    }

    public String getNewAuthToken() {
        return newAuthToken;
    }

    public void setNewAuthToken(String newAuthToken) {
        this.newAuthToken = newAuthToken;
    }
}
