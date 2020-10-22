package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.LogoutResponse;

public class AuthTokenTable implements DAOInterface<LoginRequest, LoginResponse, LogoutRequest, LogoutResponse> {
    @Override
    public LoginResponse Create(LoginRequest request) {
        //create authToken
        return new LoginResponse(new User("Test", "User", 'm', 25, "testuser@gmail.com",
                "password", "111-222-3333"), new AuthToken("Trump sucks"));
    }

    @Override
    public LoginResponse Update(LoginRequest request) {
        //Probably won't use this
        return new LoginResponse(new User("Test", "User", 'm', 25, "testuser@gmail.com",
                "password", "111-222-3333"), new AuthToken("Trump sucks"));
    }

    @Override
    public LogoutResponse Delete(LogoutRequest request) {
        //Use this when they Logout
        return new LogoutResponse(true);
    }

    @Override
    public LogoutResponse Query(LogoutRequest request) {
        return new LogoutResponse(true);
    }
}
