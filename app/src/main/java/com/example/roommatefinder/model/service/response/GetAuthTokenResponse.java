package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.AuthToken;

import java.util.List;

public class GetAuthTokenResponse extends Response {
    private AuthToken authToken;
    private List<AuthToken> authTokens;

    public GetAuthTokenResponse(List<AuthToken> authTokens) {
        super(true);
        this.authTokens = authTokens;
    }

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

    public List<AuthToken> getAuthTokens() {
        return authTokens;
    }

    public void setAuthTokens(List<AuthToken> authTokens) {
        this.authTokens = authTokens;
    }
}
