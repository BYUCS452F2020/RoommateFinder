package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.LogoutResponse;

public class AuthTokenTable {
    public AuthToken Create(LoginRequest request) {
        //create authToken
        return new AuthToken("Token");
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
