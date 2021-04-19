package com.joystays.joy.activities;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.UserRatingContract;
import com.joystays.joy.mvp.presenter.activity.UserRatingImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GiveRateUsActivity extends BaseAbstractActivity<UserRatingImpl> implements UserRatingContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_submit;
    private RatingBar rb_rating;
    private EditText et_feedback;
    private UserSession userSession;
    private String user_id, token, isApproved, hostel_id,rating_count;
    int re_rats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        back_arrow.setOnClickListener(this);
        linear_submit.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(context);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        isApproved = userSession.getUserDetails().get("approved");
        linear_submit = findViewById(R.id.linear_submit);
        back_arrow = findViewById(R.id.back_arrow);
        rb_rating = findViewById(R.id.rb_rating);
        et_feedback = findViewById(R.id.et_feedback);


       rb_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 rating_count = String.valueOf(rating);


            }
        });


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_give_rate_us, null);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow:
                onBackPressed();
                break;
            case R.id.linear_submit:
                if (rb_rating.getRating() == 0) {
                    Toast.makeText(context, "Please give  rating", Toast.LENGTH_SHORT).show();
                } else if (et_feedback.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Please write comment", Toast.LENGTH_SHORT).show();
                } else {
                    callSubmitRatingWs();

                }
                break;
        }
    }

    private void callSubmitRatingWs() {

        // int ratings = rb_rating.getRating();

        if (isApproved.equalsIgnoreCase("yes")) {

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("hostel_id", hostel_id);
            requestBody.put("ratings", rating_count);
            requestBody.put("reviews", et_feedback.getText().toString());
            presenter.userRating(GiveRateUsActivity.this, this, requestBody);
        } else {
            Toast.makeText(context, "You need to book in order to give Ratings", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenter() {
        presenter = new UserRatingImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                //   Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.SUBMIT_RATING == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Toast.makeText(context, "Thanks for the feedback", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
