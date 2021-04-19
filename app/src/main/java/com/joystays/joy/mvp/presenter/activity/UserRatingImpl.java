package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.UserRatingContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class UserRatingImpl extends BasePresenter implements UserRatingContract.IPresenter {
    private UserRatingContract.IView mainContainView;
    private Context context;

    public UserRatingImpl(UserRatingContract.IView mainContainView, Context context) {
        super(mainContainView, context);
    }

    @Override
    public void userRating(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.submit_rating(NetworkConstants.RequestCode.SUBMIT_RATING, requestBody);
    }
}
