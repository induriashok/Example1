package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.TransactionDetailsActContract;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public class TransactionDetailsImpl extends BasePresenter implements TransactionDetailsActContract.IPresenter {

    private TransactionDetailsActContract.IView mainContainView;
    private Context context;

    public TransactionDetailsImpl(TransactionDetailsActContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void transactionsDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}
