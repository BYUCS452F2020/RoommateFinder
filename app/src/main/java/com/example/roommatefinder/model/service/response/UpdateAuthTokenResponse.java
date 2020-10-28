package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.AuthToken;

public class UpdateAuthTokenResponse extends Response {
    private AuthToken newAuthToken;

    public UpdateAuthTokenResponse(AuthToken newAuthToken) {
        super(true, null);
        this.newAuthToken = newAuthToken;
    }

    public UpdateAuthTokenResponse(boolean success) {
        super(success);
    }

    public UpdateAuthTokenResponse(boolean success, String message) {
        super(success, message);
    }

    public AuthToken getNewAuthToken() {
        return newAuthToken;
    }

    public void setNewAuthToken(AuthToken newAuthToken) {
        this.newAuthToken = newAuthToken;
    }
}
