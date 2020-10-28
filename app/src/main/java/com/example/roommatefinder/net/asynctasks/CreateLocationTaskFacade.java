package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.net.DBDAO.LocationTable;

public class CreateLocationTaskFacade extends AsyncTask<CreateLocationRequest, Void, CreateLocationResponse> {
    private Observer observer;

    public interface Observer {
        public void onCreateLocationResponseReceived(CreateLocationResponse response);
    }

    public CreateLocationTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected CreateLocationResponse doInBackground(CreateLocationRequest... requests) {
        //create location
        LocationTable locationTable = new LocationTable();
        CreateLocationResponse response = null;
        response = locationTable.Create(requests[0]);
        /*if (location != null) {
            response = new CreateLocationResponse(true);
        }
        else {
            response = new CreateLocationResponse(false);
        }*/
        return response;
    }
}
