package com.joystays.joy.mvp.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.joystays.joy.controller.ApplicationController;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;


public abstract class BasePresenter<T> implements APIResponseCallback {

    protected T view;
    protected Context context;

    public BasePresenter(T view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void start() {
        ApplicationController.getInstance().setContext(context);
    }

    public void stop() {

    }

    public void launchLocatinScreen(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {

    }

    public void onFragmentLaunch(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {

    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {

    }
}