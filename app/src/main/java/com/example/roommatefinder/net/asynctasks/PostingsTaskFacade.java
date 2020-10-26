package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.DBDAO.PostingTable;

import java.util.List;

public class PostingsTaskFacade extends AsyncTask<PostingsRequest, Void, PostingsResponse> {

    private Observer observer;

    public interface Observer {
        public void onPostingsResponseReceived(PostingsResponse response);
    }

    @Override
    protected PostingsResponse doInBackground(PostingsRequest ... requests) {
        PostingTable postingTable = new PostingTable();
        List<Posting> postings = postingTable.Query(requests[0]);
        PostingsResponse response = null;
        if (postings != null) {
            response = new PostingsResponse(postings);
        }
        else {
            response = new PostingsResponse("Failed to retrieve postings!");
        }

        return response;
    }

    @Override
    protected void onPostExecute(PostingsResponse postingsResponse){
        super.onPostExecute(postingsResponse);

        observer.onPostingsResponseReceived(postingsResponse);
    }
}
