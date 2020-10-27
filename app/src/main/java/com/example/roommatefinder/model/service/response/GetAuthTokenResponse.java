package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.AuthToken;

public class GetAuthTokenResponse extends Response {
    private AuthToken authToken;

    public GetAuthTokenResponse(AuthToken authToken) {
        super(true);
        this.authToken = authToken;
    }

    public GetAuthTokenResponse(boolean success) {
        super(success);
    }

    public GetAuthTokenResponse(String message) {
        super(false, message);
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
