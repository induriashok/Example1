package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.SelectFloorRoomContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class SelectFloorRoomImpl extends BasePresenter implements SelectFloorRoomContract.IPresenter {

    private SelectFloorRoomContract.IView mainContainView;
    private Context context;

    public SelectFloorRoomImpl(SelectFloorRoomContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }


    @Override
    public void userLogin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.login(NetworkConstants.RequestCode.USER_LOGIN, requestBody);
    }

    @Override
    public void check_hostel_prices(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.check_hostel_prices(NetworkConstants.RequestCode.CHECK_SHARE_PRICES, requestBody);

    }

    @Override
    public void getFloors(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.signUp(NetworkConstants.RequestCode.HOSTEL_ROOMS, requestBody);

    }

    @Override
    public void getBeds(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.getBeds(NetworkConstants.RequestCode.HOSTEL_BEDS, requestBody);

    }

    @Override
    public void getRooms(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.getRooms(NetworkConstants.RequestCode.HOSTEL_ROOMS, requestBody);

    }

    @Override
    public void hostel_floors(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostel_floors(NetworkConstants.RequestCode.HOSTEL_FLOORS, requestBody);

    }
}
