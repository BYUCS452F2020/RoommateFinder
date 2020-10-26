package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.PostingsTaskFacade;

import java.io.IOException;

public class PostingsService {
    public void getPostings(PostingsRequest request, PostingsTaskFacade.Observer observer) throws IOException {
        //TODO: implement and call function that creates a posting in serviceFacade.

    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
