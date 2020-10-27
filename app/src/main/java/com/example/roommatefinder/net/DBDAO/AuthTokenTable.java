package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.Utils.RandomAuthTokenGenerator;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class AuthTokenTable {
    public AuthToken Create(LoginRequest request) {
        //create authToken
        boolean success = false;
        String generatedToken = null;
        try {
            generatedToken = new RandomAuthTokenGenerator().generateAuthToken();
            success = SQLAccess.addEntryToAuthTokenTable(request, generatedToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (success) {
            return new AuthToken(generatedToken);
        }
        else {
            return null;
        }
    }

    public Boolean Update(LoginRequest request) {
        //Probably won't use this
       return true;
    }

    public Boolean Delete(LogoutRequest request) {
        //Use this when they Logout
        return true;
    }

    public AuthToken Query(LogoutRequest request) {
        return new AuthToken("Token");
    }
}
