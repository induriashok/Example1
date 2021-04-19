package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;


import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.NotificationsContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class NotificationImpl extends BasePresenter implements NotificationsContract.IPresenter {
    private NotificationsContract.IView mainContainView;
    private Context context;

    public NotificationImpl(NotificationsContract.IView mainContainView, Context context) {
        super(mainContainView, context);
        this.context = context;
        this.mainContainView = mainContainView;
    }

    @Override
    public void notifications(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.notifications(NetworkConstants.RequestCode.NOTIFICATIONS, requestBody);
    }
}
