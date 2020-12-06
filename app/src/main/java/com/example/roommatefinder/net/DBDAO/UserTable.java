package com.example.roommatefinder.net.DBDAO;

import android.annotation.SuppressLint;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
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
import com.example.roommatefinder.Utils.PasswordHasher;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.SessionCache;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.SQLAccess;
import com.google.gson.Gson;

import java.sql.SQLException;

public class UserTable {
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table userTable = dynamoDB.getTable("RoommateFinderUser");
    
    public User Create(RegisterRequest request) {
        //Needs to check for AuthToken
//        try {
//            @SuppressLint({"NewApi", "LocalSuppress"}) String newPassword = new PasswordHasher(request.getPassword()).getHashPassword();
//            request.setPassword(newPassword);
//            return SQLAccess.addEntryToUserTable(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        Gson gson = new Gson();
        User user = new User(request.getFirstName(), request.getLastName(), request.getGender(), request.getAge(), request.getEmail(),
                request.getPassword(), request.getPhoneNumber());
        String userJSON = gson.toJson(user);
        String email = request.getEmail();


        try {
            //System.out.println("Adding a new item...");
            PutItemOutcome outcome = userTable.putItem(new Item()
                    .withPrimaryKey("Email", email)
                    .withString("user", userJSON));

           return user;
        }
        catch (Exception e) {
            System.err.println("Unable to add item: ");
            System.err.println(e.getMessage());
            return null;
        }

    }

    public ChangeUserResponse Update(ChangeUserRequest request) {
        //needs to check for authToken
//        try {
//            return SQLAccess.updateUser(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ChangeUserResponse(false, e.getMessage());
//        }
        Gson gson = new Gson();
        User user = SessionCache.getInstance().getUser();
        user.setAge(request.getAge());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        String userJson = gson.toJson(user);
        String email = request.getEmail();
        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("Email", email)
                .withUpdateExpression("set user = :p")
                .withValueMap(new ValueMap().withString(":p", userJson));

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = userTable.updateItem(spec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Unable to update item: " + request.getEmail());
            System.err.println(e.getMessage());
        }

        return null;
    }

    public DeleteUserResponse Delete(DeleteUserRequest request) {
//        try {
//            return SQLAccess.deleteUser(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new DeleteUserResponse(false, e.getMessage());
//        }
        String email = request.getEmail();
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey("Email", email);

        Boolean userDeleted = false;

        try {
            System.out.println("Attempting a delete...");
            userTable.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
            userDeleted = true;
        }
        catch (Exception e) {
            System.err.println("Unable to delete the follow.");
            System.err.println(e.getMessage());
        }

        return new DeleteUserResponse(userDeleted);
    }
    
    public User Query(LoginRequest request) {
//        try {
//            return SQLAccess.queryUser(request);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
        String email = request.getEmail();

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email", email);

        User response = null;
        try {
            System.out.println("Attempting to read the item...");
            Item outcome = userTable.getItem(spec);
            String userJson = outcome.getString("user");
            Gson gson = new Gson();
            User user = gson.fromJson(userJson, User.class);
            response = user;
            System.out.println("Item read!");
        }
        catch (Exception e) {
            System.err.println("Unable to read item.");
            System.err.println(e.getMessage());
            response = null;
        }

        return response;
    }

}
