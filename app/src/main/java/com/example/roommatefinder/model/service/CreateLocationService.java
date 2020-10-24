package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.CreateLocationTaskFacade;

import java.io.IOException;

public class CreateLocationService {

    public void createLocation(CreateLocationRequest request, CreateLocationTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call createLocation function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
