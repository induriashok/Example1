package com.joystays.joy.mvp.contract.activity;

import android.content.Context;
import androidx.fragment.app.Fragment;

import com.joystays.joy.mvp.base.IBaseContract;
import com.joystays.joy.mvp.base.IBaseDataManager;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public interface OwnerConformationActContract {
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN = 14;
    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IView {
        void replaceRespectiveFragment(Fragment fragment, String[] data, String tag);

    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        void bookingDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void cancelBooking(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);


        //This method is use to login.
        // void launchHomeFragment();
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
