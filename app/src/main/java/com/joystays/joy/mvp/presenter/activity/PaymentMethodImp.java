package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.PaymentMethodContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class PaymentMethodImp extends BasePresenter implements PaymentMethodContract.IPresenter {
    private PaymentMethodContract.IView mainContainView;
    private Context context;

    public PaymentMethodImp(PaymentMethodContract.IView mainContainView, Context context) {
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
    @Override
    public void generatePaymentToken(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.generatePaymentToken(NetworkConstants.RequestCode.GENERATE_PAYMENT_TOKEN, requestBody);
    }
}
