package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.CreatePreferenceTaskFacade;

import java.io.IOException;

public class CreatePreferenceService {

    public void createPreference(CreatePreferenceRequest request, CreatePreferenceTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call createPreference function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
