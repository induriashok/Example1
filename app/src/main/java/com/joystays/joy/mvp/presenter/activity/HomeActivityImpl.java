package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.HomeActivityContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class HomeActivityImpl extends BasePresenter implements HomeActivityContract.IPresenter {

    private HomeActivityContract.IView mainContainView;
    private Context context;

    public HomeActivityImpl(HomeActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;

    }

    @Override
    public void update_token(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.update_device_token(NetworkConstants.RequestCode.UPDATE_TOKEN, requestBody);

    }


    @Override
    public void user_booking_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.user_booking_details(NetworkConstants.RequestCode.USER_BOOKING_DETAILS, requestBody);

    }

    @Override
    public void user_login(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.login(NetworkConstants.RequestCode.USER_LOGIN, requestBody);

    }
}
