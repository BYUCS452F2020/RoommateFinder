package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roommatefinder.model.SessionCache;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.presenter.ProfilePresenter;

import org.apache.commons.lang3.StringUtils;

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.View {

    private EditText firstNameField;
    private EditText lastNameField;
    private EditText phoneNumberField;
    private EditText age;
    private Button saveButton;
    private SessionCache sessionCache = SessionCache.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firstNameField = findViewById(R.id.firstNameSettings);
        firstNameField.setText(sessionCache.getUser().getFirstName());
        lastNameField = findViewById(R.id.lastNameSettings);
        lastNameField.setText(sessionCache.getUser().getLastName());
        phoneNumberField = findViewById(R.id.phoneNumberSettings);
        phoneNumberField.setText(sessionCache.getUser().getPhoneNumber());
        age = findViewById(R.id.ageSettings);
        age.setText(Integer.toString(sessionCache.getUser().getAge()));
        saveButton = findViewById(R.id.saveChanges);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desiredFirstName = firstNameField.getText().toString();
                String desiredLastName = lastNameField.getText().toString();
                int desiredAge = Integer.parseInt(age.getText().toString());
                String desiredPhoneNumber = phoneNumberField.getText().toString();
                String currEmail = SessionCache.getInstance().getUser().getEmail();
                ChangeUserRequest request = new ChangeUserRequest(currEmail, desiredFirstName, desiredLastName, desiredPhoneNumber
                , desiredAge);
                ProfilePresenter presenter = new ProfilePresenter(ProfileActivity.this);
                presenter.changeUserInfo(request);
            }
        });
        firstNameField.addTextChangedListener(textWatcher);
        lastNameField.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);
        phoneNumberField.addTextChangedListener(textWatcher);
    }

    @Override
    public void onUserChangeRequested(ChangeUserResponse response) {
        if (response.isSuccess()) {
            SessionCache sessionCache = SessionCache.getInstance();
            User user = sessionCache.getUser();
            User modifiedUser = new User(firstNameField.getText().toString(), lastNameField.getText().toString(),
                    user.getGender(), Integer.parseInt(age.getText().toString()), user.getEmail(), user.getPassword(), phoneNumberField.getText().toString());
            SessionCache.getInstance().setUser(modifiedUser);
            Toast.makeText(this, "Saved Changes", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "There was an error in Saving Changes: " + response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (isAllValid()) {
                saveButton.setEnabled(true);
            }
            else {
                saveButton.setEnabled(false);
            }
        }
    };

    private boolean isAllValid() {
        boolean allValid = true;
        if (!isValidFirstName()) {
            Toast.makeText(this, "First Name must be between 1 and 30 characters", Toast.LENGTH_LONG).show();
            allValid = false;
        }
        if (!isValidLastName()) {
            Toast.makeText(this, "Last Name must be between 1 and 30 characters", Toast.LENGTH_LONG).show();
            allValid = false;
        }
        if (!isValidPhoneNumber()) {
            Toast.makeText(this, "Phone Number must be between 1 and 10 characters", Toast.LENGTH_LONG).show();
            allValid = false;
        }
        if (!isValidAge()) {
            Toast.makeText(this, "Age must be a number", Toast.LENGTH_LONG).show();
            allValid = false;
        }
        return allValid;
    }

    private boolean isValidFirstName() {
        int length = firstNameField.getText().toString().length();
        return length > 1 && length <= 30;
    }

    private boolean isValidLastName() {
        int length = lastNameField.getText().toString().length();
        return length > 1 && length <= 30;
    }

    private boolean isValidPhoneNumber() {
        int length = phoneNumberField.getText().toString().length();
        return length > 1 && length <= 10;
    }

    private boolean isValidAge() {
        return StringUtils.isNumericSpace(age.getText().toString());
    }
}