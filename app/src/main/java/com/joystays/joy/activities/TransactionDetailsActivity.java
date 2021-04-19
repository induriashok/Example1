package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.TransactionDetailsActContract;
import com.joystays.joy.mvp.presenter.activity.TransactionDetailsImpl;
import com.joystays.joy.network.APIResponseCallback;

public class TransactionDetailsActivity extends BaseAbstractActivity<TransactionDetailsImpl> implements TransactionDetailsActContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private TextView tv_paid_amount, tv_paid_date, tv_hostel_name,trasaction_id,paidz_to,paid_frm;
    private String paid_amount, hostel_name, payment_date,BID,user_name,txn_id="";

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
        tv_paid_amount = findViewById(R.id.tv_paid_amount);
        tv_hostel_name = findViewById(R.id.tv_hostel_name);
        tv_paid_date = findViewById(R.id.tv_paid_date);
        trasaction_id = findViewById(R.id.trasaction_id);
        paidz_to = findViewById(R.id.paidz_to);
        paid_frm = findViewById(R.id.paid_frm);
        Intent intent = getIntent();
        paid_amount = intent.getStringExtra("paid_amount");
        hostel_name = intent.getStringExtra("hostel_name");
        payment_date = intent.getStringExtra("payment_date");
        user_name = intent.getStringExtra("user_name");
        BID = intent.getStringExtra("BID");
        txn_id = intent.getStringExtra("txn_id");

        tv_paid_amount.setText(paid_amount);
        tv_hostel_name.setText(hostel_name);
        paidz_to.setText(hostel_name);
        tv_paid_date.setText(payment_date);
        paid_frm.setText(user_name);
        if(txn_id.equalsIgnoreCase(null)){
            trasaction_id.setText(BID);

        }else{
            trasaction_id.setText(txn_id);

        }

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_transaction_details, null);
        return view;
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void setPresenter() {
        presenter = new TransactionDetailsImpl(this, this);
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
