package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.ForgotPasswordActivityContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class ForgotPasswordActivityImpl  extends BasePresenter implements ForgotPasswordActivityContract.IPresenter  {
    public ForgotPasswordActivityImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void forgot_pass(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.forgotPassword(NetworkConstants.RequestCode.FORGOT_PASSWORD, requestBody);
    }
}
