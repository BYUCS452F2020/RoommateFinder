package com.example.roommatefinder.net.DBDAO;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.net.SQLAccess;
import com.google.gson.Gson;

import java.sql.SQLException;

public class PostingTable {

    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table postingTable = dynamoDB.getTable("RoommateFinderPosting");

    public CreatePostingResponse Create(CreatePostingRequest request) {

        /*try {
            return SQLAccess.insertPostingIntoPostingTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new CreatePostingResponse(e.getMessage());
        }*/

        Gson gson = new Gson();
        String postingJSON = gson.toJson(request.getPosting());
        String postingID = request.getPosting().getPostID();

        CreatePostingResponse createPostingResponse = null;

        try{
            PutItemOutcome outcome = postingTable.putItem(new Item().withPrimaryKey("PostingID", postingID)
                    .withString("posting", postingJSON));

            createPostingResponse = new CreatePostingResponse(request.getPosting());
        }
        catch (Exception e){
            System.err.println("Unable to add item: ");
            System.err.println(e.getMessage());
            createPostingResponse = new CreatePostingResponse("Unable to add item");
        }

        return createPostingResponse;
    }

    public CreatePostingResponse Update(CreatePostingRequest request) {

        /*try {
            return SQLAccess.insertPostingIntoPostingTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new CreatePostingResponse(e.getMessage());
        }*/

        Gson gson = new Gson();
        String postingJSON = gson.toJson(request.getPosting());
        String postingID = request.getPosting().getPostID();
        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("PostingID", postingID)
                .withUpdateExpression("set posting = :p")
                .withValueMap(new ValueMap().withString(":p", postingJSON));

        try{
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = postingTable.updateItem(spec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
        }
        catch (Exception e){
            System.err.println("Unable to update item: " + request.getPosting());
            System.err.println(e.getMessage());
        }

        return null;
    }

//    public Boolean Delete(Posting posting) {
//        //Don't really need this one.
//        return true;
//    }

    public PostingsResponse Query(PostingsRequest request) {

         /*try {
             return SQLAccess.queryAllPostings(request);
         } catch (SQLException e) {
             e.printStackTrace();
             return new PostingsResponse(e.getMessage());
         }*/

        String postingID = request.getLastPosting().getPostID();

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("PostID", postingID);

        PostingsResponse postingsResponse = null;

        try{
            System.out.println("Attempting to read the item...");
            Item outcome = postingTable.getItem(spec);
            String postingJSON = outcome.getString("posting");
            Gson gson = new Gson();
            Posting posting = gson.fromJson(postingJSON, Posting.class);

            System.out.println("Item read!");

        }
        catch(Exception e){
            System.err.println("Unable to read item.");
            System.err.println(e.getMessage());
        }

        return null;
    }
}