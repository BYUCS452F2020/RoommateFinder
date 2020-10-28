package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.model.service.response.UpdateAuthTokenResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;
import com.example.roommatefinder.net.SQLAccess;

public class UpdateAuthTokenTaskFacade extends AsyncTask<UpdateAuthTokenRequest, Void, UpdateAuthTokenResponse> {
    private Observer observer;

    public UpdateAuthTokenTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        public void onUpdateTokenResponse(UpdateAuthTokenResponse response);
    }

    @Override
    protected UpdateAuthTokenResponse doInBackground(UpdateAuthTokenRequest... updateAuthTokenRequests) {
        AuthTokenTable authTokenTable = new AuthTokenTable();
        return authTokenTable.Update(updateAuthTokenRequests[0]);
    }

    @Override
    protected void onPostExecute(UpdateAuthTokenResponse updateAuthTokenResponse) {
        super.onPostExecute(updateAuthTokenResponse);
        observer.onUpdateTokenResponse(updateAuthTokenResponse);
    }
}
