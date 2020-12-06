package com.example.roommatefinder.net.DBDAO;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.example.roommatefinder.Utils.RandomAuthTokenGenerator;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.SessionCache;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.GetAuthTokenRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;
import com.example.roommatefinder.model.service.response.GetAuthTokenResponse;
import com.example.roommatefinder.model.service.response.LogoutResponse;
import com.example.roommatefinder.model.service.response.UpdateAuthTokenResponse;
import com.example.roommatefinder.net.SQLAccess;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.sql.Time;

public class AuthTokenTable {
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table authTokenTable = dynamoDB.getTable("RoommateFinderAuthToken");

    public AuthToken Create(LoginRequest request) {
        //create authToken
//        boolean success = false;
//        String generatedToken = null;
//        try {
//            generatedToken = new RandomAuthTokenGenerator().generateAuthToken();
//            success = SQLAccess.addEntryToAuthTokenTable(request, generatedToken);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (success) {
//            return new AuthToken(generatedToken);
//        }
//        else {
//            return null;
//        }
        Gson gson = new Gson();
        String generatedToken = new RandomAuthTokenGenerator().generateAuthToken();
        String email = request.getEmail();
        AuthToken authToken = new AuthToken(generatedToken, email, new Time(System.currentTimeMillis()));
        String authTokenJson = gson.toJson(authToken);


        try {
            //System.out.println("Adding a new item...");
            PutItemOutcome outcome = authTokenTable.putItem(new Item()
                    .withPrimaryKey("Token", authTokenJson));

            return authToken;
        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + authToken);
            System.err.println(e.getMessage());
            return null;
        }

    }

    public UpdateAuthTokenResponse Update(UpdateAuthTokenRequest request) {
        //Probably won't use this
//        String generatedToken = null;
//        UpdateAuthTokenResponse response = null;
//        try {
//            generatedToken = new RandomAuthTokenGenerator().generateAuthToken();
//            request.setNewAuthToken(generatedToken);
//            response = SQLAccess.updateAuthToken(request);
//            return response;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new UpdateAuthTokenResponse(false, e.getMessage());
//        }
        Gson gson = new Gson();
        boolean success = false;
        String authTokenJson = gson.toJson(request.getOldAuthToken());
        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("Token", authTokenJson)
                .withUpdateExpression("set Token = :p")
                .withValueMap(new ValueMap().withString(":p", gson.toJson(request.getNewAuthToken())));

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = authTokenTable.updateItem(spec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
            success = true;
        }
        catch (Exception e) {
            System.err.println("Unable to update item: " + request.getNewAuthToken());
            System.err.println(e.getMessage());
        }
        return new UpdateAuthTokenResponse(success);
    }

    public LogoutResponse Delete(LogoutRequest request) {
        //Use this when they Logout
//        try {
//            return SQLAccess.deleteAuthToken(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new LogoutResponse(e.getMessage());
//        }
        Gson gson = new Gson();
        String tokenJson = gson.toJson(request.getToken());
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey("Token", tokenJson);

        Boolean tokenDeleted = false;

        try {
            System.out.println("Attempting a delete...");
            authTokenTable.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
            tokenDeleted = true;
        }
        catch (Exception e) {
            System.err.println("Unable to delete the follow.");
            System.err.println(e.getMessage());
        }

        return new LogoutResponse(tokenDeleted);
    }

    public GetAuthTokenResponse Query(GetAuthTokenRequest request) {
//        try {
//            return SQLAccess.queryAuthToken(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new GetAuthTokenResponse(e.getMessage());
//        }
        String email = request.getEmail();

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email", email);

        AuthToken response = null;
        try {
            System.out.println("Attempting to read the item...");
            Item outcome = authTokenTable.getItem(spec);
            String userJson = outcome.getString("Token");
            Gson gson = new Gson();
            AuthToken authToken = gson.fromJson(userJson, AuthToken.class);
            response = authToken;
            System.out.println("Item read!");
        }
        catch (Exception e) {
            System.err.println("Unable to read item.");
            System.err.println(e.getMessage());
            response = null;
        }

        return new GetAuthTokenResponse(response);

    }
}
