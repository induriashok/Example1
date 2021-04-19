package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.SecureBookingContract;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public class SecureBookingImpl extends BasePresenter implements SecureBookingContract.IPresenter {

    private SecureBookingContract.IView mainContainView;
    private Context context;

    public SecureBookingImpl(SecureBookingContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void bookingConfirmation(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}
