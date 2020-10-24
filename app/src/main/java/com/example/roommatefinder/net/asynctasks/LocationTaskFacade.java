package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.net.DBDAO.LocationTable;

public class LocationTaskFacade extends AsyncTask<LocationRequest, Void, LocationResponse> {

    private Observer observer;

    public interface Observer {
        public void onLocationResponseReceived(LocationResponse response);
    }

    public LocationTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected LocationResponse doInBackground(LocationRequest... locationRequests) {
        LocationTable locationTable = new LocationTable();
        LocationResponse response = locationTable.Query(locationRequests[0]);

        return response;
    }

    @Override
    protected void onPostExecute(LocationResponse locationResponse) {
        super.onPostExecute(locationResponse);
        //call observer
        observer.onLocationResponseReceived(locationResponse);
    }
}
