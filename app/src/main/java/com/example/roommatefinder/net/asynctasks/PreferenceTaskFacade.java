package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.net.DBDAO.PreferenceTable;

public class PreferenceTaskFacade extends AsyncTask<PreferenceRequest, Void, PreferenceResponse> {

    private Observer observer;

    public interface Observer {
        public void onPreferenceResponseReceived(PreferenceResponse response);
    }

    public PreferenceTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected PreferenceResponse doInBackground(PreferenceRequest... requests) {
        PreferenceTable preferenceTable = new PreferenceTable();
        PreferenceResponse response = preferenceTable.Query(requests[0]);
        if (response.getPreference() != null) {
            response = new PreferenceResponse(response.getPreference(), true);
        }
        else {
            response = new PreferenceResponse(null, false);
        }
        return response;
    }

    @Override
    protected void onPostExecute(PreferenceResponse preferenceResponse){
        super.onPostExecute(preferenceResponse);
        observer.onPreferenceResponseReceived(preferenceResponse);
    }
}
