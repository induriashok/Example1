package com.joystays.joy.activities;

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

import com.google.gson.Gson;
import com.joystays.joy.Adapter.TrasactionsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.TransactionsActContract;
import com.joystays.joy.mvp.presenter.activity.TransactionsActImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.TransactionsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TransactionActivity extends BaseAbstractActivity<TransactionsActImpl> implements TransactionsActContract.IView, APIResponseCallback {
    private ImageView back_arrow;

    private UserSession userSession;
    private TransactionsPojo transactionsPojo;
    private String hostel_id, user_id, token;
    private RecyclerView rview_payments;
    private TrasactionsAdapter trasactionsAdapter;
    private String isApproved = "";
    private TextView tv_no_data ;


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
        userSession = new UserSession(TransactionActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        isApproved = userSession.getUserDetails().get("approved");


        back_arrow = findViewById(R.id.back_arrow);
        rview_payments = findViewById(R.id.rview_payments);
        tv_no_data = findViewById(R.id.tv_no_data);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callTransactionsWs();
    }

    private void callTransactionsWs() {


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.transactions(context, this, requestBody);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_transaction, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new TransactionsActImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.TRANSACTIONS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    tv_no_data.setVisibility(View.GONE);
                    transactionsPojo = new Gson().fromJson(responseString, TransactionsPojo.class);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                    rview_payments.setLayoutManager(mLayoutManager);
                    trasactionsAdapter = new TrasactionsAdapter(context, transactionsPojo);
                    rview_payments.setAdapter(trasactionsAdapter);

                }else{
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
}
