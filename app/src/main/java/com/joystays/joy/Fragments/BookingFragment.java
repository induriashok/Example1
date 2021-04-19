package com.joystays.joy.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.MyBookingsAdapter;
import com.joystays.joy.activities.HomeActivity;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractFragment;
import com.joystays.joy.mvp.contract.fragment.MyBookingFragContract;
import com.joystays.joy.mvp.presenter.fragment.MyBookingFragImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.BookingsPojo;
import com.joystays.joy.pojos.MyWalletPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookingFragment extends BaseAbstractFragment<MyBookingFragImpl> implements MyBookingFragContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private RecyclerView rview_bookings;
    private UserSession userSession;
    private String token, user_id;
    private BookingsPojo bookingsPojo;
    private int page_number = 0;
    private MyBookingsAdapter myBookingsAdapter;
    private TextView tv_nodata;
    CardView card_v;

    private MyWalletPojo myWalletPojo;
    private TextView tv_name, tv_room_no, tv_share_type, tv_due_amount, tv_package, tv_refund_amnt, tv_uid_no, tv_bed_no, tv_paid_date;
    private CardView data_cv;

    private SimpleDateFormat dateFormatprev;
    private Date d;
    private SimpleDateFormat dateFormat;

    ImageView iv_profile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.GONE);

        back_arrow = view.findViewById(R.id.back_arrow);
        rview_bookings = view.findViewById(R.id.rview_bookings);
        tv_nodata = view.findViewById(R.id.tv_nodata);

        tv_name = view.findViewById(R.id.tv_name);
        tv_room_no = view.findViewById(R.id.tv_room_no);
        tv_share_type = view.findViewById(R.id.tv_share_type);
        tv_due_amount = view.findViewById(R.id.tv_due_amount);
        tv_package = view.findViewById(R.id.tv_package);
        tv_refund_amnt = view.findViewById(R.id.tv_refund_amnt);
        tv_uid_no = view.findViewById(R.id.tv_uid_no);
        tv_bed_no = view.findViewById(R.id.tv_bed_no);
        tv_paid_date = view.findViewById(R.id.tv_paid_date);
        data_cv = view.findViewById(R.id.data_cv);
        iv_profile = view.findViewById(R.id.iv_profile);
        data_cv.setVisibility(View.GONE);


        userSession = new UserSession(getActivity());

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");

        dateFormatprev = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat = new SimpleDateFormat("dd MMM yyyy");


        callMyBookingsWs();

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("to", "home");
                startActivity(intent);
            }
        });
    }

    private void callMyBookingsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        //requestBody.put("user_id", "1");
        requestBody.put("count", String.valueOf(page_number));
        presenter.myBookings(getActivity(), this, requestBody);

    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.booking_fragment, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new MyBookingFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.MY_BOOKINGS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    tv_nodata.setVisibility(View.GONE);
                    bookingsPojo = new Gson().fromJson(responseString, BookingsPojo.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                    rview_bookings.setLayoutManager(mLayoutManager);
                    myBookingsAdapter = new MyBookingsAdapter(getActivity(), bookingsPojo);
                    rview_bookings.setAdapter(myBookingsAdapter);
                    callMyWalletWs();

                } else {
                    tv_nodata.setVisibility(View.VISIBLE);
                }

            } else if (NetworkConstants.RequestCode.MY_WALLET == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    data_cv.setVisibility(View.VISIBLE);

                    myWalletPojo = new Gson().fromJson(responseString, MyWalletPojo.class);

                    tv_uid_no.setText(myWalletPojo.getResponse().getUBID());
                    tv_name.setText(myWalletPojo.getResponse().getName());
                    tv_room_no.setText(myWalletPojo.getResponse().getRoom_name());
                    tv_share_type.setText(myWalletPojo.getResponse().getShare());
                    tv_package.setText(myWalletPojo.getResponse().getPackage_type());
                    tv_refund_amnt.setText(myWalletPojo.getResponse().getAdvance_amount());
                    tv_bed_no.setText(myWalletPojo.getResponse().getBed_name());
                    tv_due_amount.setText(myWalletPojo.getResponse().getAmount());
                    myWalletPojo.getResponse().getBooking_status();
                    System.out.println("dhtjtyjkguyik" + myWalletPojo.getResponse().getBooking_status());

                    Picasso.with(getActivity()).load(NetworkConstants.URL.Imagepath_URL + myWalletPojo.getResponse().getProfile_pic()).into(iv_profile);

                    String last_paid = myWalletPojo.getResponse().getLast_payment_date().substring(0, 10);

                    d = dateFormatprev.parse(last_paid);

                    String last_paid_date = dateFormat.format(d);
                    tv_paid_date.setText(last_paid_date);


                    if (myWalletPojo.getResponse().getBooking_status().equalsIgnoreCase("vacated")) {
                        data_cv.setVisibility(View.VISIBLE);
                    } else {
                        data_cv.setVisibility(View.GONE);

                    }


                } else {
                    data_cv.setVisibility(View.GONE);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void callMyWalletWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        //requestBody.put("user_id", "1");
        presenter.myWallet(getActivity(), this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
