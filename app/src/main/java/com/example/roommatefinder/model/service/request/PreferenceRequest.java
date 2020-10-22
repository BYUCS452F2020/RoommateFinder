package com.example.roommatefinder.model.service.request;

public class PreferenceRequest {
    //Used to query from Table
    private String username;

    public PreferenceRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
