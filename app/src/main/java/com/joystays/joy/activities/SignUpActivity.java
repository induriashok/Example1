package com.joystays.joy.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.SignUpContract;
import com.joystays.joy.mvp.presenter.activity.RegisterActivityImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends BaseAbstractActivity<RegisterActivityImpl> implements SignUpContract.IView, APIResponseCallback {
    private LinearLayout linear_register;
    private EditText et_user_name, et_email, et_number, et_password,et_preferal_code;
    private APIResponseCallback apiResponseCallback;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ImageView password_show, passwordhide;


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
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_sign_up, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        apiResponseCallback = this;

        linear_register = findViewById(R.id.linear_register);


        et_user_name = findViewById(R.id.et_user_name);
        et_email = findViewById(R.id.et_email);
        et_number = findViewById(R.id.et_number);
        et_password = findViewById(R.id.et_password);
        password_show = findViewById(R.id.password_show);
        passwordhide = findViewById(R.id.passwordhide);
        et_preferal_code = findViewById(R.id.et_preferal_code);

        linear_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile_num =et_number.getText().toString();
                String firstLetter = mobile_num.substring(0, 1);


                if (et_user_name.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
                } else if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (!et_email.getText().toString().matches(emailPattern) || !emailValidation(et_email.getText().toString())) {
                    Toast.makeText(context, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (et_number.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                } else if (et_number.getText().toString().length() < 10) {
                    Toast.makeText(context, "Mobile number must be 10 digits ", Toast.LENGTH_SHORT).show();

                } else if ((firstLetter.equalsIgnoreCase("0")
                        ||firstLetter.equalsIgnoreCase("1")
                        ||firstLetter.equalsIgnoreCase("2"))
                        ||firstLetter.equalsIgnoreCase("3")||
                        firstLetter.equalsIgnoreCase("4")
                        ||firstLetter.equalsIgnoreCase("5")) {
                    Toast.makeText(getApplicationContext(), "Please enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (et_password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (et_password.getText().toString().trim().length() < 6) {
                    Toast.makeText(context, "Password must be minimum of 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    callRegisterws();
                }

            }
        });


        password_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setSelection(et_password.getText().toString().length());
                //et_password.setSelection(et_password.length());

                password_show.setVisibility(View.GONE);
                passwordhide.setVisibility(View.VISIBLE);

                et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());



            }
        });

        passwordhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setSelection(et_password.getText().toString().length());
                // et_password.setSelection(et_password.length());

                password_show.setVisibility(View.VISIBLE);
                passwordhide.setVisibility(View.GONE);
                et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());


            }
        });


    }

    private boolean emailValidation(String emailAddress) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private void callRegisterws() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", et_user_name.getText().toString().trim());
        requestBody.put("email_id", et_email.getText().toString().trim());
        requestBody.put("mobile", et_number.getText().toString().trim());
        requestBody.put("password", et_password.getText().toString().trim());
        requestBody.put("reffered_code", et_preferal_code.getText().toString().trim());
        requestBody.put("otp_confirmed", "No");
        presenter.userRegister(SignUpActivity.this, apiResponseCallback, requestBody);

    }

    @Override
    public void setPresenter() {
        presenter = new RegisterActivityImpl(this, this);
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
                   // Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Successfully sent OTP to your registered mobile number", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, OtpActivity.class);
                    intent.putExtra("name", et_user_name.getText().toString());
                    intent.putExtra("email_id",et_email.getText().toString());
                    intent.putExtra("mobile", et_number.getText().toString().trim());
                    intent.putExtra("password", et_password.getText().toString().trim());
                    intent.putExtra("refferal_code", et_preferal_code.getText().toString().trim());
                    intent.putExtra("otp", jsonObject.getString("response"));
                    intent.putExtra("tag", "signUp");
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                    context.startActivity(intent, options.toBundle());
                    //  finish();
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

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(SignUpActivity.this,WelcomeActivity.class);
        startActivity(i);
        finish();

    }
}
