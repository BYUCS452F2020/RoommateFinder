package com.example.roommatefinder.net.DBDAO;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.net.SQLAccess;
import java.sql.SQLException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.gson.Gson;

public class PreferenceTable {

   // private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    private AmazonDynamoDB client = AmazonDynamoDBClient.builder().withRegion("us-east-2").build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table preferenceTable = dynamoDB.getTable("RoommateFinderPreference");

    public CreatePreferenceResponse Create(CreatePreferenceRequest request) {

        //Old SQL code.
        /*
        try {
            return SQLAccess.createPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new CreatePreferenceResponse(false, e.getMessage());
        }
         */

        Gson gson = new Gson();
        String preferenceJSON = gson.toJson(request.getPreference());
        String email = request.getPreference().getEmail();

        CreatePreferenceResponse response = null;

        try {
            //System.out.println("Adding a new item...");
            PutItemOutcome outcome = preferenceTable.putItem(new Item()
                    .withPrimaryKey("Email", email).
                            withString("preference", preferenceJSON));
            response = new CreatePreferenceResponse(true);
        }
        catch (Exception e) {
            System.err.println("Unable to add item: ");
            System.err.println(e.getMessage());
            response = new CreatePreferenceResponse(false);
        }

        return response;
    }

    public CreatePreferenceResponse Update(CreatePreferenceRequest request) {
        /*
        try {
           return SQLAccess.createPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */

        Gson gson = new Gson();
        String preferenceJSON = gson.toJson(request.getPreference());
        String email = request.getPreference().getEmail();
        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("Email", email)
                .withUpdateExpression("set preference = :p")
                .withValueMap(new ValueMap().withString(":p", preferenceJSON));

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = preferenceTable.updateItem(spec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Unable to update item: " + request.getPreference());
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Boolean Delete(PreferenceRequest request){
        //Old SQL code
        /*
        try{
            return SQLAccess.deletePreference(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
         */

        String email = request.getEmail();
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey("Email", email);

        Boolean preferenceDeleted = false;

        try {
            System.out.println("Attempting a delete...");
            preferenceTable.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
            preferenceDeleted = true;
        }
        catch (Exception e) {
            System.err.println("Unable to delete the follow.");
            System.err.println(e.getMessage());
        }

        return preferenceDeleted;
    }

    public PreferenceResponse Query(PreferenceRequest request) {
        //Old Code
        /*
        try {
            return SQLAccess.queryPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new PreferenceResponse(false, e.getMessage());
        }
         */

        String email = request.getEmail();

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email", email);

        PreferenceResponse response = null;
        try {
            System.out.println("Attempting to read the item...");
            Item outcome = preferenceTable.getItem(spec);
            String preferenceJSON = outcome.getString("preference");
            Gson gson = new Gson();
            Preference preference = gson.fromJson(preferenceJSON, Preference.class);

            System.out.println("Item read!");
            response = new PreferenceResponse(preference, true);
        }
        catch (Exception e) {
            System.err.println("Unable to read item.");
            System.err.println(e.getMessage());
            response = new PreferenceResponse(null, false);
        }

        return response;
    }
}
