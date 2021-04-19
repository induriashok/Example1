package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;


import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.LoginActivityContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class LoginActivitylmpl extends BasePresenter implements LoginActivityContract.IPresenter {
    private LoginActivityContract.IView mainContainView;
    private Context context;

    public LoginActivitylmpl(LoginActivityContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void userLogin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.login(NetworkConstants.RequestCode.USER_LOGIN, requestBody);
    }
}
