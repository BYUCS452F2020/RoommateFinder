package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.CreatePostingTaskFacade;

import java.io.IOException;

public class CreatePostingService {
    public void createPosting(CreatePostingRequest request, CreatePostingTaskFacade.Observer observer) throws IOException {
        //TODO: implement and call function that creates a posting in serviceFacade.
        //getServerFacade().post(request, observer);
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
