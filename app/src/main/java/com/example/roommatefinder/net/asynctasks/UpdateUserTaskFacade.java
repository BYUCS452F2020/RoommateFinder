package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.net.DBDAO.UserTable;
import com.example.roommatefinder.net.SQLAccess;

public class UpdateUserTaskFacade extends AsyncTask<ChangeUserRequest, Void, ChangeUserResponse> {
    private Observer observer;

    public UpdateUserTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        public void onUserUpdated(ChangeUserResponse response);
    }
    @Override
    protected ChangeUserResponse doInBackground(ChangeUserRequest... changeUserRequests) {
        UserTable userTable = new UserTable();
        return userTable.Update(changeUserRequests[0]);
    }

    @Override
    protected void onPostExecute(ChangeUserResponse response) {
        super.onPostExecute(response);
        observer.onUserUpdated(response);
    }
}
