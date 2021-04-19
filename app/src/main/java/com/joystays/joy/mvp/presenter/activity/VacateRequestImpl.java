package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.VacateRequestContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class VacateRequestImpl extends BasePresenter implements VacateRequestContract.IPresenter {

    private VacateRequestContract.IView mainContainView;
    private Context context;

    public VacateRequestImpl(VacateRequestContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.view = mainContainView;
    }

    @Override
    public void vacateRequest(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.vacateRequest(NetworkConstants.RequestCode.VACATE_REQUEST, requestBody);
    }

    @Override
    public void triprequest(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.triprequest(NetworkConstants.RequestCode.TRIP_REQUEST, requestBody);
    }

    @Override
    public void my_trips(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.my_trips(NetworkConstants.RequestCode.MY_TRIPS, requestBody);
    }

    @Override
    public void extend_trip_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.extend_trip_request(NetworkConstants.RequestCode.EXTEND_TRIP, requestBody);
    }

    @Override
    public void my_vacant_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.my_vacant_request(NetworkConstants.RequestCode.MY_VACANT_REQUEST, requestBody);
    }

    @Override
    public void extend_vacant_request(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.extend_vacant_request(NetworkConstants.RequestCode.EXTEND_VACATE_REQUEST, requestBody);
    }
}
