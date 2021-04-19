package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.ChangeRoomActContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class ChangeRoomImpl extends BasePresenter implements ChangeRoomActContract.IPresenter {
    private ChangeRoomActContract.IView mainContainView;
    private Context context;

    public ChangeRoomImpl(ChangeRoomActContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;





    }

    @Override
    public void changRoom(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.changeroom(NetworkConstants.RequestCode.CHANGE_ROOM, requestBody);

    }
    @Override
    public void hostel_floors(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostel_floors(NetworkConstants.RequestCode.HOSTEL_FLOORS, requestBody);

    }

    @Override
    public void hostel_beds(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.getBeds(NetworkConstants.RequestCode.HOSTEL_BEDS, requestBody);

    }

    @Override
    public void hostel_rooms(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.getRooms(NetworkConstants.RequestCode.HOSTEL_ROOMS, requestBody);

    }
}
