package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Posting;

public class CreatePostingResponse extends Response {
    private Posting posting;

    public CreatePostingResponse(String message) {
        super(false, message);
    }

    public CreatePostingResponse(Posting posting) {
        super(true, null);
        this.posting = posting;
    }

    public Posting getPosting() {
        return posting;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
