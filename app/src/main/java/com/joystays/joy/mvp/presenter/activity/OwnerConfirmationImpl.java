package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.OwnerConformationActContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class OwnerConfirmationImpl extends BasePresenter implements OwnerConformationActContract.IPresenter {
    private OwnerConformationActContract.IView mainContainView;
    private Context context;

    public OwnerConfirmationImpl(OwnerConformationActContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void bookingDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.bookingDetails(NetworkConstants.RequestCode.BOOKING_DETAILS, requestBody);

    }


    @Override
    public void cancelBooking(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.cancelBooking(NetworkConstants.RequestCode.CANCEL_BOOKING, requestBody);

    }
}
