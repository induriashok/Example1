package com.joystays.joy.mvp.contract.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.IBaseContract;
import com.joystays.joy.mvp.base.IBaseDataManager;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public interface MyBookingFragContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int LAUNCH_ALL_SERVICE_FRAGMENT_SCREEN = 10;
    }

    /**
     * Declared the methods here which can be used in Home Screen.
     */
    interface IView {


    }

    /**
     * Declared the methods here which can be used in Home Screen.
     */
    interface IPresenter {
        void myBookings(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void myWallet(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

    }

    /**
     * Declared the methods here which can be used in Data manager of Home Screen.
     */
    interface IDataManager extends IBaseDataManager {

    }
}
