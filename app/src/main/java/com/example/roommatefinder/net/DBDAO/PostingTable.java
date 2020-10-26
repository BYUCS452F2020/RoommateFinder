package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.PostingsRequest;

import java.util.ArrayList;

public class PostingTable {

    public Posting Create(CreatePostingRequest request) {
        Posting posting = request.getPosting();

        //TODO: implement the code that adds posting to the database table.

        return new Posting(posting.getUser(), posting.getCountry(), posting.getState(), posting.getCity(),
                posting.getStreet(), posting.getBuildNum(),posting.getRoomNum());
    }

    public Boolean Update(CreatePostingRequest request) {

        //TODO: implement the code that updates the current posting for a user to a new one. (We can look into this one later.)

        return true;
    }

    public Boolean Delete(Posting posting) {

        //TODO: implement the code that deletes a posting from the posting table.

        return true;
    }

    public ArrayList<Posting> Query(PostingsRequest request) {
         ArrayList<Posting> postings = new ArrayList<>();

         //TODO: Return all of the postings for preview in main activity.

         return postings;
    }
}
