package com.joystays.joy.activities;

import android.content.Intent;
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
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ForgotPasswordActivityContract;
import com.joystays.joy.mvp.presenter.activity.ForgotPasswordActivityImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends BaseAbstractActivity<ForgotPasswordActivityImpl> implements ForgotPasswordActivityContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_next;
    private EditText et_number;
    APIResponseCallback apiResponseCallback;

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
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        back_arrow = findViewById(R.id.back_arrow);
        linear_next = findViewById(R.id.linear_next);
        et_number = findViewById(R.id.et_number);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linear_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_number.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                } else if (et_number.getText().toString().length() != 10) {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter Valid mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    forgetpaswordWBS();


                }
                // startActivity(  new Intent( ForgotPasswordActivity.this,OtpActivity.class ) );
            }
        });


    }

    private void forgetpaswordWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile", et_number.getText().toString());
        presenter.forgot_pass(ForgotPasswordActivity.this, apiResponseCallback, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new ForgotPasswordActivityImpl(this, this);

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

            } else if (NetworkConstants.RequestCode.FORGOT_PASSWORD == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {

                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("tag", "forget");
                    intent.putExtra("mobile", et_number.getText().toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

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
