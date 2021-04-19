package com.joystays.joy.mvp.presenter.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.fragment.MyBookingFragContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class MyBookingFragImpl extends BasePresenter implements MyBookingFragContract.IPresenter {
    public MyBookingFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void myBookings(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.myBookings(NetworkConstants.RequestCode.MY_BOOKINGS, requestBody);
    }

    @Override
    public void myWallet(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.myWallet(NetworkConstants.RequestCode.MY_WALLET, requestBody);
    }
}
