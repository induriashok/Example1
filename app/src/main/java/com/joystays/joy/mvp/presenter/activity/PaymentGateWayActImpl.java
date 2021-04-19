package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.PaymentGateWayActContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class PaymentGateWayActImpl extends BasePresenter implements PaymentGateWayActContract.IPresenter{

    private PaymentGateWayActContract.IView mainContainView;
    private Context context;

    public PaymentGateWayActImpl(PaymentGateWayActContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }



    @Override
    public void placeOrder(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.placeOrder(NetworkConstants.RequestCode.PLACE_ORDER, requestBody);
    }
    @Override
    public void renewBooking(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.renewBooking(NetworkConstants.RequestCode.RENEW_BOOKING, requestBody);
    }


}
