package com.joystays.joy.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.SearchHostelAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.HostelSearchActContract;
import com.joystays.joy.mvp.presenter.activity.HostelSearchImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostelSearchActivity extends BaseAbstractActivity<HostelSearchImpl> implements HostelSearchActContract.IView, APIResponseCallback {
    private UserSession userSession;
    private NearByHostelsPojo nearByHostelsPojo;
    private SearchHostelAdapter searchHostelAdapter;
    private List<NearByHostelsPojo.ResponseBean> responseBeans;
    private RecyclerView rview_hostel;
    private EditText et_search;
    private String user_id, token;
    private Intent intent;
    private String lat, lng,search,search2;
    private TextView tv_no_results;

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.hostel_search, null);
        return view;
    }

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
        userSession = new UserSession(context);
        rview_hostel = findViewById(R.id.rview_hostel);
        et_search = findViewById(R.id.et_search);
        tv_no_results = findViewById(R.id.tv_no_results);
        intent = getIntent();
        lat = intent.getStringExtra("lat");
        lng = intent.getStringExtra("lng");
        et_search.setFocusable(true);

        responseBeans = new ArrayList<>();

        userSession = new UserSession(this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                  search = et_search.getText().toString();
                if (search.length() > 1) {
                    try {
                        callNearByHostelsWS();
                        searchHostelAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if (search.length()==0){
                    System.out.println("ontextchanged"+search);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                 search2 = et_search.getText().toString();

                if (search2.length() > 1) {

                    try {
                        callNearafterByHostelsWS();
                        searchHostelAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if (search2.length()==0){



                }
            }
        });
    }

    private void callNearafterByHostelsWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_type", "");
        requestBody.put("search", search2);
        requestBody.put("lat", lat);
        requestBody.put("lng", lng);
        requestBody.put("count", "0");
        presenter.nearByHostels(context, this, requestBody);
        System.out.println("requestBody" + requestBody);
    }

    private void callNearByHostelsWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_type", "");
        requestBody.put("search", search);
        requestBody.put("lat", lat);
        requestBody.put("lng", lng);
        requestBody.put("count", "0");
        presenter.nearByHostels(context, this, requestBody);
        System.out.println("requestBody" + requestBody);
    }


    @Override
    public void setPresenter() {
        presenter = new HostelSearchImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, this);
            } else if (NetworkConstants.RequestCode.NEAR_HOSTELS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    tv_no_results.setVisibility(View.GONE);
                    rview_hostel.setVisibility(View.VISIBLE);
                    responseBeans = new ArrayList<>();
                    nearByHostelsPojo = new Gson().fromJson(responseString, NearByHostelsPojo.class);
                    responseBeans.addAll(nearByHostelsPojo.getResponse());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    rview_hostel.setLayoutManager(mLayoutManager);
                    searchHostelAdapter = new SearchHostelAdapter(this, responseBeans);
                    rview_hostel.setAdapter(searchHostelAdapter);


                    searchHostelAdapter.setOnItemClickListener(new SearchHostelAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(context, HostelDetailsActivity.class);
                            intent.putExtra("hostel_id", responseBeans.get(position).getId());
                            intent.putExtra("ac_status", responseBeans.get(position).getAc());
                            intent.putExtra("non_ac_status", responseBeans.get(position).getNon_ac());
                            intent.putExtra("gender_type", responseBeans.get(position).getHostel_type());
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                            startActivity(intent, options.toBundle());

                        }
                    });


                } else {
                    tv_no_results.setVisibility(View.VISIBLE);
                    rview_hostel.setVisibility(View.GONE);
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
