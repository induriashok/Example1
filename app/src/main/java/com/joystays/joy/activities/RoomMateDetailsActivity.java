package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.RoomMateDetailsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.customfonts.CustomTextViewBold;
import com.joystays.joy.mvp.contract.activity.RoommateDetailsContract;
import com.joystays.joy.mvp.presenter.activity.RoomMateDetailsImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.RoomMateDetailsPojo;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RoomMateDetailsActivity extends BaseAbstractActivity<RoomMateDetailsImpl> implements RoommateDetailsContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private CustomTextViewBold ctvb_cancel, ctvb_continue;
    private String owner_id, room_type, package_type, from_date, to_date, share_type,owner_pic,book_status;
    private String hostel_id, token;
    private RoomMateDetailsPojo roomDetailsPojo;
    private RoomMateDetailsAdapter roomMateDetailsAdapter;
    private RecyclerView rview_roommate_details;
    private TextView tv_no_members;

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
        back_arrow = findViewById(R.id.back_arrow);
        ctvb_cancel = findViewById(R.id.ctvb_cancel);
        ctvb_continue = findViewById(R.id.ctvb_continue);
        rview_roommate_details = findViewById(R.id.rview_roommate_details);
        tv_no_members = findViewById(R.id.tv_no_members);


        final Intent intent = getIntent();
        hostel_id = intent.getStringExtra("hostel_id");
        token = intent.getStringExtra("token");

        owner_id = intent.getStringExtra("owner_id");
        room_type = intent.getStringExtra("room_type");
        package_type = intent.getStringExtra("package_type");
        from_date = intent.getStringExtra("from_date");
        to_date = intent.getStringExtra("to_date");
        share_type = intent.getStringExtra("share_type");
        owner_pic = intent.getStringExtra("owner_pic");
        book_status = intent.getStringExtra("book_status");
        ctvb_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (book_status.equalsIgnoreCase("done")) {
                     Toast.makeText(context, "You are not allowed for another Booking", Toast.LENGTH_SHORT).show();
                 }else{
                     Intent intent1 = new Intent(RoomMateDetailsActivity.this, ProfilePaymentActvity.class);
                     intent1.putExtra("hostel_id", hostel_id);
                     intent1.putExtra("owner_id", owner_id);
                     intent1.putExtra("token", token);
                     intent1.putExtra("room_type", room_type);
                     intent1.putExtra("package_type", package_type);
                     intent1.putExtra("from_date", from_date);
                     intent1.putExtra("to_date", to_date);
                     intent1.putExtra("share_type", share_type);
                     intent1.putExtra("owner_pic", owner_pic);
                     startActivity(intent1);
                     System.out.println("gyghgggj"+room_type);

                 }


            }
        });
        ctvb_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("hostel_id", hostel_id);
        //requestBody.put("hostel_id", "1");
        requestBody.put("room_id", Variables.room_id);
        presenter.roommateDetails(context, this, requestBody);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_secure_booking, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new RoomMateDetailsImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.ROOMMATE_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    roomDetailsPojo = new Gson().fromJson(responseString, RoomMateDetailsPojo.class);
                    tv_no_members.setVisibility(View.GONE);
                    rview_roommate_details.setVisibility(View.VISIBLE);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                    rview_roommate_details.setLayoutManager(mLayoutManager);
                    roomMateDetailsAdapter = new RoomMateDetailsAdapter(context, roomDetailsPojo);
                    rview_roommate_details.setAdapter(roomMateDetailsAdapter);
                }
            } else {
                Toast.makeText(context, roomDetailsPojo.getMessage(), Toast.LENGTH_SHORT).show();
                tv_no_members.setVisibility(View.VISIBLE);
                rview_roommate_details.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
