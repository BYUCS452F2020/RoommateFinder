package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.Utils.RandomAuthTokenGenerator;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.model.service.response.LogoutResponse;
import com.example.roommatefinder.model.service.response.UpdateAuthTokenResponse;
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

    public UpdateAuthTokenResponse Update(UpdateAuthTokenRequest request) {
        //Probably won't use this
        String generatedToken = null;
        UpdateAuthTokenResponse response = null;
        try {
            generatedToken = new RandomAuthTokenGenerator().generateAuthToken();
            request.setNewAuthToken(generatedToken);
            response = SQLAccess.updateAuthToken(request);
            return response;
        } catch (SQLException e) {
            e.printStackTrace();
            return new UpdateAuthTokenResponse(false, e.getMessage());
        }
    }

    public LogoutResponse Delete(LogoutRequest request) {
        //Use this when they Logout
        return true;
    }

    public AuthToken Query(LogoutRequest request) {
        return new AuthToken("Token");
    }
}
