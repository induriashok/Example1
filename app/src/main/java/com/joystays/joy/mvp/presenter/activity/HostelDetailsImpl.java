package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;

import com.joystays.joy.mvp.contract.activity.HosteldetailsContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class HostelDetailsImpl extends BasePresenter implements HosteldetailsContract.IPresenter {
    private HosteldetailsContract.IView mainContainView;
    private Context context;

    public HostelDetailsImpl(HosteldetailsContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;
    }

    @Override
    public void hostelDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostelDetails(NetworkConstants.RequestCode.HOSTEL_DETAILS, requestBody);

    }

    @Override
    public void hostelPrices(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostelPrices(NetworkConstants.RequestCode.HOSTEL_PRICES, requestBody);

    }
    @Override
    public void setFavourite(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.setFavourite(NetworkConstants.RequestCode.SET_FAV, requestBody);

    }

}
