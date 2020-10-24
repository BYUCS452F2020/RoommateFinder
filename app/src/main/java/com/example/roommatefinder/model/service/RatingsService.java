package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.RatingsTaskFacade;

import java.io.IOException;

public class RatingsService {

    public void getRatings(RatingsRequest request, RatingsTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call getRatings function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
