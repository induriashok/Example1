package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.RoommateDetailsContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class RoomMateDetailsImpl extends BasePresenter implements RoommateDetailsContract.IPresenter {


    private RoommateDetailsContract.IView mainContainView;
    private Context context;

    public RoomMateDetailsImpl(RoommateDetailsContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void roommateDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.roommateDetails(NetworkConstants.RequestCode.ROOMMATE_DETAILS, requestBody);

    }
}
