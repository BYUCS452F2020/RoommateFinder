package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class UserTable {
    
    public User Create(RegisterRequest request) {
        try {
           return SQLAccess.addEntryToUserTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean Update(RegisterRequest request) {
        return true;
    }

    public Boolean Delete(LoginRequest request) {
        return true;
    }
    
    public User Query(LoginRequest request) {
        try {
            return SQLAccess.queryUser(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
