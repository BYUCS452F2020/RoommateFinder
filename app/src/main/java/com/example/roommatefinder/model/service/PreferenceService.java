package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.CreatePreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;

import java.io.IOException;

public class PreferenceService {

    public void getPreference(PreferenceRequest request, PreferenceTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        serverFacade.getPreference(request, observer);
    }

    public void createPreference(CreatePreferenceRequest request, CreatePreferenceTaskFacade.Observer observer) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        serverFacade.addPreference(request, observer);
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
