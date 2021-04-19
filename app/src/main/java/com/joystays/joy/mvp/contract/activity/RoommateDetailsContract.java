package com.joystays.joy.mvp.contract.activity;

import android.content.Context;
import androidx.fragment.app.Fragment;

import com.joystays.joy.mvp.base.IBaseContract;
import com.joystays.joy.mvp.base.IBaseDataManager;
import com.joystays.joy.network.APIResponseCallback;

import java.util.Map;

public interface RoommateDetailsContract {
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

        void roommateDetails(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
