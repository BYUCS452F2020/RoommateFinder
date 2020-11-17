package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.UpdateAuthTokenResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;
import com.example.roommatefinder.net.DBDAO.PreferenceTable;

public class UpdatePreferenceTaskFacade extends AsyncTask<CreatePreferenceRequest, Void, CreatePreferenceResponse> {
    private Observer observer;

    public UpdatePreferenceTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        void onUpdateResponse(CreatePreferenceResponse response);
    }

    @Override
    protected CreatePreferenceResponse doInBackground(CreatePreferenceRequest... createPreferenceRequests) {
        PreferenceTable preferenceTable = new PreferenceTable();
        return preferenceTable.Update(createPreferenceRequests[0]);
    }

    @Override
    protected void onPostExecute(CreatePreferenceResponse createPreferenceResponse) {
        super.onPostExecute(createPreferenceResponse);
        observer.onUpdateResponse(createPreferenceResponse);
    }
}
