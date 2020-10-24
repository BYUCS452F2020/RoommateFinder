package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;

import java.io.IOException;

public class PreferenceService {

    public void getPreference(PreferenceRequest request, PreferenceTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        //TODO: Make and call getPreference function in ServerFacade.
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
