package com.joystays.joy.mvp.presenter.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.fragment.FavouriteFragContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class FavHostelFragImpl extends BasePresenter implements FavouriteFragContract.IPresenter {

    public FavHostelFragImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void favHostels(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.favHostels(NetworkConstants.RequestCode.FAV_HOSTELS, requestBody);

    }
}
