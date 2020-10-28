package com.example.roommatefinder.model.service.request;

public class PreferenceRequest extends Request {
    //Used to query from Table
    private String email;

    public PreferenceRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
