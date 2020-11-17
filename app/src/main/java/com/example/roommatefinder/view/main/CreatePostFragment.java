package com.example.roommatefinder.view.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.presenter.CreatePostingPresenter;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePostFragment extends Fragment implements CreatePostingPresenter.View {

    private static final String LOG_TAG = "CreatePostActivity";

    private static final String ARG_USER_EMAIL = "UserEmail";

    private String userEmail;
    private EditText postContent;
    private EditText vacancy;
    private Button submitButton;

    private CreatePostingPresenter presenter;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    public static CreatePostFragment newInstance(String userEmail) {
        CreatePostFragment fragment = new CreatePostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_EMAIL, userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString(ARG_USER_EMAIL);
        }

        presenter = new CreatePostingPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        postContent = (EditText) view.findViewById(R.id.post_content);
        vacancy = (EditText) view.findViewById(R.id.vacancy_count);
        submitButton = (Button) view.findViewById(R.id.submit_post_button);
        submitButton.setEnabled(false);
        postContent.addTextChangedListener(textWatcher);
        vacancy.addTextChangedListener(textWatcher);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            Toast failureToast;

                String content = postContent.getText().toString();
                Integer vacancyNum = Integer.parseInt(vacancy.getText().toString());

                String randomString = RandomString.getAlphaNumericString(10);

                CreatePostingRequest request = new CreatePostingRequest(new Posting(randomString, userEmail, content, vacancyNum));

                try {
                    presenter.createPosting(request);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
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
            if (isValidPostContent() && isValidVacancy()) {
                submitButton.setEnabled(true);
            }
            else {
                submitButton.setEnabled(false);
            }
        }
    };

    private Boolean isValidPostContent() {
        return postContent.getText().toString().length() > 0 && postContent.getText().toString().length() <= 500;
    }

    private Boolean isValidVacancy() {
        return StringUtils.isNumericSpace(vacancy.getText().toString());
    }

//    private Boolean postFieldsFilled() {
//        if (!postContent.getText().toString().equals("")) {
//            return false;
//        }
//        if (!vacancy.getText().toString().equals("")) {
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public void onCreatePostingResult(CreatePostingResponse response) {
        if (response.isSuccess()) {
            Toast.makeText(getActivity(), "Post Created " + response.getPosting().getPostID(), Toast.LENGTH_LONG).show();
            //Not sure what we want to do here. Do we need to add the post to the postsRecylcerView;
        }
        else {
            Toast failureToast = Toast.makeText(getActivity(), "Please fill all of the fields before submitting.", Toast.LENGTH_LONG );
            failureToast.show();
        }
    }

    private static class RandomString {

        // function to generate a random string of length n
        static String getAlphaNumericString(int n)
        {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int)(AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            return sb.toString();
        }
    }
}