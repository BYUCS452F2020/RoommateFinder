package com.example.roommatefinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.SessionCache;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.presenter.CreatePreferencePresenter;
import com.example.roommatefinder.presenter.PreferencePresenter;
import com.example.roommatefinder.view.main.MainActivity;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class ChoosePreferenceActivity extends AppCompatActivity implements CreatePreferencePresenter.View, PreferencePresenter.View {
    private Button submitButton;
    private EditText timeToSleep;
    private EditText socialLevel;
    private EditText cleanliness;
    private EditText price;
    private EditText type;
    private EditText contract;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_preference);
        mode = getIntent().getIntExtra("mode", 0);
        //if 0 then no preference is made yet
        //else there is already one
        submitButton = findViewById(R.id.buttonChoose);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sleepTime = Integer.valueOf(timeToSleep.getText().toString());
                String email = SessionCache.getInstance().getUser().getEmail();
                int levelSocial = Integer.valueOf(socialLevel.getText().toString());
                int clean = Integer.valueOf(cleanliness.getText().toString());
                int pricePref = Integer.valueOf(price.getText().toString());
                String typePref = type.getText().toString().toLowerCase();
                String contractPref = contract.getText().toString();
                Preference preference = new Preference(email, sleepTime, levelSocial, clean, pricePref, typePref, contractPref);
                if (mode == 0) {
                    CreatePreferencePresenter preferencePresenter = new CreatePreferencePresenter(ChoosePreferenceActivity.this);
                    try {
                        preferencePresenter.createPreference(new CreatePreferenceRequest(preference));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mode == 1) {
                    PreferencePresenter preferencePresenter = new PreferencePresenter(ChoosePreferenceActivity.this);
                    preferencePresenter.updatePreference(new CreatePreferenceRequest(preference));
                }
            }
        });
        timeToSleep = findViewById(R.id.timeToSleepChoose);
        socialLevel = findViewById(R.id.socialLevelChoose);
        cleanliness = findViewById(R.id.cleanlinessChoose);
        price = findViewById(R.id.priceChoose);
        type = findViewById(R.id.typeChoose);
        contract = findViewById(R.id.contractChoose);
        timeToSleep.addTextChangedListener(textWatcher);
        socialLevel.addTextChangedListener(textWatcher);
        cleanliness.addTextChangedListener(textWatcher);
        price.addTextChangedListener(textWatcher);
        type.addTextChangedListener(textWatcher);
        contract.addTextChangedListener(textWatcher);
        if (mode == 1) {
            PreferencePresenter preferencePresenter = new PreferencePresenter(this);
            try {
                preferencePresenter.getPreference(new PreferenceRequest(SessionCache.getInstance().getUser().getEmail()));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                submitButton.setEnabled(true);
            }
            else {
                submitButton.setEnabled(false);
            }
        }
    };

    private boolean isAllValid() {
        return isValidCleanliness() && isValidContract() && isValidPrice()
                && isValidSocialLevel() && isValidTimeToSleep() && isValidType();
    }

    private boolean isValidTimeToSleep() {
        return StringUtils.isNumeric(timeToSleep.getText());
    }

    private boolean isValidSocialLevel() {
        return StringUtils.isNumeric(socialLevel.getText());
    }

    private boolean isValidCleanliness() {
        return StringUtils.isNumeric(cleanliness.getText());
    }

    private boolean isValidPrice() {
        return StringUtils.isNumeric(price.getText());
    }

    private boolean isValidType() {
        return type.getText().toString().toLowerCase().equals("m") || type.getText().toString().toLowerCase().equals("f") || type.getText().equals("");
    }

    private boolean isValidContract() {
        return contract.getText().length() < 10 && contract.getText().length() > 0;
    }

    @Override
    public void onCreatePreferenceResult(CreatePreferenceResponse response) {
        if (response.isSuccess()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPreferenceResult(PreferenceResponse response) {
        if (response.isSuccess()) {
            int clean = response.getPreference().getCleanlinessLevel();
            cleanliness.setText(Integer.toString(clean));
            int social = response.getPreference().getSocialLevel();
            socialLevel.setText(Integer.toString(social));
            int pricey = (int) response.getPreference().getPrice();
            price.setText(Integer.toString(pricey));
            int sleeptime = response.getPreference().getPreferredTimeToSleep();
            timeToSleep.setText(Integer.toString(sleeptime));
            String typey = response.getPreference().getType().toLowerCase();
            type.setText(typey);
            String contracty = response.getPreference().getLengthOfContract();
            contract.setText(contracty);
        }
        else {
            Toast.makeText(this, "Something went wrong retrieving the preference", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdatePreferenceResult(CreatePreferenceResponse response) {
        if (response.isSuccess()) {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Something went wrong updating the preference", Toast.LENGTH_SHORT).show();
        }
    }
}