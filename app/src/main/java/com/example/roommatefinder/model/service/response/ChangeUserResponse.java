package com.example.roommatefinder.model.service.response;

public class ChangeUserResponse extends Response {
    public ChangeUserResponse(boolean success) {
        super(success);
    }

    public ChangeUserResponse(boolean success, String message) {
        super(success, message);
    }
}
