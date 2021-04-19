package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.RateYourHostelContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class RateYourHostelImpl extends BasePresenter implements RateYourHostelContract.IPresenter {
    private RateYourHostelContract.IView mainContainView;
    private Context context;

    public RateYourHostelImpl(RateYourHostelContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void submit_hotel_rating(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.submit_rating(NetworkConstants.RequestCode.SUBMIT_RATING, requestBody);
    }
}