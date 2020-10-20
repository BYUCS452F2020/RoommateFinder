package com.example.roommatefinder.net;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;

public class ServerFacade {

    public LoginResponse login(LoginRequest request) {
        User user = new User("Test", "User", 'm', 25, "testuser@gmail.com",
                "password", "111-222-3333");
        return new LoginResponse(user, new AuthToken());
    }

    public RegisterResponse register(RegisterRequest request){
        User user = new User(request.getFirstName(), request.getLastName(), request.getGender(), request.getAge(),
                request.getEmail(), request.getPassword(), request.getPhoneNumber());

        return new RegisterResponse(user, new AuthToken());
    }
}
