package com.example.roommatefinder.net.DBDAO;

import android.annotation.SuppressLint;

import com.example.roommatefinder.Utils.PasswordHasher;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class UserTable {
    
    public User Create(RegisterRequest request) {
        //Needs to check for AuthToken
        try {
            @SuppressLint({"NewApi", "LocalSuppress"}) String newPassword = new PasswordHasher(request.getPassword()).getHashPassword();
            request.setPassword(newPassword);
           return SQLAccess.addEntryToUserTable(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChangeUserResponse Update(ChangeUserRequest request) {
        //needs to check for authToken
        try {
            return SQLAccess.updateUser(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ChangeUserResponse(false, e.getMessage());
        }
    }

    public DeleteUserResponse Delete(DeleteUserRequest request) {
        try {
            return SQLAccess.deleteUser(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DeleteUserResponse(false, e.getMessage());
        }
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
