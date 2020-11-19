package com.example.roommatefinder.model;

import java.io.Serializable;
import java.sql.Time;

public class AuthToken implements Serializable {
    private String authToken;
    private String email;
    private Time timeCreated;

    public AuthToken(String authToken) {
        this.authToken = authToken;
    }

    public AuthToken(String authToken, String email, Time timeCreated) {
        this.authToken = authToken;
        this.email = email;
        this.timeCreated = timeCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Time getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Time timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
