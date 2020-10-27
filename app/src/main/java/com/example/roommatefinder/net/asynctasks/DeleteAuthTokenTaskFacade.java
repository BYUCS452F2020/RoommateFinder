package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.response.LogoutResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;

public class DeleteAuthTokenTaskFacade extends AsyncTask<LogoutRequest, Void, LogoutResponse> {
    private Observer observer;

    public DeleteAuthTokenTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        public void onDeleteAuthTokenResponse(LogoutResponse response);
    }
    @Override
    protected LogoutResponse doInBackground(LogoutRequest... logoutRequests) {
        AuthTokenTable authTokenTable = new AuthTokenTable();
        return authTokenTable.Delete(logoutRequests[0]);
    }

    @Override
    protected void onPostExecute(LogoutResponse logoutResponse) {
        super.onPostExecute(logoutResponse);
        observer.onDeleteAuthTokenResponse(logoutResponse);
    }
}
