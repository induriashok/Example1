package com.joystays.joy.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.RateYourHostelContract;
import com.joystays.joy.mvp.presenter.activity.RateYourHostelImpl;
import com.joystays.joy.network.APIResponseCallback;

public class RateYourHostelActvity extends BaseAbstractActivity<RateYourHostelImpl> implements RateYourHostelContract.IView, APIResponseCallback {

    private RatingBar rb_room_rating,rb_owner_rating,rb_overall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.hotel_facilities_rating, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new RateYourHostelImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
