package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;
import com.example.roommatefinder.net.DBDAO.UserTable;

public class DeleteUserTaskFacade extends AsyncTask<DeleteUserRequest, Void, DeleteUserResponse> {

    private Observer observer;

    public DeleteUserTaskFacade(Observer observer) {
        this.observer = observer;
    }

    public interface Observer {
        public void onDeletedUser(DeleteUserResponse response);
    }

    @Override
    protected DeleteUserResponse doInBackground(DeleteUserRequest... deleteUserRequests) {
        UserTable userTable = new UserTable();
        return userTable.Delete(deleteUserRequests[0]);
    }

    @Override
    protected void onPostExecute(DeleteUserResponse deleteUserResponse) {
        super.onPostExecute(deleteUserResponse);
        observer.onDeletedUser(deleteUserResponse);
    }
}
