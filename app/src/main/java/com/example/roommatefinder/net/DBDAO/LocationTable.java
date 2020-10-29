package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class LocationTable {
    public CreateLocationResponse Create(CreateLocationRequest request) {
        try{
            return SQLAccess.createLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new CreateLocationResponse(false, e.getMessage());
        }
        //return new Location("email", "USA", "UT", "Provo", "Land", 123, 12);
    }

    public Boolean Update(CreateLocationRequest request) {
        //might not have to use this
        return true;
    }

    public Boolean Delete(LocationRequest request) {
        return  true;
    }

    public LocationResponse Query(LocationRequest request) {
        try{
            return SQLAccess.queryLocation(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new LocationResponse("Failed to return location: " + e.getMessage());
        }
    }
}
