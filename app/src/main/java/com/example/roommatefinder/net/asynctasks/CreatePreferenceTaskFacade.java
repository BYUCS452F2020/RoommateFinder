package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.net.DBDAO.PreferenceTable;

public class CreatePreferenceTaskFacade extends AsyncTask<CreatePreferenceRequest, Void, CreatePreferenceResponse> {
    private Observer observer;

    public interface Observer {
        public void onCreatePreferenceResponseReceived(CreatePreferenceResponse response);
    }

    public CreatePreferenceTaskFacade (Observer observer) {
        this.observer = observer;
    }

    @Override
    protected CreatePreferenceResponse doInBackground(CreatePreferenceRequest... requests) {
        //create preference
        PreferenceTable preferenceTable = new PreferenceTable();
        CreatePreferenceResponse response = preferenceTable.Create(requests[0]);

        return response;
    }
}
