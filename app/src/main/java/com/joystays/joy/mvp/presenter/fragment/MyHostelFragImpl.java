package com.joystays.joy.mvp.presenter.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.fragment.MyHostelContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class MyHostelFragImpl extends BasePresenter implements MyHostelContract.IPresenter {
    public MyHostelFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void hostelMenu(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostelMenu(NetworkConstants.RequestCode.HOSTEL_MENU, requestBody);
    }

    public void lockDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.lockDetails(NetworkConstants.RequestCode.LOCK_DETAILS, requestBody);
    }

    public void lockRoom(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.lockRoom(NetworkConstants.RequestCode.LOCK_ROOM, requestBody);
    }
    @Override
    public void user_booking_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.user_booking_details(NetworkConstants.RequestCode.USER_BOOKING_DETAILS, requestBody);

    }
    @Override
    public void submit_polling(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.submit_polling(NetworkConstants.RequestCode.SUBMIT_POLLING, requestBody);
    }

    @Override
    public void get_banners(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.get_banners(NetworkConstants.RequestCode.GET_BANNERS, requestBody);
    }
}
