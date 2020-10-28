package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.net.DBDAO.PostingTable;

public class CreatePostingTaskFacade extends AsyncTask<CreatePostingRequest, Void, CreatePostingResponse> {
    private Observer observer;

    public interface Observer {
        public void onCreatePostingResponseReceived(CreatePostingResponse response);
    }

    public CreatePostingTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected CreatePostingResponse doInBackground(CreatePostingRequest... request) {
        //create posting
        PostingTable postingTable = new PostingTable();
        CreatePostingResponse response = postingTable.Create(request[0]);
        return response;
    }
}
