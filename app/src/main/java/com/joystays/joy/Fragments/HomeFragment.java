package com.joystays.joy.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.gson.Gson;
import com.joystays.joy.Adapter.BannersAdapter;
import com.joystays.joy.Adapter.HomeHostelAdapter;
import com.joystays.joy.Adapter.HomeSuggestAdapter;
import com.joystays.joy.Adapter.RecomendedHostelbannersdapter;
import com.joystays.joy.Adapter.RecommendedHostelsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.activities.HomeActivity;
import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.activities.HostelSearchActivity;
import com.joystays.joy.base.BaseAbstractFragment;
import com.joystays.joy.mvp.contract.fragment.HomeFragContract;
import com.joystays.joy.mvp.presenter.fragment.HomFragImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.joystays.joy.pojos.UserBookingDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.GPSTracker;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static androidx.viewpager.widget.ViewPager.*;

public class HomeFragment extends BaseAbstractFragment<HomFragImpl> implements HomeFragContract.IView, APIResponseCallback {
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 20;
    ViewPager viewPager;
    private LinearLayout vllDost;
    private TextView nodat_tv;
    private com.joystays.joy.Adapter.HomeSlideAdapter HomeSlideAdapter;
    private TextView[] mdots;
    Context context;
    RecyclerView home_hostel_recycler, home_suggest_recycler;
    HomeHostelAdapter homeHostelAdapter;
    private RecommendedHostelsAdapter recommendedHostelsAdapter;
    HomeSuggestAdapter homeSuggestAdapter;
    LinearLayout linear_home_aftersearch, linar_home_beforesearch;
    private CircleImageView circle_image_boy, circle_image_girl;
    private List<NearByHostelsPojo.ResponseBean> hostelBean;
    private UserSession userSession;
    private String user_id, token;
    private String gender_type = "boys", laguages = "Telugu,English";
    private NestedScrollView nested_home;
    private UserBookingDetailsPojo userBookingDetailsPojo;
    private String search_filter = "";
    private String room_rating = "", owner_rating = "", overall_rating = "";
    private RatingBar rb_room_rating, rb_owner_rating, rb_overall;

    int currentPage = -1;
    RecomendedHostelbannersdapter recomendedHostelbannersdapter;


    private NearByHostelsPojo nearByHostelsPojo;

    String strAddress;


    public int[] slide_images = {
            R.drawable.dummy_image,
            R.drawable.dummy_image,
            R.drawable.dummy_image,
    };
    private int page_number = 0;
    private int selected_position;
    GPSTracker gpsTracker;
    private double latitude, longitude;
    private LocationRequest mLocationRequest;
    private AlertDialog alertDialog_menu;


    // String otherprjct = "AIzaSyAgDEVcq-w2BgTNdAQqjj67SHnj51yr0Ig";
    String otherprjct = "AIzaSyAZuaRON0_DtBElxleq-UOXOawpEdYKrjo";

    //  String str_cia = "AIzaSyBSaSzEI417kuNqInU-19QL1JhIr3nNX3M";
    private LatLng latLng;
    private double search_lat, search_lang;
    private static final int GRANT_LOC_ACCESS = 800;


    static final Integer GPS_SETTINGS = 0x7;

    private static final int REQUEST_ENABLE_GPS = 516;
    //  private GoogleApiClient client;
    PendingResult<LocationSettingsResult> result;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;

    private Location mylocation;
    Handler handler;

    String gpsonoroff = "1", data_refresh = "false";
    String clicksttus = "strat";
    private Dialog dialog;

    TextView tv_rv_txt;


    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    private void openDailogMenu() {
        LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.dialog_group_menu, null);

        alertDialog_menu = new androidx.appcompat.app.AlertDialog.Builder(context).create();
        alertDialog_menu.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog_menu.setView(view1);
        alertDialog_menu.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = alertDialog_menu.getWindow().getAttributes();
        wlp.gravity = Gravity.TOP | Gravity.END;
        alertDialog_menu.setCancelable(true);
        TextView tv_location, tv_name;
        tv_location = view1.findViewById(R.id.tv_location);
        tv_name = view1.findViewById(R.id.tv_name);


        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!Places.isInitialized()) {
                        Places.initialize(getActivity(), otherprjct);
                    }

                    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

                    Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).setCountry("IN").build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                  /*  Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(MapsActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);*/
                } catch (Exception e) {
                    // TODO: Handle the error.
                }
                alertDialog_menu.dismiss();
            }
        });
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HostelSearchActivity.class);
                intent.putExtra("lat", gpsTracker.getLatitude() + "");
                intent.putExtra("lng", gpsTracker.getLongitude() + "");
                startActivity(intent);

                alertDialog_menu.dismiss();
            }
        });
        alertDialog_menu.show();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        circle_image_boy = view.findViewById(R.id.circle_image_boy);
        circle_image_girl = view.findViewById(R.id.circle_image_girl);
        nested_home = view.findViewById(R.id.nested_home);

        linar_home_beforesearch = view.findViewById(R.id.linar_home_beforesearch);
        linear_home_aftersearch = view.findViewById(R.id.linear_home_aftersearch);

        hostelBean = new ArrayList<>();

        context = getActivity();

        /*client = new GoogleApiClient.Builder(getActivity())

                .addApi(LocationServices.API)
                .build();*/

        userSession = new UserSession(getActivity());
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");


        LinearLayout linear_address = (LinearLayout) ((Activity) getActivity()).findViewById(R.id.linear_address);
        linear_address.setVisibility(View.VISIBLE);


//        ImageView iv_gps = (ImageView) ((Activity) getActivity()).findViewById(R.id.iv_gps);
//        iv_gps.setVisibility(View.VISIBLE);

        View view_search = (View) ((Activity) getActivity()).findViewById(R.id.view_search);
        view_search.setVisibility(View.GONE);


        TextView et_search = ((Activity) getActivity()).findViewById(R.id.et_search);

        circle_image_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle_image_boy.setBackground(getResources().getDrawable(R.drawable.round_green_bg));
                circle_image_girl.setBackground(getResources().getDrawable(R.drawable.round_white_bg));
                gender_type = "boys";
                page_number = 0;

                if (clicksttus.equalsIgnoreCase("strat")) {
                    callNearByHostelsWSwithutlatlongs();

                } else {
                    callNearByHostelsWS();

                }


                if (hostelBean.isEmpty()) {
                    //  Toast.makeText(getActivity(), "Nothing to show", Toast.LENGTH_SHORT).show();

                } else {
                    homeHostelAdapter.notifyDataSetChanged();

                }
            }
        });
        circle_image_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle_image_boy.setBackground(getResources().getDrawable(R.drawable.round_white_bg));
                circle_image_girl.setBackground(getResources().getDrawable(R.drawable.round_green_bg));
                gender_type = "girls";
                callNearByHostelsWS();
                page_number = 0;

                if (clicksttus.equalsIgnoreCase("strat")) {
                    callNearByHostelsWSwithutlatlongs();

                } else {
                    callNearByHostelsWS();

                }


                if (hostelBean.isEmpty()) {
                    // Toast.makeText(getActivity(), "Nothing to show", Toast.LENGTH_SHORT).show();

                } else {
                    homeHostelAdapter.notifyDataSetChanged();

                }
            }
        });


        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDailogMenu();
            }
        });

        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.VISIBLE);

        ImageView nav_menu_img = (ImageView) ((Activity) getActivity()).findViewById(R.id.nav_menu_img);
        nav_menu_img.setImageResource(R.drawable.menu_icon_joy);


        viewPager = view.findViewById(R.id.homefrag_viewpager);
        home_hostel_recycler = view.findViewById(R.id.home_hostel_recycler);
        home_suggest_recycler = view.findViewById(R.id.home_suggest_recycler);
        nodat_tv = view.findViewById(R.id.nodat_tv);
        tv_rv_txt = view.findViewById(R.id.tv_rv_txt);

        callUserBookingDetailsWs();

        gpsTracker = new GPSTracker(getActivity());
        if (gpsTracker.canGetLocation()) {
            gpsTracker = new GPSTracker(getActivity());

            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            System.out.println("latitude" + latitude + "longitude" + longitude);
            callNearByHostelsWSwithutlatlongs();


        } else {

        }

    }


    private void openDialofForRating() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hotel_facilities_rating);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);

        LinearLayout linear_submit;

        rb_room_rating = dialog.findViewById(R.id.rb_room_rating);
        rb_owner_rating = dialog.findViewById(R.id.rb_owner_rating);
        rb_overall = dialog.findViewById(R.id.rb_overall);
        linear_submit = dialog.findViewById(R.id.linear_submit);


        rb_overall.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                overall_rating = String.valueOf(rating);

            }
        });
        rb_room_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                room_rating = String.valueOf(rating);
            }
        });
        rb_owner_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                owner_rating = String.valueOf(rating);
            }
        });
        linear_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (room_rating.isEmpty()) {
                    Toast.makeText(getActivity(), "Kindly provide room rating", Toast.LENGTH_SHORT).show();
                } else if (owner_rating.isEmpty()) {
                    Toast.makeText(getActivity(), "Kindly provide Property Manager Response rating", Toast.LENGTH_SHORT).show();
                } else if (overall_rating.isEmpty()) {
                    Toast.makeText(getActivity(), "Kindly provide Overall rating", Toast.LENGTH_SHORT).show();
                } else {
                    callSubmitRatingWS();
                }

            }
        });
        dialog.show();


    }

    private void callSubmitRatingWS() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", userSession.getUserDetails().get("hostel_id"));
        requestBody.put("ratings", overall_rating);
        requestBody.put("reviews", "");
        requestBody.put("room_id", userSession.getUserDetails().get("room_id"));
        requestBody.put("property_manager_response", owner_rating);
        requestBody.put("room_status", room_rating);
        presenter.submit_hotel_rating(context, this, requestBody);
    }

    private void callWSforRatingCheck() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);

        presenter.openPopUpRatings(context, this, requestBody);
    }


    private void callNearByHostelsWSwithutlatlongs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_type", gender_type);
       /* requestBody.put("lat", "17.387140");
        requestBody.put("lng", "78.491684");*/
        requestBody.put("lat", String.valueOf(gpsTracker.getLatitude()));
        requestBody.put("lng", String.valueOf(gpsTracker.getLongitude()));
        requestBody.put("count", String.valueOf(page_number));
        presenter.nearByHostels(context, this, requestBody);
    }

    private void callUserBookingDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_booking_details(getActivity(), this, requestBody);
    }

    private void callNearByHostelsWS() {
        gpsTracker = new GPSTracker(context);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_type", gender_type);
        requestBody.put("lat", String.valueOf(latitude));
        requestBody.put("lng", String.valueOf(longitude));
        //  requestBody.put("lat", String.valueOf(gpsTracker.getLatitude()));
//        requestBody.put("lng", String.valueOf(gpsTracker.getLongitude()));
        requestBody.put("count", String.valueOf(page_number));
        presenter.nearByHostels(context, this, requestBody);


        System.out.println("requestBody" + requestBody);
    }

    private void callFavWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostelBean.get(selected_position).getId());
        presenter.setFavourite(getActivity(), this, requestBody);
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        return view;
    }

    public void viewPagerButtons() {


        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (vllDost != null && vllDost.getTag() != null) {
                    ((ImageView) vllDost.getTag()).setImageResource(R.drawable.onboard_circle_lite);
                    ((ImageView) vllDost.getChildAt(position)).setImageResource(R.drawable.onboard_circle_white);
                    vllDost.setTag(vllDost.getChildAt(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDotBar(final Integer length) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);
        param.setMargins(5, 0, 5, 0);
        vllDost.removeAllViews();
        for (int i = 0; i < length; i++) {
            int i2;
            ImageView img = new ImageView(this.getActivity());
/*LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(5,5);
img.setLayoutParams(ivParams);*/
            if (i == 0) {
                i2 = R.drawable.onboard_circle_lite;
            } else {
                i2 = R.drawable.onboard_circle_white;
            }
            img.setImageResource(i2);
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            vllDost.addView(img, param);
            if (i == 0) {
                vllDost.setTag(img);
            }
        }
    }

    private String getCompleteAddressString(double latitude, double longitude) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        return address;
    }

    @Override
    public void setPresenter() {
        presenter = new HomFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.NEAR_HOSTELS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    nodat_tv.setVisibility(View.GONE);
                    home_suggest_recycler.setVisibility(View.VISIBLE);
                    home_hostel_recycler.setVisibility(View.VISIBLE);
                    if (page_number == 0) {
                        hostelBean = new ArrayList<>();
                    }
                    nearByHostelsPojo = new Gson().fromJson(responseString, NearByHostelsPojo.class);
                    hostelBean.addAll(nearByHostelsPojo.getResponse());
                    int count = nearByHostelsPojo.getResponse().size();
                    int position = hostelBean.size();
                    if (page_number == 0) {
                        data_refresh = "true";

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                        home_hostel_recycler.setLayoutManager(mLayoutManager);
                        homeHostelAdapter = new HomeHostelAdapter(this.getActivity(), hostelBean);
                        home_hostel_recycler.setAdapter(homeHostelAdapter);
                    } else {
                        homeHostelAdapter.notifyItemRangeChanged(position, count);
                        data_refresh = "true";

                    }

                    nested_home.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                        @Override
                        public void onScrollChanged() {
                            View view = (View) nested_home.getChildAt(nested_home.getChildCount() - 1);

                            int diff = (view.getBottom() - (nested_home.getHeight() + nested_home
                                    .getScrollY()));
                            if (diff == 0) {
                                // your pagination code

                                if (data_refresh.equalsIgnoreCase("true")) {
                                    data_refresh = "false";
                                    page_number++;
                                    callNearByHostelsWS();
                                }

                            }
                        }
                    });
                    homeHostelAdapter.setOnItemClickListener(new HomeHostelAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_hostel_item:

                                    Intent intent = new Intent(getActivity(), HostelDetailsActivity.class);
                                    intent.putExtra("hostel_id", hostelBean.get(position).getId());
                                    intent.putExtra("ac_status", hostelBean.get(position).getAc());
                                    intent.putExtra("non_ac_status", hostelBean.get(position).getNon_ac());
                                    intent.putExtra("gender_type", gender_type);
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_left, R.anim.slide_out_right);
                                    getActivity().startActivity(intent, options.toBundle());


                                    break;

                                case R.id.tv_availability:

                                    Intent intentdata = new Intent(getActivity(), HostelDetailsActivity.class);
                                    intentdata.putExtra("hostel_id", hostelBean.get(position).getId());
                                    intentdata.putExtra("ac_status", hostelBean.get(position).getAc());
                                    intentdata.putExtra("non_ac_status", hostelBean.get(position).getNon_ac());
                                    intentdata.putExtra("gender_type", gender_type);
                                    ActivityOptions options1 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_left, R.anim.slide_out_right);
                                    getActivity().startActivity(intentdata, options1.toBundle());


                                    break;
                                case R.id.iv_fav:
                                    callFavWs();
                                    break;

                                case R.id.ll_direction:
                                    String lat = hostelBean.get(position).getLat();
                                    String lng = hostelBean.get(position).getLng();
                                    String slat = String.valueOf(latitude);
                                    String slng = String.valueOf(longitude);
                                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + slat + "," + slng + "&daddr=" + lat + "," + lng;

                                    Intent maps_intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                                    startActivity(maps_intent);
                                    break;
                            }

                        }
                    });
                    if (page_number == 0) {
                      /*  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        home_suggest_recycler.setLayoutManager(mLayoutManager);
                        recommendedHostelsAdapter = new RecommendedHostelsAdapter(getActivity(), nearByHostelsPojo.getRecommended_hostels());
                        home_suggest_recycler.setAdapter(recommendedHostelsAdapter);
*/
                        if (!nearByHostelsPojo.getRecommended_hostels().isEmpty()) {
                            viewPager.setVisibility(VISIBLE);
                            tv_rv_txt.setVisibility(VISIBLE);

                            recomendedHostelbannersdapter = new RecomendedHostelbannersdapter(getActivity(), nearByHostelsPojo.getRecommended_hostels(), gender_type);
                            viewPager.setAdapter(recomendedHostelbannersdapter);
                            viewPager.setFocusable(true);
                            viewPager.setClipToPadding(false);
                            viewPager.setPadding(0, 0, 170, 0);
                            viewPager.setPageMargin(10);

                            final Handler handler = new Handler();
                            TimerTask timer = new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            int numPages = viewPager.getAdapter().getCount();
                                            currentPage = (currentPage + 1) % numPages;
                                            viewPager.setCurrentItem(currentPage);
                                        }
                                    });
                                }
                            };


                            Timer time = new Timer();
                            time.schedule(timer, 0, 5000);

                            recommendedHostelsAdapter.setOnItemClickListener(new RecommendedHostelsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.slide_image:
                                            Intent intent = new Intent(getActivity(), HostelDetailsActivity.class);
                                            intent.putExtra("hostel_id", nearByHostelsPojo.getRecommended_hostels().get(position).getId());
                                            intent.putExtra("ac_status", nearByHostelsPojo.getRecommended_hostels().get(position).getAc());
                                            intent.putExtra("gender_type", gender_type);
                                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_left, R.anim.slide_out_right);
                                            getActivity().startActivity(intent, options.toBundle());
                                            break;
                                    }
                                }
                            });
                        }else{
                            viewPager.setVisibility(GONE);
                            tv_rv_txt.setVisibility(GONE);
                        }

                    } else {

                    }


                } else {
                    if (page_number == 0) {
                        home_suggest_recycler.setVisibility(View.GONE);
                        home_hostel_recycler.setVisibility(View.GONE);
                        nodat_tv.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(GONE);
                        tv_rv_txt.setVisibility(GONE);
                    }

                    Toast.makeText(context, "No more Hostels", Toast.LENGTH_SHORT).show();
                }
            } else if (NetworkConstants.RequestCode.SET_FAV == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    if (jsonObject.optString("message").equalsIgnoreCase("Added to favourite!")) {
                        hostelBean.get(selected_position).setFavourite("yes");
                        homeHostelAdapter.notifyItemChanged(selected_position);
                        Toast.makeText(getActivity(), "Hostel added to favourites", Toast.LENGTH_SHORT).show();
                    } else {
                        hostelBean.get(selected_position).setFavourite("no");
                        Toast.makeText(getActivity(), "Removed from favourites", Toast.LENGTH_SHORT).show();
                        homeHostelAdapter.notifyItemChanged(selected_position);
                    }
                } else {
                    Toast.makeText(context, "Something wrong ", Toast.LENGTH_SHORT).show();
                }
            } else if (NetworkConstants.RequestCode.RATING_CHECK == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    if (userBookingDetailsPojo.getResponse().getStatus().equalsIgnoreCase("approved")) {
                        openDialofForRating();
                    }else{

                    }
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_RATING == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Toast.makeText(getActivity(), "Thank you for your valuable ratings", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            } else if (NetworkConstants.RequestCode.USER_BOOKING_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    userBookingDetailsPojo = new Gson().fromJson(responseString, UserBookingDetailsPojo.class);
                    String package_type = userBookingDetailsPojo.getResponse().getPackage_type();
                    Variables.packagetype = package_type;



                    if (userBookingDetailsPojo.getResponse().getOwner_details().getLanguages() != null) {
                        laguages = userBookingDetailsPojo.getResponse().getOwner_details().getLanguages();
                    } else {
                    }

                    if (userBookingDetailsPojo.getResponse().getStatus().equalsIgnoreCase("approved")) {
                        userSession.storeHostel_details("yes",
                                userBookingDetailsPojo.getResponse().getHostel_id(),
                                userBookingDetailsPojo.getResponse().getOwner_id(),
                                userBookingDetailsPojo.getResponse().getFloor(),
                                userBookingDetailsPojo.getResponse().getRoom_id(),
                                userBookingDetailsPojo.getResponse().getBed_id(),
                                userBookingDetailsPojo.getResponse().getShare(),
                                userBookingDetailsPojo.getResponse().getBed_name(),
                                userBookingDetailsPojo.getResponse().getRoom_name(),
                                userBookingDetailsPojo.getResponse().getOwner_details().getName(),
                                userBookingDetailsPojo.getResponse().getOwner_details().getMobile(),
                                userBookingDetailsPojo.getResponse().getOwner_details().getLanguages(),
                                userBookingDetailsPojo.getResponse().getOwner_details().getProfile_pic(),
                                userBookingDetailsPojo.getResponse().getPresent_booking_id());
                        System.out.println("gjgfkfjl" + userBookingDetailsPojo.getResponse().getPresent_booking_id());
                    }

                    callWSforRatingCheck();


                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("reculycode" + requestCode + "resultCode" + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_SETTINGS) {
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!isGpsEnabled) {
                    //  getMyLocation();
                    Toast.makeText(getActivity(), "permissions", Toast.LENGTH_SHORT).show();
                    //  showSettingsAlert();
                } else {
                    gpsTracker = new GPSTracker(getActivity());
                    latitude = (gpsTracker.getLatitude());
                    longitude = (gpsTracker.getLongitude());
                    Toast.makeText(getActivity(), "permissions", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), latitude, Toast.LENGTH_SHORT).show();


                    System.out.println("latitude longitude" + gpsTracker.getLatitude() + gpsTracker.getLongitude());
                }
            } else if (requestCode == REQUEST_CHECK_SETTINGS_GPS) {
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        handler = new Handler();
                        gpsonoroff = "2";
                        //   getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }

            } else if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {

                    try {


                        final Place place = Autocomplete.getPlaceFromIntent(data);
                        // final Place place = PlaceAutocomplete.getPlace(this, data);
                        Log.e("onActivityResult", "Place: " + place.getAddress());

                        strAddress = String.valueOf(place.getAddress());
                        System.out.println("strAddress" + strAddress);

                        LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                        latitude = place.getLatLng().latitude;
                        longitude = place.getLatLng().longitude;
                        System.out.println("latjjjjjjj" + latitude + "latng" + longitude);
                        clicksttus = "frmlocationserch";
                        search_filter = "";
                        page_number = 0;
                        callNearByHostelsWS();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //txt_Address.setText(strAddress);

                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                    // TODO: Handle the error.
                    Log.e("onActivityResult", String.valueOf(PlaceAutocomplete.getStatus(getActivity(), data)));
                    System.out.println("onActivityResult" + status.getStatus());

                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
            }
        }
    }


}



