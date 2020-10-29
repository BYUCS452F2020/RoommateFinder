package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class PostingTable {

    public CreatePostingResponse Create(CreatePostingRequest request) {

        try {
            return SQLAccess.insertPostingIntoPostingTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new CreatePostingResponse(e.getMessage());
        }
    }

    public CreatePostingResponse Update(CreatePostingRequest request) {

        try {
            return SQLAccess.insertPostingIntoPostingTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new CreatePostingResponse(e.getMessage());
        }

    }

//    public Boolean Delete(Posting posting) {
//        //Don't really need this one.
//        return true;
//    }

    public PostingsResponse Query(PostingsRequest request) {

         try {
             return SQLAccess.queryAllPostings(request);
         } catch (SQLException e) {
             e.printStackTrace();
             return new PostingsResponse(e.getMessage());
         }
    }
}
