package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.net.ServerFacade;

import java.io.IOException;

public class LoginService {

    public LoginResponse login(LoginRequest request) throws IOException {

        LoginResponse loginResponse = getServerFacade().login(request);

        return loginResponse;
    }

    ServerFacade getServerFacade(){return new ServerFacade();}




}

