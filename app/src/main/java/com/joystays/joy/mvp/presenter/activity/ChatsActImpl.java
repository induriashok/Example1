package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.ChangeRoomActContract;
import com.joystays.joy.mvp.contract.activity.ChatsActContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class ChatsActImpl extends BasePresenter implements ChatsActContract.IPresenter  {
    public ChatsActImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void user_chats(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.user_chats(NetworkConstants.RequestCode.USER_CHATS, requestBody);

    }
}
