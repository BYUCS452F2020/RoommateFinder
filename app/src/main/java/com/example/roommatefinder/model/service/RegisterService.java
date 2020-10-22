package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.RegisterTaskFacade;

import java.io.IOException;

public class RegisterService {

    public void register(RegisterRequest request, RegisterTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        serverFacade.register(request, observer);
    }


    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
