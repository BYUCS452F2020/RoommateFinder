package com.example.roommatefinder.model.service.response;

public class LogoutResponse extends Response {
    public LogoutResponse(boolean success) {
        super(success);
    }

    public LogoutResponse(String message) {
        super(false, message);
    }
}
