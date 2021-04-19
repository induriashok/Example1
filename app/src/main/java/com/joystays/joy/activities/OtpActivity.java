package com.joystays.joy.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.OTPVerificationContract;
import com.joystays.joy.mvp.presenter.activity.OTPVerifivationImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.utils.EditTextOtp;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpActivity extends BaseAbstractActivity<OTPVerifivationImpl> implements OTPVerificationContract.IView, APIResponseCallback, View.OnClickListener {
    private ImageView back_arrow;
    private LinearLayout linear_verify, tv_resnedotp;
    private APIResponseCallback apiResponseCallback;
    private EditTextOtp editTextOtp;
    CountDownTimer cTimer = null;
    private TextView tvTimer;
    private String resend_active = "false";
    private String name, email_id, mobile, password, otp, tag,refferal_code;
    private String user_id;


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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_verify:
                String checkotp = editTextOtp.getOtp();

                if (checkotp.isEmpty()) {
                    Toast.makeText(this, "Please Enter OTP ", Toast.LENGTH_SHORT).show();

                } else if (checkotp.equals(otp)) {

                    if (tag.equalsIgnoreCase("signUp")) {
                        callRegisterWs();
                    } else {
                        Intent intent = new Intent(OtpActivity.this, ResetPasswordActivity.class);
                        intent.putExtra("user_id", user_id);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                        context.startActivity(intent, options.toBundle());
                        finish();
                    }
                } else {
                    editTextOtp.setOtp("");
                    Toast.makeText(this, "InValid OTP ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_arrow:
                onBackPressed();
                break;
            case R.id.tv_resnedotp:
                if (resend_active.equalsIgnoreCase("true")) {

                    if (tag.equalsIgnoreCase("signUp")) {
                        callResendOtpWS();
                    } else {
                        callFogotpassWs();
                    }
                }
        }
    }

    private void callRegisterWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("email_id", email_id);
        requestBody.put("mobile", mobile);
        requestBody.put("password", password);
        requestBody.put("reffered_code", refferal_code);
        requestBody.put("otp_confirmed", "Yes");
        presenter.verifiyOTP(OtpActivity.this, apiResponseCallback, requestBody);
    }

    private void callResendOtpWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("email_id", email_id);
        requestBody.put("mobile", mobile);
        requestBody.put("password", password);
        requestBody.put("otp_confirmed", "No");
        presenter.verifiyOTP(OtpActivity.this, apiResponseCallback, requestBody);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        back_arrow.setOnClickListener(this);
        linear_verify.setOnClickListener(this);
        tv_resnedotp.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;

        Intent intent = getIntent();

        tag = intent.getStringExtra("tag");

        if (tag.equalsIgnoreCase("signUp")) {
            name = intent.getStringExtra("name");
            email_id = intent.getStringExtra("email_id");
            mobile = intent.getStringExtra("mobile");
            password = intent.getStringExtra("password");
            otp = intent.getStringExtra("otp");
            refferal_code = intent.getStringExtra("refferal_code");
           // Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();

        } else {

            mobile = intent.getStringExtra("mobile");
            callFogotpassWs();
        }
        linear_verify = findViewById(R.id.linear_verify);
        back_arrow = findViewById(R.id.back_arrow);
        tvTimer = findViewById(R.id.tvTimer);
        tv_resnedotp = findViewById(R.id.tv_resnedotp);
        editTextOtp = (EditTextOtp) findViewById(R.id.etOtp);

        // Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();
        cTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText(" in " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                resend_active = "true";
                tvTimer.setText("");
            }

        }.start();

    }

    private void callFogotpassWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile", mobile);
        presenter.forgot_pass(OtpActivity.this, apiResponseCallback, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_otp, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new OTPVerifivationImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.USER_SIGNUP == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    if (message.equalsIgnoreCase("User Registration Successful!")) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LoginActivity.class);

                        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                        context.startActivity(intent, options.toBundle());
                        finish();
                    } else {

                        otp = jsonObject.getString("response");
                       // Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (NetworkConstants.RequestCode.FORGOT_PASSWORD == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Toast.makeText(context, "OTP sent Succesfully", Toast.LENGTH_SHORT).show();

                    otp = jsonObject.getString("otp");

                    user_id = jsonObject.getJSONObject("response").getString("id");
                   // Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
