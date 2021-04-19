package com.joystays.joy.activities;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.NotificationAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.NotificationsContract;
import com.joystays.joy.mvp.presenter.activity.NotificationImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.NotificationsPojo;
import com.joystays.joy.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationActivity extends BaseAbstractActivity<NotificationImpl> implements NotificationsContract.IView, APIResponseCallback {
    private RecyclerView recycler_notifications;
    NotificationAdapter notificationAdapter;
    private ImageView back_arrow;
    private NotificationsPojo notificationsPojo;
    private UserSession userSession;
    private String user_id, token;
    private int page_number = 0;
    private TextView tv_no_data;

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
        tv_no_data = findViewById(R.id.tv_no_data);
        recycler_notifications = findViewById(R.id.recycler_notifications);
        userSession = new UserSession(NotificationActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callNotificationsWs();
    }

    private void callNotificationsWs() {
        Map<String, String> requestBody = new HashMap<>();
        //   requestBody.put("token", token);
        requestBody.put("token", token);
        requestBody.put("user_type", "user");
        requestBody.put("count", String.valueOf(page_number));
        presenter.notifications(this, this, requestBody);
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
            } else if (NetworkConstants.RequestCode.NOTIFICATIONS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    tv_no_data.setVisibility(View.GONE);
                    notificationsPojo = new Gson().fromJson(responseString, NotificationsPojo.class);


                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    recycler_notifications.setLayoutManager(mLayoutManager);
                    notificationAdapter = new NotificationAdapter(this, notificationsPojo);
                    recycler_notifications.setAdapter(notificationAdapter);
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    tv_no_data.setVisibility(View.VISIBLE);
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
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_notification, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new NotificationImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }
}
