package com.joystays.joy.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.OwnerComplaintContract;
import com.joystays.joy.mvp.presenter.activity.OwnerComplaintImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerComplaintActivity extends BaseAbstractActivity<OwnerComplaintImpl> implements OwnerComplaintContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_send;
    private EditText et_complaint;
    private UserSession userSession;
    private String user_id, token, isApproved = "", hostel_id, owner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        presenter.start();
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        linear_send.setOnClickListener(this);
        back_arrow.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        userSession = new UserSession(context);
        et_complaint = findViewById(R.id.et_complaint);
        linear_send = findViewById(R.id.linear_send);
        back_arrow = findViewById(R.id.back_arrow);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        owner_id = userSession.getUserDetails().get("owner_id");
        isApproved = userSession.getUserDetails().get("approved");
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_owner_complaint, null);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_send:
                if (et_complaint.getText().toString().length() < 2) {
                    Toast.makeText(context, "Write complaint", Toast.LENGTH_SHORT).show();
                } else {
                    callComplaintWs();
                }
                break;
            case R.id.back_arrow:
                onBackPressed();
                break;

        }
    }

    private void callComplaintWs() {

        if (isApproved.equalsIgnoreCase("yes")) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("user_id", user_id);
            requestBody.put("token", token);
            requestBody.put("hostel_id", hostel_id);
            requestBody.put("owner_id", owner_id);
            requestBody.put("owner_id", owner_id);
            requestBody.put("message", et_complaint.getText().toString());
            presenter.complaint(OwnerComplaintActivity.this, this, requestBody);
        } else {
            Toast.makeText(context, "You need to book in order to give Complaints", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter() {
        presenter = new OwnerComplaintImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.USER_COMPLAINT == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {
                    Toast.makeText(context, "Complaint submitted successfully", Toast.LENGTH_SHORT).show();
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
