package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;

public class UserTable implements DAOInterface<RegisterRequest, RegisterResponse, LoginRequest, LoginResponse> {
    
    @Override
    public RegisterResponse Create(RegisterRequest request) {
        return new RegisterResponse(new User(request.getFirstName(), request.getLastName(), request.getGender(), request.getAge()
        , request.getEmail(), request.getPassword(), request.getPhoneNumber()), null);
    }

    @Override
    public RegisterResponse Update(RegisterRequest request) {
        return null;
    }

    @Override
    public LoginResponse Delete(LoginRequest request) {
        return null;
    }

    @Override
    public LoginResponse Query(LoginRequest request) {
        return new LoginResponse(new User("Test", "User", 'm', 25, "testuser@gmail.com",
                "password", "111-222-3333"), null);
    }

}
