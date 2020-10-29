package com.example.roommatefinder.model.service.response;

public class CreateLocationResponse extends Response {
    public CreateLocationResponse(boolean success) {
        super(success);
    }

    public CreateLocationResponse(boolean success, String message) {
        super(success, message);
    }
}
