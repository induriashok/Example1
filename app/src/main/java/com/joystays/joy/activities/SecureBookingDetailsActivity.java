package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.SecureBookingContract;
import com.joystays.joy.mvp.presenter.activity.SecureBookingImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.utils.Variables;

public class SecureBookingDetailsActivity extends BaseAbstractActivity<SecureBookingImpl> implements SecureBookingContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_ok;
    TextView hostel_name;
    LinearLayout tv_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        back_arrow = findViewById(R.id.back_arrow);
        linear_ok = findViewById(R.id.linear_ok);
        hostel_name = findViewById(R.id.hostel_name);
        tv_call = findViewById(R.id.tv_call);
        hostel_name.setText(Variables.hostelname);

        tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + Variables.hostel_number;
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        linear_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecureBookingDetailsActivity.this, OwnerConformationActivity.class));
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent i = new Intent(SecureBookingDetailsActivity.this,HomeActivity.class);
                i.putExtra("to", "home");
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(SecureBookingDetailsActivity.this,HomeActivity.class);
        i.putExtra("to", "home");
        startActivity(i);
        finish();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_secure_booking_details, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new SecureBookingImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
