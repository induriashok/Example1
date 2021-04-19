package com.joystays.joy.Fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.FavHostelsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.activities.HomeActivity;
import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.base.BaseAbstractFragment;
import com.joystays.joy.mvp.contract.fragment.FavouriteFragContract;
import com.joystays.joy.mvp.presenter.fragment.FavHostelFragImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.FavHostelsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.GPSTracker;
import com.joystays.joy.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FavoriteFragment extends BaseAbstractFragment<FavHostelFragImpl> implements FavouriteFragContract.IView, APIResponseCallback {
    private ImageView back_arrow;

    private FavHostelsAdapter favHostelsAdapter;
    private FavHostelsPojo favHostelsPojo;
    private UserSession userSession;
    private String token, user_id;
    private RecyclerView rview_fav;
    private TextView tv_no_data;
    private String isApproved = "";
    private double latitude, longitude;

    GPSTracker gpsTracker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        userSession = new UserSession(getActivity());

        gpsTracker = new GPSTracker(getActivity());
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {

            Toast.makeText(getActivity(), "Turn on Location", Toast.LENGTH_SHORT).show();
        }

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        isApproved = userSession.getUserDetails().get("approved");
        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.GONE);

//        CardView card_v = view.findViewById(R.id.card_v);
//        card_v.setVisibility(View.GONE);

//        ImageView iv_favorite = view.findViewById(R.id.iv_favorite);
//        iv_favorite.setImageResource(R.drawable.joy_logo_red);
        back_arrow = view.findViewById(R.id.back_arrow);
        rview_fav = view.findViewById(R.id.rview_fav);
        tv_no_data = view.findViewById(R.id.tv_no_data);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("to", "home");
                startActivity(intent);
            }
        });

        callFavHostelsWs();
    }

    private void callFavHostelsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("lat", String.valueOf(latitude));
        requestBody.put("lng", String.valueOf(longitude));
        presenter.favHostels(getActivity(), this, requestBody);
        System.out.println("requestBody"+requestBody);
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.favorite_fragment, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new FavHostelFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.FAV_HOSTELS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    tv_no_data.setVisibility(View.GONE);
                    favHostelsPojo = new Gson().fromJson(responseString, FavHostelsPojo.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                    rview_fav.setLayoutManager(mLayoutManager);
                    favHostelsAdapter = new FavHostelsAdapter(getActivity(), favHostelsPojo);

                    favHostelsAdapter.setOnItemClickListener(new FavHostelsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_hostel_item:

                                    String hostel_id = favHostelsPojo.getResponse().get(position).getId();
                                    String ac_availability = favHostelsPojo.getResponse().get(position).getAc();
                                    String gender_type = favHostelsPojo.getResponse().get(position).getHostel_type();

                                    Intent intent = new Intent(getActivity(), HostelDetailsActivity.class);
                                    intent.putExtra("hostel_id", hostel_id);
                                    intent.putExtra("ac_status", ac_availability);
                                    intent.putExtra("gender_type", gender_type);
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_left, R.anim.slide_out_right);
                                    getActivity().startActivity(intent, options.toBundle());



                                    break;

                                case R.id.tv_availability:

                                    String hostel_ida = favHostelsPojo.getResponse().get(position).getId();
                                    String ac_availabilitya = favHostelsPojo.getResponse().get(position).getAc();
                                    String gender_typea = favHostelsPojo.getResponse().get(position).getHostel_type();

                                    Intent intent1 = new Intent(getActivity(), HostelDetailsActivity.class);
                                    intent1.putExtra("hostel_id", hostel_ida);
                                    intent1.putExtra("ac_status", ac_availabilitya);
                                    intent1.putExtra("gender_type", gender_typea);
                                    ActivityOptions options1 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_left, R.anim.slide_out_right);
                                    getActivity().startActivity(intent1, options1.toBundle());



                                    break;
                                case R.id.ll_direction:

                                    String lat = favHostelsPojo.getResponse().get(position).getLat();
                                    String lng = favHostelsPojo.getResponse().get(position).getLng();

                                    String slat = String.valueOf(latitude);
                                    String slng = String.valueOf(longitude);
                                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + slat + "," + slng + "&daddr=" + lat + "," + lng;

                                    Intent maps_intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                                    startActivity(maps_intent);

                                    break;
                            }
                        }
                    });


                    rview_fav.setAdapter(favHostelsAdapter);
                } else {
                    tv_no_data.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
