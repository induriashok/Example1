package com.joystays.joy.activities;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.LoginActivityContract;
import com.joystays.joy.mvp.presenter.activity.LoginActivitylmpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.LoginPojo;
import com.joystays.joy.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseAbstractActivity<LoginActivitylmpl> implements LoginActivityContract.IView, View.OnClickListener, APIResponseCallback {
    private LinearLayout linear_login;
    private TextView tv_forgotpassword;
    private APIResponseCallback apiResponseCallback;
    private EditText et_number, et_password;
    private UserSession userSession;
    String password,laguages="Telugu,English";
    private static final int GRANT_LOC_ACCESS = 800;


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
        view = getLayoutInflater().inflate(R.layout.activity_login, null);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(LoginActivity.this,WelcomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        et_number = findViewById(R.id.et_number);
        et_password = findViewById(R.id.et_password);
        tv_forgotpassword = findViewById(R.id.tv_forgotpassword);
        linear_login = findViewById(R.id.linear_login);
        userSession = new UserSession(context);


      /*  if (ActivityCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    GRANT_LOC_ACCESS);
        }else{

        }

*/
        tv_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForgotPasswordActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                context.startActivity(intent, options.toBundle());
            }
        });
        linear_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = et_password.getText().toString().trim();

                callLoginWs();
            }
        });
    }


    private void callLoginWs() {
        if (et_number.getText().toString().isEmpty()) {
            Toast.makeText(context, "Enter mobile number", Toast.LENGTH_SHORT).show();
        } else if (et_number.getText().toString().length() < 10) {
            Toast.makeText(context, "Mobile number must be 10 characters", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().isEmpty()) {
            Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().length() < 6) {
            Toast.makeText(context, "password must be minimum 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("mobile", et_number.getText().toString().trim());
            requestBody.put("password", et_password.getText().toString().trim());
            presenter.userLogin(LoginActivity.this, apiResponseCallback, requestBody);
        }
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new LoginActivitylmpl(this, this);
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
            } else if (NetworkConstants.RequestCode.USER_LOGIN == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                  //  Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    LoginPojo loginPojo = new Gson().fromJson(responseString, LoginPojo.class);
                    String bed_confromred = loginPojo.getResponse().getBed_confirmed();




                    userSession.StoreUserDetails(loginPojo.getResponse().getId(),
                            loginPojo.getResponse().getName(),
                            loginPojo.getResponse().getMobile(),
                            loginPojo.getResponse().getEmail_id(),
                            loginPojo.getResponse().getBed_confirmed(),
                            loginPojo.getToken(),loginPojo.getResponse().getProfile_pic(),password);

                    if (bed_confromred.equalsIgnoreCase("no")) {

                    } else {

                        if(loginPojo.getResponse().getBooking().equalsIgnoreCase("done")) {
                            if( loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages()!=null){
                                laguages = loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages();

                            }
                            //if (loginPojo.getResponse().getBooking_details().getStatus().equalsIgnoreCase("approved")) {
                                        userSession.storeHostel_details("yes",
                                                loginPojo.getResponse().getBooking_details().getHostel_id(),
                                        loginPojo.getResponse().getBooking_details().getOwner_id(),
                                        loginPojo.getResponse().getBooking_details().getFloor(),
                                        loginPojo.getResponse().getBooking_details().getRoom_id(),
                                        loginPojo.getResponse().getBooking_details().getBed_id(),
                                        loginPojo.getResponse().getBooking_details().getShare(),
                                        loginPojo.getResponse().getBooking_details().getBed_name(),
                                        loginPojo.getResponse().getBooking_details().getRoom_name(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getName(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getMobile(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getProfile_pic(),
                                        loginPojo.getResponse().getBooking_details().getPresent_booking_id()
                                        );
                           // }
                        }else{

                        }


                    }
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("to", "home");
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                    context.startActivity(intent, options.toBundle());
                    finish();
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
