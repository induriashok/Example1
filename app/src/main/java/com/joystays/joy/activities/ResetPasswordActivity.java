
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
import android.widget.TextView;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ResetPasswordContract;
import com.joystays.joy.mvp.presenter.activity.ResetPasswordImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends BaseAbstractActivity<ResetPasswordImpl> implements ResetPasswordContract.IView, APIResponseCallback {
    private TextView tv_save;
    private ImageView back_arrow;
    private EditText et_pass2, et_pass1;
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
    protected void initializeViews() {
        super.initializeViews();


        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        tv_save = findViewById(R.id.tv_save);
        back_arrow = findViewById(R.id.back_arrow);
        et_pass1 = findViewById(R.id.et_pass1);
        et_pass2 = findViewById(R.id.et_pass2);
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_pass1.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (et_pass1.getText().toString().length() < 6) {
                    Toast.makeText(context, "Password must be 6 characters", Toast.LENGTH_SHORT).show();
                } else if (et_pass2.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (et_pass2.getText().toString().length() < 6) {
                    Toast.makeText(context, "Confirm Password must be 6 characters", Toast.LENGTH_SHORT).show();
                } else if (et_pass1.getText().toString().length() != et_pass2.getText().toString().length()) {
                    Toast.makeText(context, "Password Mismatched", Toast.LENGTH_SHORT).show();
                } else if (!et_pass1.getText().toString().equalsIgnoreCase(et_pass2.getText().toString())) {
                    Toast.makeText(context, "Password Mismatched", Toast.LENGTH_SHORT).show();

                } else {
                    callUpdatePassword();
                }
            }
        });


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void callUpdatePassword() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", user_id);
        requestBody.put("password", et_pass1.getText().toString());
        presenter.reset_password(ResetPasswordActivity.this, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_reset_password, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ResetPasswordImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.RESET_PASSWORD == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Toast.makeText(context, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
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
