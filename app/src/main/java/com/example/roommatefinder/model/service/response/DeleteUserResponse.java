package com.example.roommatefinder.model.service.response;

public class DeleteUserResponse extends Response {
    public DeleteUserResponse(boolean success) {
        super(success);
    }

    public DeleteUserResponse(boolean success, String message) {
        super(success, message);
    }
}
