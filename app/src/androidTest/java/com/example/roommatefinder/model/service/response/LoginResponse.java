package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;

public class LoginResponse extends Response {

    private User user;
    private AuthToken authToken;


    public LoginResponse(String message) {
        super(false, message);
    }


    public LoginResponse(User user, AuthToken authToken) {
        super(true, null);
        this.user = user;
        this.authToken = authToken;
    }


    public User getUser() {
        return user;
    }


    public AuthToken getAuthToken() {
        return authToken;
    }
}



