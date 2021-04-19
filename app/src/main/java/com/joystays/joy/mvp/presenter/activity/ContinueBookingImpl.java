package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.ContinueBookingActContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class ContinueBookingImpl extends BasePresenter implements ContinueBookingActContract.IPresenter {
    private ContinueBookingActContract.IView mainContainView;
    private Context context;

    public ContinueBookingImpl(ContinueBookingActContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;
    }

    @Override
    public void checkout(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.checkout_calcations(NetworkConstants.RequestCode.CHECKOUT_CALCULATIONS, requestBody);

    }
}
