package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.service.request.GetAuthTokenRequest;
import com.example.roommatefinder.model.service.response.GetAuthTokenResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;

public class GetAuthTokenTaskFacade extends AsyncTask<GetAuthTokenRequest, Void, GetAuthTokenResponse> {
    private Observer observer;

    public GetAuthTokenTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        public void onRetrieveAuthTokenSingleAuthResponse(GetAuthTokenResponse response);
        public void onRetrieveAuthTokenMultipleAuthResponse(GetAuthTokenResponse response);
        public void unsuccessful(GetAuthTokenResponse response);
    }

    @Override
    protected GetAuthTokenResponse doInBackground(GetAuthTokenRequest... getAuthTokenRequests) {
        AuthTokenTable authTokenTable = new AuthTokenTable();
        return authTokenTable.Query(getAuthTokenRequests[0]);
    }

    @Override
    protected void onPostExecute(GetAuthTokenResponse getAuthTokenResponse) {
        super.onPostExecute(getAuthTokenResponse);
        if (getAuthTokenResponse.isSuccess()) {
            if (getAuthTokenResponse.getAuthToken() != null) {
                observer.onRetrieveAuthTokenSingleAuthResponse(getAuthTokenResponse);
            }
            else if (getAuthTokenResponse.getAuthTokens() != null) {
                observer.onRetrieveAuthTokenMultipleAuthResponse(getAuthTokenResponse);
            }
        }
        else {
            observer.unsuccessful(getAuthTokenResponse);
        }
    }
}
