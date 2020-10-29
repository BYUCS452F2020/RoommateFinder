package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.Posting;

public class PostingsRequest {

    private final int limit;
    private final Posting lastPosting;

    public PostingsRequest(int limit, Posting lastPosting) {
        this.limit = limit;
        this.lastPosting = lastPosting;
    }

    public int getLimit() {
        return limit;
    }

    public Posting getLastPosting() {
        return lastPosting;
    }
}
