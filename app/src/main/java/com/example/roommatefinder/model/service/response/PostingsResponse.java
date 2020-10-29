package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Posting;

import java.util.List;

public class PostingsResponse extends Response {
    private List<Posting> postings;
    Boolean hasMorePages;

    public PostingsResponse(String message){
        super(false, message);
    }

    public PostingsResponse(List<Posting> postings, Boolean hasMorePages){
        super(false, null);
        this.postings = postings;
        this.hasMorePages = hasMorePages;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public Boolean getHasMorePages() {
        return hasMorePages;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
