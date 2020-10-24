package com.example.roommatefinder.model.service.request;

public class LocationRequest extends Request {
    //query
    private String username;

    public LocationRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
