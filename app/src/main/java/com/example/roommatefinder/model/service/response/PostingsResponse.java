package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Posting;

import java.util.List;

public class PostingsResponse extends Response {
    private List<Posting> postings;

    public PostingsResponse(String message){
        super(false, message);
    }

    public PostingsResponse(List<Posting> postings){
        super(false, null);
        this.postings = postings;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
