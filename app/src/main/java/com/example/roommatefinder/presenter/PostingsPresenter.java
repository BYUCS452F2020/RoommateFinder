package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.PostingsService;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.net.asynctasks.PostingsTaskFacade;

import java.io.IOException;

public class PostingsPresenter implements PostingsTaskFacade.Observer {

    private final View view;

    @Override
    public void onPostingsResponseReceived(PostingsResponse response) {
        view.onPostingsResult(response);
    }

    public interface View {
        public void onPostingsResult(PostingsResponse response);
    }

    public PostingsPresenter(View view) {
        this.view = view;
    }

    public void getPostings(PostingsRequest request) throws IOException {
        PostingsService postingsService = getPostingsService();
        postingsService.getPostings(request, this);
    }

    public PostingsService getPostingsService(){
        return new PostingsService();
    }
}
