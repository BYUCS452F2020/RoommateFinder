package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.Posting;

public class CreatePostingRequest extends Request {
    private Posting posting;

    public CreatePostingRequest(Posting posting) {
        this.posting = posting;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }
}
