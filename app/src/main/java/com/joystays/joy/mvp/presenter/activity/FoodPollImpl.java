package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.FoodPollContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class FoodPollImpl extends BasePresenter implements FoodPollContract.IPresenter {

    private FoodPollContract.IView mainContainView;
    private Context context;

    public FoodPollImpl(FoodPollContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;
    }

    @Override
    public void send_polls(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.send_polls(NetworkConstants.RequestCode.SEND_POLL_LIKE, requestBody);
    }

    @Override
    public void getPoll(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.getPoll(NetworkConstants.RequestCode.GET_FOOD_POLL, requestBody);
    }
}