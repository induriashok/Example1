package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;

import com.joystays.joy.mvp.contract.activity.OwnerComplaintContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class OwnerComplaintImpl extends BasePresenter implements OwnerComplaintContract.IPresenter {

    private OwnerComplaintContract.IView mainContainView;
    private Context context;

    public OwnerComplaintImpl(OwnerComplaintContract.IView mainContainView, Context context) {
        super(mainContainView, context);
    }

    @Override
    public void complaint(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.compaint(NetworkConstants.RequestCode.USER_COMPLAINT, requestBody);
    }
}
