package com.example.roommatefinder.model.service.response;

public class CreatePreferenceResponse extends Response {
    public CreatePreferenceResponse(boolean success) {
        super(success);
    }

    public CreatePreferenceResponse(boolean success, String message) {
        super(success, message);
    }
}
