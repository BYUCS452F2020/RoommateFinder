package com.example.roommatefinder.view;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.presenter.LocationPresenter;
import com.example.roommatefinder.presenter.PreferencePresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ExpandedPostActivity extends AppCompatActivity implements LocationPresenter.View, PreferencePresenter.View {

    //private static final String SELECTED_USER_KEY = "SelectedUserKey";
    private static final String SELECTED_POST_KEY = "SelectedPostKey";

    private User user;
    private Posting posting;
    private Location location;
    private Preference preference;

    private TextView userName;
    private TextView postContent;
    private TextView vacancy;
    private TextView locationContent;
    private TextView preferencesContent;

    LocationPresenter locationPresenter;
    PreferencePresenter preferencePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_post);

        //user = (User) getIntent().getSerializableExtra(SELECTED_USER_KEY);
        posting = (Posting) getIntent().getSerializableExtra(SELECTED_POST_KEY);

        userName = (TextView) findViewById(R.id.expanded_post_username);
        userName.setText(posting.getEmail());
        //NOTE: I'm using the email of the post in place of the name of the user who made the post.

        postContent = (TextView) findViewById(R.id.expanded_post_content);
        postContent.setText(posting.getPostContent());

        vacancy = (TextView) findViewById(R.id.expanded_post_vacancy);
        vacancy.setText("" + posting.getVacancyNumber());

        try {
            locationPresenter = new LocationPresenter(ExpandedPostActivity.this);
            locationPresenter.getLocation(new LocationRequest(posting.getEmail()));

            preferencePresenter = new PreferencePresenter(ExpandedPostActivity.this);
            preferencePresenter.getPreference(new PreferenceRequest(posting.getEmail()));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void onLocationResult(LocationResponse response) {
        locationContent = (TextView) findViewById(R.id.expanded_post_location_content);
        location = response.getLocation();
        String content = "Street: " + location.getStreetName() + "\n" +
                "City: " + location.getCity() + "\n" +
                "State: " + location.getState() + "\n" +
                "Country: " + location.getCountry() + "\n" +
                "Building: " + location.getBuildingNumber() + "\n" +
                "Room Number: " + location.getRoomNumber() + "\n";

        locationContent.setText(content);
    }

    @Override
    public void onPreferenceResult(PreferenceResponse response) {
        preferencesContent = (TextView) findViewById(R.id.expanded_post_preferences_content);
        preference = response.getPreference();
        String content = "Price: $" + preference.getPrice() + "\n" +
                "Contract Length: " + preference.getLengthOfContract() + "\n" +
                "Contract Type: " + preference.getType() + "\n" +
                "Cleanliness Level: " + preference.getCleanlinessLevel() + "\n" +
                "Social Level: " + preference.getSocialLevel() + "\n";

        preferencesContent.setText(content);
    }

    @Override
    public void onUpdatePreferenceResult(CreatePreferenceResponse response) {
        //...Not going to be used in this situation.
    }
}