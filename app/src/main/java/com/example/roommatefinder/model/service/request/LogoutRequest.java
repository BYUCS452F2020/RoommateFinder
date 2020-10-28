package com.example.roommatefinder.model.service.request;

public class LogoutRequest extends Request {
    private String token;

    public LogoutRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
