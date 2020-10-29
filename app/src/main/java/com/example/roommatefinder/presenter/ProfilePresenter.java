package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.ProfileService;
import com.example.roommatefinder.model.service.RatingsService;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.asynctasks.ChangeUserInfoTaskFacade;

import java.io.IOException;

public class ProfilePresenter implements ChangeUserInfoTaskFacade.Observer {
    private final ProfilePresenter.View view;

    @Override
    public void onChangeUserResult(ChangeUserResponse response) {
        view.onUserChangeRequested(response);
    }

    public interface View {
        void onUserChangeRequested(ChangeUserResponse response);
    }

    public void changeUserInfo(ChangeUserRequest request) {
        getProfileService().changeProfileInfo(request, this);
    }

    public ProfilePresenter(ProfilePresenter.View view){
        this.view = view;
    }


    public ProfileService getProfileService() {
        return new ProfileService();
    }
}
