package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.LogoutResponse;

public class AuthTokenTable implements DAOInterface<LoginRequest, LoginResponse, LogoutRequest, LogoutResponse> {
    @Override
    public LoginResponse Create(LoginRequest request) {
        //create authToken
        return null;
    }

    @Override
    public LoginResponse Update(LoginRequest request) {
        //Probably won't use this
        return null;
    }

    @Override
    public LogoutResponse Delete(LogoutRequest request) {
        //Use this when they Logout
        return null;
    }

    @Override
    public LogoutResponse Query(LogoutRequest request) {
        return null;
    }
}
