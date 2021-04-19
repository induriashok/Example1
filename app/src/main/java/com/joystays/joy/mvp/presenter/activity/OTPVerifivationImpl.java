package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.OTPVerificationContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class OTPVerifivationImpl extends BasePresenter implements OTPVerificationContract.IPresenter {
    private OTPVerificationContract.IView mainContainView;
    private Context context;

    public OTPVerifivationImpl(OTPVerificationContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void verifiyOTP(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.signUp(NetworkConstants.RequestCode.USER_SIGNUP, requestBody);
    }

    @Override
    public void forgot_pass(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.forgotPassword(NetworkConstants.RequestCode.FORGOT_PASSWORD, requestBody);
    }
}
