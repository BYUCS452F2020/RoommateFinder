package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.RegisterService;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;

import java.io.IOException;

public class RegisterPresenter {

    private final View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }


    public RegisterPresenter(View view){
        this.view = view;
    }

    public RegisterResponse register(RegisterRequest request) throws IOException {
        RegisterService registerService = getRegisterService();
        return registerService.register(request);
    }

    public RegisterService getRegisterService(){return new RegisterService();}
}
