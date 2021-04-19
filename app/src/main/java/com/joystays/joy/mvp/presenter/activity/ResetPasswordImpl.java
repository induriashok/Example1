package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.ResetPasswordContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class ResetPasswordImpl extends BasePresenter implements ResetPasswordContract.IPresenter {

    private ResetPasswordContract.IView mainContainView;
    private Context context;

    public ResetPasswordImpl(ResetPasswordContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;
    }


    @Override
    public void reset_password(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.reset_password(NetworkConstants.RequestCode.RESET_PASSWORD, requestBody);
    }
}
