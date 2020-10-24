package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.CreateLocationService;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.net.asynctasks.CreateLocationTaskFacade;

import java.io.IOException;

public class CreateLocationPresenter implements CreateLocationTaskFacade.Observer {

    private final View view;

    @Override
    public void onCreateLocationResponseReceived(CreateLocationResponse response){
        view.onCreateLocationResult(response);
    }

    public interface View {
        void onCreateLocationResult(CreateLocationResponse response);
    }

    public CreateLocationPresenter(View view) {
        this.view = view;
    }

    public void createLocation(CreateLocationRequest request) throws IOException {
        CreateLocationService createLocationService = getCreateLocationService();
        createLocationService.createLocation(request, this);
    }

    public CreateLocationService getCreateLocationService() {
        return new CreateLocationService();
    }
}
