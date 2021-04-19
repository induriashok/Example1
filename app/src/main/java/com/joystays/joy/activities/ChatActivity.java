package com.joystays.joy.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.joystays.joy.Adapter.ChatsAdapter;
import com.joystays.joy.Adapter.RoomMateDetailsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ChangeRoomActContract;
import com.joystays.joy.mvp.contract.activity.ChatsActContract;
import com.joystays.joy.mvp.presenter.activity.ChangeRoomImpl;
import com.joystays.joy.mvp.presenter.activity.ChatsActImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.ChatsPojo;
import com.joystays.joy.pojos.RoomMateDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends BaseAbstractActivity<ChatsActImpl> implements ChatsActContract.IView, View.OnClickListener, APIResponseCallback {
    ChatsAdapter chatsAdapter;
    ChatsPojo chatsPojo;
    UserSession userSession;
    APIResponseCallback apiResponseCallback;
    String user_id,token;
    RecyclerView chat_rv;
    TextView no_chats_tv;

    private ImageView back_arrow;






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

        userSession = new UserSession(this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");


        chat_rv = findViewById(R.id.chat_rv);
        no_chats_tv = findViewById(R.id.no_chats_tv);
        back_arrow = findViewById(R.id.back_arrow);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        chatWBS();




    }

    private void chatWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_chats(this, this, requestBody);

    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_chat, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new ChatsActImpl(this, this);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.USER_CHATS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    chatsPojo = new Gson().fromJson(responseString, ChatsPojo.class);
                    no_chats_tv.setVisibility(View.GONE);
                    chat_rv.setVisibility(View.VISIBLE);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                    chat_rv.setLayoutManager(mLayoutManager);
                    chatsAdapter = new ChatsAdapter(context, chatsPojo);
                    chat_rv.setAdapter(chatsAdapter);
                }else{
                    no_chats_tv.setVisibility(View.VISIBLE);
                    chat_rv.setVisibility(View.GONE);


                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
