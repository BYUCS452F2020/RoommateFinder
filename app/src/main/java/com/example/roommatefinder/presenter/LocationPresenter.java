package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.LocationService;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.net.asynctasks.LocationTaskFacade;

import java.io.IOException;

public class LocationPresenter implements LocationTaskFacade.Observer {

    private final View view;

    @Override
    public void onLocationResponseReceived(LocationResponse response) {
        view.onLocationResult(response);
    }

    public interface View  {
        public void onLocationResult(LocationResponse response);
    }

    public LocationPresenter(View view){
        this.view = view;
    }

    public void getLocation(LocationRequest request) throws IOException {
        LocationService locationService = getLocationService();
    }

    public LocationService getLocationService() {
        return new LocationService();
    }
}
