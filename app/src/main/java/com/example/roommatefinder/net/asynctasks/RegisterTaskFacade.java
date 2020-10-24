package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.DBDAO.AuthTokenTable;
import com.example.roommatefinder.net.DBDAO.UserTable;

public class RegisterTaskFacade extends AsyncTask<RegisterRequest, Void, RegisterResponse> {
    private Observer observer;

    public interface Observer {
        public void onRegisterResponseReceived(RegisterResponse response);
    }

    public RegisterTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected RegisterResponse doInBackground(RegisterRequest... registerRequests) {
        //register user
        UserTable userTable = new UserTable();
        RegisterResponse response = userTable.Create(registerRequests[0]);
        if (response.isSuccess()) {
            //login user
            AuthTokenTable authTokenTable = new AuthTokenTable();
            LoginResponse loginResponse = authTokenTable.Create(new LoginRequest(response.getUser().getEmail(), response.getUser().getPassword()));
            response.setAuthToken(loginResponse.getAuthToken());
        }
        return response;
    }

    @Override
    protected void onPostExecute(RegisterResponse registerResponse) {
        //call observer method
        super.onPostExecute(registerResponse);
        observer.onRegisterResponseReceived(registerResponse);
    }
}
