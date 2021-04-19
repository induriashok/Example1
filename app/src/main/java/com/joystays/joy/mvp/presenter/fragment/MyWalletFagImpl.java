package com.joystays.joy.mvp.presenter.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.fragment.MyWalletFragContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class MyWalletFagImpl extends BasePresenter implements MyWalletFragContract.IPresenter {
    public MyWalletFagImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void myWallet(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.myWallet(NetworkConstants.RequestCode.MY_WALLET, requestBody);
    }
}
