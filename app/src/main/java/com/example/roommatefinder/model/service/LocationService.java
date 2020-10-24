package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.LocationTaskFacade;

import java.io.IOException;

public class LocationService {

    public void getLocation(LocationRequest request, LocationTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call location function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
