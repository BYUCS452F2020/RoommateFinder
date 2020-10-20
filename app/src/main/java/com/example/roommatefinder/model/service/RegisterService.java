package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.ServerFacade;

import java.io.IOException;

public class RegisterService {

    public RegisterResponse register(RegisterRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        RegisterResponse registerResponse = serverFacade.register(request);


        return registerResponse;
    }


    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
