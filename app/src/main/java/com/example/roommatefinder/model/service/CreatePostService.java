package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;

import java.io.IOException;

public class CreatePostService {
    public void addPosting(CreatePostingRequest request, LoginTaskFacade.Observer observer) throws IOException {
        //TODO: implement and call function that creates a posting in serviceFacade.
        //getServerFacade().login(request, observer);
    }

    private ServerFacade getServerFacade(){return new ServerFacade();}
}
