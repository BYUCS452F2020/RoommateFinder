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
import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.net.SQLAccess;
import com.google.gson.Gson;

import java.sql.SQLException;

public class LocationTable {

    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table locationTable = dynamoDB.getTable("RoommateFinderLocation");

    public CreateLocationResponse Create(CreateLocationRequest request) {
        /*try{
            return SQLAccess.createLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new CreateLocationResponse(false, e.getMessage());
        }*/

        Gson gson = new Gson();
        String locationJSON = gson.toJson(request.getLocation());
        String email = request.getLocation().getUsername();

        CreateLocationResponse createLocationResponse = null;

        try{
            PutItemOutcome outcome = locationTable.putItem(new Item().withPrimaryKey("Email", email)
                    .withString("location", locationJSON));
            createLocationResponse = new CreateLocationResponse(true);
        }
        catch(Exception e){
            System.err.println("Unable to add item: ");
            System.err.println(e.getMessage());
            createLocationResponse = new CreateLocationResponse(false);
        }

        return createLocationResponse;
        //return new Location("email", "USA", "UT", "Provo", "Land", 123, 12);
    }

    public CreateLocationResponse Update(CreateLocationRequest request) {
        /*try{
            return SQLAccess.createLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new CreateLocationResponse(false, e.getMessage());
        }*/

        Gson gson = new Gson();
        String locationJSON = gson.toJson(request.getLocation());
        String email = request.getLocation().getUsername();
        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("Email", email)
                .withUpdateExpression("set location = :p").withValueMap(new ValueMap().withString(":p", locationJSON));

        CreateLocationResponse createLocationResponse = null;
        try{
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = locationTable.updateItem(spec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
            createLocationResponse = new CreateLocationResponse(true);
        }
        catch(Exception e){
            System.err.println("Unable to update item: " + request.getLocation());
            System.err.println(e.getMessage());
            createLocationResponse = new CreateLocationResponse(false);
        }

        return createLocationResponse;

    }

    public Boolean Delete(LocationRequest request) {
        try{
            return SQLAccess.deleteLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public LocationResponse Query(LocationRequest request) {
        /*try{
            return SQLAccess.queryLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new LocationResponse("Failed to return location: " + e.getMessage());
        }*/

        String email = request.getUsername();

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email", email);

        LocationResponse locationResponse = null;

        try{
            System.out.println("Attempting to read the item...");
            Item outcome = locationTable.getItem(spec);
            String locationJSON = outcome.getString("location");
            Gson gson = new Gson();
            Location location = gson.fromJson(locationJSON, Location.class);

            System.out.println("Item read!");
            locationResponse = new LocationResponse(location);
        }
        catch (Exception e){
            System.err.println("Unable to read item.");
            System.err.println(e.getMessage());
            locationResponse = new LocationResponse("Unable to read item.");
        }

        return locationResponse;
    }
}