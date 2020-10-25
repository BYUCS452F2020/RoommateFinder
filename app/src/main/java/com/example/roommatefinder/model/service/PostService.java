package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;

import java.io.IOException;

public class PostService {
    public void addPosting(LoginRequest request, LoginTaskFacade.Observer observer) throws IOException {
        getServerFacade().login(request, observer);
    }

    private ServerFacade getServerFacade(){return new ServerFacade();}
}
