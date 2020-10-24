package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.CreateRatingTaskFacade;

import java.io.IOException;

public class CreateRatingService {

    public void createRating(CreateRatingRequest request, CreateRatingTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call createRating function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
