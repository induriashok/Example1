package com.joystays.joy.mvp.contract.fragment;

import android.content.Context;

import com.joystays.joy.mvp.base.IBaseContract;
import com.joystays.joy.mvp.base.IBaseDataManager;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public interface HomeFragContract {
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
        void nearByHostels(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void setFavourite(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void user_booking_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void submit_hotel_rating(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void openPopUpRatings(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
    }

    /**
     * Declared the methods here which can be used in Data manager of Home Screen.
     */
    interface IDataManager extends IBaseDataManager {

    }
}
