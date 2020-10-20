package com.example.roommatefinder.net;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;

public class ServerFacade {

    public LoginResponse login(LoginRequest request) {
        User user = new User("Test", "User", 'm', 25, "testuser@gmail.com",
                "password", "111-222-3333");
        return new LoginResponse(user, new AuthToken());
    }
}
