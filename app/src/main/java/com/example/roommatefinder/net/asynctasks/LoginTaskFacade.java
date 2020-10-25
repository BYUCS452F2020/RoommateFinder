package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;
import com.example.roommatefinder.net.DBDAO.UserTable;

public class LoginTaskFacade extends AsyncTask<LoginRequest, Void, LoginResponse> {
    private Observer observer;

    public interface Observer {
        public void onLoginResponseReceived(LoginResponse response);
    }

    public LoginTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected LoginResponse doInBackground(LoginRequest... loginRequests) {
        //query the user
        //create a token
        LoginResponse response = null;
        UserTable userTable = new UserTable();
        User user = userTable.Query(loginRequests[0]);
        if (user != null) {
            AuthTokenTable authTokenTable = new AuthTokenTable();
            AuthToken authToken = authTokenTable.Create(loginRequests[0]);
            response = new LoginResponse(user, authToken);
        }
        else {
            response = new LoginResponse("User not Found");
        }
        return response;
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        super.onPostExecute(loginResponse);
        //call observer
        observer.onLoginResponseReceived(loginResponse);
    }
}
