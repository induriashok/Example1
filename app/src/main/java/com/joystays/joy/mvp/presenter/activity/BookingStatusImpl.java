package com.joystays.joy.mvp.presenter.activity;

import android.content.Context;

import com.joystays.joy.mvp.base.BasePresenter;
import com.joystays.joy.mvp.contract.activity.BookingStatusAcContract;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.network.UserApiCallModel;

import java.util.Map;

public class BookingStatusImpl extends BasePresenter implements BookingStatusAcContract.IPresenter {
    private BookingStatusAcContract.IView mainContainView;
    private Context context;

    public BookingStatusImpl(BookingStatusAcContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.mainContainView = view;

    }

    @Override
    public void bookingStatus(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.hostelDetails(NetworkConstants.RequestCode.HOSTEL_DETAILS, requestBody);

    }
    @Override
    public void user_booking_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.user_booking_details(NetworkConstants.RequestCode.USER_BOOKING_DETAILS, requestBody);

    }

    @Override
    public void cancelBooking(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.cancelBooking(NetworkConstants.RequestCode.CANCEL_BOOKING, requestBody);

    }

    @Override
    public void complaint(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiCallModel userApiCallModel = new UserApiCallModel(context, apiResponseCallback, requestBody);
        userApiCallModel.compaint(NetworkConstants.RequestCode.USER_COMPLAINT, requestBody);
    }
}
