package com.joystays.joy.mvp.presenter.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.fragment.HomeFragContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class HomFragImpl extends BasePresenter implements HomeFragContract.IPresenter {

    public HomFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void nearByHostels(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.nearhostels(NetworkConstants.RequestCode.NEAR_HOSTELS, requestBody);

    }

    @Override
    public void setFavourite(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.setFavourite(NetworkConstants.RequestCode.SET_FAV, requestBody);

    }

    @Override
    public void user_booking_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.user_booking_details(NetworkConstants.RequestCode.USER_BOOKING_DETAILS, requestBody);

    }

    @Override
    public void submit_hotel_rating(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.submit_rating(NetworkConstants.RequestCode.SUBMIT_RATING, requestBody);
    }

    @Override
    public void openPopUpRatings(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.openPopUpRatings(NetworkConstants.RequestCode.RATING_CHECK, requestBody);
    }
}
