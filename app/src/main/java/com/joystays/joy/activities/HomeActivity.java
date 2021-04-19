package com.joystays.joy.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.joystays.joy.Adapter.HomeHostelAdapter;
import com.joystays.joy.Adapter.RecommendedHostelsAdapter;
import com.joystays.joy.FoodPollActivity;
import com.joystays.joy.Fragments.BookingFragment;
import com.joystays.joy.Fragments.FavoriteFragment;
import com.joystays.joy.Fragments.HomeFragment;
import com.joystays.joy.Fragments.MyHostelFragment;
import com.joystays.joy.Fragments.SearchFragment;
import com.joystays.joy.Fragments.WalletFragment;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.customfonts.CustomTextViewNormal;
import com.joystays.joy.mvp.contract.activity.HomeActivityContract;
import com.joystays.joy.mvp.presenter.activity.HomeActivityImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.LoginPojo;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.joystays.joy.pojos.UserBookingDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.GPSTracker;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends BaseAbstractActivity<HomeActivityImpl> implements HomeActivityContract.IView, View.OnClickListener, APIResponseCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    private LinearLayout linear_home, linear_search, linear_favorite, linear_booking, linear_wallet, linear_search_gps;
    private ImageView iv_home, iv_search, iv_favorite, iv_booking, iv_wallet, iv_chat;
    private ImageView iv_nav_profile;
    private CustomTextViewNormal tv_home, tv_search, tv_favorite, tv_booking, tv_wallet;
    private ImageView nav_menu_img;
    private DrawerLayout drawer_layout;
    private ImageView iv_notification;
    private RelativeLayout nav_rl_invite, nav_rl_complaint, nav_rl_bookingstatus,
            nav_rl_changeroom, nav_rl_transactions, nav_rl_terms, nav_rl_vacant,
            nav_rl_about, nav_rl_rate, nav_rl_logout, profile_rl, nav_rl_foodpolling;
    private UserSession userSession;
    private String refreshedToken, approve_status = "";
    GPSTracker gpsTracker;
    private double latitude, longitude;
    private String isApproved = "";
    TextView et_search;
    private String str_image, str_name, number, password;
    private TextView txtProfilename;
    private String user_id, token, laguages = "Telugu,English";
    private UserBookingDetailsPojo userBookingDetailsPojo;

    LoginPojo loginPojo;


    static final Integer GPS_SETTINGS = 0x7;

    private static final int REQUEST_ENABLE_GPS = 516;
    private LocationRequest mLocationRequest;
    private GoogleApiClient client;
    PendingResult<LocationSettingsResult> result;


    private static final int GRANT_LOC_ACCESS = 800;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;

    private Location mylocation;
    Handler handler;

    String gpsonoroff = "1";


    private BroadcastReceiver gpsSwitchStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (LocationManager.PROVIDERS_CHANGED_ACTION.equals(intent.getAction())) {

                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (isGpsEnabled || isNetworkEnabled) {
                    Toast.makeText(context, "isGpsEnabled", Toast.LENGTH_SHORT).show();
                    // Handle Location turned ON
                } else {
                    Toast.makeText(context, "dffgdgd", Toast.LENGTH_SHORT).show();
                    //askForGPS();

                    displayLocationSettingsRequest(context);
                    // Handle Location turned OFF
                    // getMyLocation();
                }
            }
        }
    };

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {

        }

    }

    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                // .enableAutoManage(this, 0, this)
                // .addConnectionCallbacks(this)
                //  .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_SETTINGS) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!isGpsEnabled) {
                    //  getMyLocation();
                    Toast.makeText(context, "permissions", Toast.LENGTH_SHORT).show();
                    //  showSettingsAlert();
                } else {
                    gpsTracker = new GPSTracker(HomeActivity.this);
                    latitude = (gpsTracker.getLatitude());
                    longitude = (gpsTracker.getLongitude());
                    Toast.makeText(context, "permissions" + gpsTracker.getLatitude(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this, HomeActivity.class);
                    i.putExtra("to", "home");
                    startActivity(i);
                    finish();
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

            }


        }
    }


    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("dfsd", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("htfhfh", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(HomeActivity.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("jjjgjh", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("gdfgdf", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }


    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        presenter.start();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        userSession = new UserSession(this);




        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        str_image = userSession.getUserDetails().get("image");
        str_name = userSession.getUserDetails().get("name");
        // isApproved = userSession.getUserDetails().get("approved");
        number = userSession.getUserDetails().get("mobileno");
        password = userSession.getUserDetails().get("password");

        //  final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        linear_search_gps = findViewById(R.id.linear_search_gps);
        nav_rl_invite = findViewById(R.id.nav_rl_invite);
        nav_rl_complaint = findViewById(R.id.nav_rl_complaint);
        nav_rl_bookingstatus = findViewById(R.id.nav_rl_bookingstatus);
        nav_rl_changeroom = findViewById(R.id.nav_rl_changeroom);
        nav_rl_transactions = findViewById(R.id.nav_rl_transactions);
        nav_rl_terms = findViewById(R.id.nav_rl_terms);
        nav_rl_vacant = findViewById(R.id.nav_rl_vacant);
        nav_rl_about = findViewById(R.id.nav_rl_about);
        nav_rl_logout = findViewById(R.id.nav_rl_logout);
        profile_rl = findViewById(R.id.profile_rl);
        nav_rl_rate = findViewById(R.id.nav_rl_rate);
        et_search = findViewById(R.id.et_search);
        nav_rl_foodpolling = findViewById(R.id.nav_rl_foodpolling);
        nav_rl_foodpolling.setVisibility(View.GONE);
        iv_nav_profile = findViewById(R.id.iv_nav_profile);
        gpsTracker = new GPSTracker(context);
        tv_favorite = findViewById(R.id.tv_favorite);
        linear_home = findViewById(R.id.linear_home);
        linear_search = findViewById(R.id.linear_search);
        linear_favorite = findViewById(R.id.linear_favorite);
        linear_booking = findViewById(R.id.linear_booking);
        linear_wallet = findViewById(R.id.linear_wallet);
        iv_chat = findViewById(R.id.iv_chat);

        iv_home = findViewById(R.id.iv_home);
        iv_search = findViewById(R.id.iv_search);
        iv_favorite = findViewById(R.id.iv_favorite);
        iv_booking = findViewById(R.id.iv_booking);
        iv_wallet = findViewById(R.id.iv_wallet);

        tv_home = findViewById(R.id.tv_home);
        tv_search = findViewById(R.id.tv_search);

        tv_booking = findViewById(R.id.tv_booking);
        tv_wallet = findViewById(R.id.tv_wallet);
        iv_nav_profile = findViewById(R.id.iv_nav_profile);
        txtProfilename = findViewById(R.id.txtProfilename);
        nav_menu_img = findViewById(R.id.nav_menu_img);
        drawer_layout = findViewById(R.id.drawer_layout);


        Intent intent = getIntent();
        if (intent == null) {
            fragment = new HomeFragment();
            switchFragment(fragment);
        } else if (intent.getStringExtra("to").equalsIgnoreCase("myHostel")) {
            fragment = new MyHostelFragment();
            switchFragment(fragment);
            iv_home.setImageResource(R.drawable.menu_home_icon_black);
            tv_home.setTextColor(getResources().getColor(R.color.text_gray));

            iv_search.setImageResource(R.drawable.offers_grey);
            tv_search.setTextColor(getResources().getColor(R.color.text_gray));
            tv_search.setText("Offers");

            iv_favorite.setImageResource(R.drawable.menu_favorite_icon_red);
            tv_favorite.setTextColor(getResources().getColor(R.color.text_red));

            iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
            tv_booking.setTextColor(getResources().getColor(R.color.text_gray));

            iv_wallet.setImageResource(R.drawable.menu_wallet_icon_black);
            tv_wallet.setTextColor(getResources().getColor(R.color.text_gray));
        } else if (intent.getStringExtra("to").equalsIgnoreCase("wallet")) {

            fragment = new WalletFragment();
            switchFragment(fragment);

            iv_home.setImageResource(R.drawable.menu_home_icon_black);
            tv_home.setTextColor(getResources().getColor(R.color.text_gray));

            iv_search.setImageResource(R.drawable.offers_grey);
            tv_search.setTextColor(getResources().getColor(R.color.text_gray));
            tv_search.setText("Offers");

            iv_favorite.setImageResource(R.drawable.menu_favorite_icon_black);
            tv_favorite.setTextColor(getResources().getColor(R.color.text_gray));

            iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
            tv_booking.setTextColor(getResources().getColor(R.color.text_gray));

            iv_wallet.setImageResource(R.drawable.menu_wallet_icon_red);
            tv_wallet.setTextColor(getResources().getColor(R.color.text_red));

        } else {
            fragment = new HomeFragment();

            switchFragment(fragment);

        }

        setUpGClient();
        //  IntentFilter filter = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
        //  filter.addAction(Intent.ACTION_PROVIDER_CHANGED);
        //  registerReceiver(gpsSwitchStateReceiver, filter);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();


        callLoginWs();
        callUserBookingDetailsWs();

        txtProfilename.setText(str_name);
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + str_image)
                .error(R.drawable.profile_png_image)
                .into(iv_nav_profile);
        /*if (isApproved.equalsIgnoreCase("yes")) {
            nav_rl_rate.setVisibility(View.VISIBLE);
            nav_rl_vacant.setVisibility(View.VISIBLE);

            nav_rl_changeroom.setVisibility(View.VISIBLE);
            nav_rl_complaint.setVisibility(View.VISIBLE);
            //nav_rl_foodpolling.setVisibility(View.VISIBLE);
            tv_favorite.setText("My Property");
        } else {
            nav_rl_rate.setVisibility(View.GONE);
            nav_rl_vacant.setVisibility(View.GONE);
            nav_rl_changeroom.setVisibility(View.GONE);
            nav_rl_complaint.setVisibility(View.GONE);
            nav_rl_foodpolling.setVisibility(View.GONE);
            tv_favorite.setText("Favourites");
        }
*/
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // AIzaSyD--P3IM3M1g7UFJehbmcylkiPasOMxUgE    serverkey of user

        Log.d("firebase_token", refreshedToken);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("android_token", refreshedToken);
        requestBody.put("user_id", user_id);
        requestBody.put("user_type", "user");


        presenter.update_token(HomeActivity.this, this, requestBody);

        gpsTracker = new GPSTracker(context);
       /* if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

        } else {
            //  gpsTracker.showSettingsAlert();
            displayLocationSettingsRequest(context);

            //  showSettingsAlert();
        }*/
        profile_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));

            }
        });

        iv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));

            }
        });
        nav_rl_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                //   startActivity(new Intent(HomeActivity.this, GiveRateUsActivity.class));

            }
        });

        /*nav_rl_foodpolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FoodPollActivity.class));
            }
        });*/
        nav_rl_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                alertDialogBuilder.setMessage("Are you sure, You want to Logout?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                userSession.logoutUser();
                                //Clear the application data to generate the new device token
                                // MyApplication.getInstance().clearApplicationData();
                                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


        nav_rl_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
            }
        });


        nav_rl_vacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WanttoVacantActivity.class));
            }
        });


        nav_rl_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TermsAndConditionActivity.class));
            }
        });


        nav_rl_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TransactionActivity.class));
            }
        });


        nav_rl_changeroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChangeRoomActivity.class));
            }
        });


        nav_rl_bookingstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BookingStatusActivity.class));
            }
        });


        nav_rl_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OwnerComplaintActivity.class));
            }
        });

        nav_rl_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, InviteAndEarnActivity.class));
            }
        });
        iv_notification = findViewById(R.id.iv_notification);
        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });
        nav_menu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);
                }
            }
        });

        linear_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new HomeFragment();
                switchFragment(fragment);
                iv_home.setImageResource(R.drawable.menu_home_icon_red);
                tv_home.setTextColor(getResources().getColor(R.color.text_red));
                iv_search.setImageResource(R.drawable.offers_grey);
                tv_search.setTextColor(getResources().getColor(R.color.text_gray));
                tv_search.setText("Offers");
                iv_favorite.setImageResource(R.drawable.menu_favorite_icon_black);
                tv_favorite.setTextColor(getResources().getColor(R.color.text_gray));
                iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
                tv_booking.setTextColor(getResources().getColor(R.color.text_gray));
                iv_wallet.setImageResource(R.drawable.menu_wallet_icon_black);
                tv_wallet.setTextColor(getResources().getColor(R.color.text_gray));
            }
        });
        linear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SearchFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.menu_home_icon_black);
                tv_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_search.setImageResource(R.drawable.offer_icon_red);
                tv_search.setTextColor(getResources().getColor(R.color.text_red));

                iv_favorite.setImageResource(R.drawable.menu_favorite_icon_black);
                tv_favorite.setTextColor(getResources().getColor(R.color.text_gray));

                iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
                tv_booking.setTextColor(getResources().getColor(R.color.text_gray));

                iv_wallet.setImageResource(R.drawable.menu_wallet_icon_black);
                tv_wallet.setTextColor(getResources().getColor(R.color.text_gray));

            }
        });
        linear_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (isApproved.equalsIgnoreCase("yes")) {
                    fragment = new MyHostelFragment();
                    switchFragment(fragment);
                } else {
                    fragment = new FavoriteFragment();
                    switchFragment(fragment);
                }
                iv_home.setImageResource(R.drawable.menu_home_icon_black);
                tv_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_search.setImageResource(R.drawable.offers_grey);
                tv_search.setTextColor(getResources().getColor(R.color.text_gray));
                tv_search.setText("Offers");

                iv_favorite.setImageResource(R.drawable.menu_favorite_icon_red);
                tv_favorite.setTextColor(getResources().getColor(R.color.text_red));

                iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
                tv_booking.setTextColor(getResources().getColor(R.color.text_gray));

                iv_wallet.setImageResource(R.drawable.menu_wallet_icon_black);
                tv_wallet.setTextColor(getResources().getColor(R.color.text_gray));
            }
        });
        linear_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new BookingFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.menu_home_icon_black);
                tv_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_search.setImageResource(R.drawable.offers_grey);
                tv_search.setTextColor(getResources().getColor(R.color.text_gray));
                tv_search.setText("Offers");

                iv_favorite.setImageResource(R.drawable.menu_favorite_icon_black);
                tv_favorite.setTextColor(getResources().getColor(R.color.text_gray));

                iv_booking.setImageResource(R.drawable.menu_booking_icon_red);
                tv_booking.setTextColor(getResources().getColor(R.color.text_red));

                iv_wallet.setImageResource(R.drawable.menu_wallet_icon_black);
                tv_wallet.setTextColor(getResources().getColor(R.color.text_gray));

            }
        });
        linear_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new WalletFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.menu_home_icon_black);
                tv_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_search.setImageResource(R.drawable.offers_grey);
                tv_search.setTextColor(getResources().getColor(R.color.text_gray));
                tv_search.setText("Offers");

                iv_favorite.setImageResource(R.drawable.menu_favorite_icon_black);
                tv_favorite.setTextColor(getResources().getColor(R.color.text_gray));

                iv_booking.setImageResource(R.drawable.menu_booking_icon_black);
                tv_booking.setTextColor(getResources().getColor(R.color.text_gray));

                iv_wallet.setImageResource(R.drawable.menu_wallet_icon_red);
                tv_wallet.setTextColor(getResources().getColor(R.color.text_red));

            }
        });
    }


    private void callLoginWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile", number);
        requestBody.put("password", password);
        presenter.user_login(HomeActivity.this, this, requestBody);
    }


    private void callUserBookingDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_booking_details(HomeActivity.this, this, requestBody);

    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        unregisterReceiver(gpsSwitchStateReceiver);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
/*
    private void askForGPS() {
        client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        result = LocationServices.SettingsApi.checkLocationSettings(client, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(HomeActivity.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }
*/


    @Override
    public void onBackPressed() {
        // super.onBackPressed();


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        alertDialogBuilder.setMessage("Are you sure, You want to Exit?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_home, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new HomeActivityImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, HomeActivity.this);

            } else if (NetworkConstants.RequestCode.USER_BOOKING_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    userBookingDetailsPojo = new Gson().fromJson(responseString, UserBookingDetailsPojo.class);


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

                } else {

                }
            } else if (NetworkConstants.RequestCode.UPDATE_TOKEN == requestId) {
                String message = jsonObject.getString("message");
                if (jsonObject.optBoolean("status") == true) {

                    //  Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                } else {
                    // Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                }


            } else if (NetworkConstants.RequestCode.USER_LOGIN == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //  Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                    loginPojo = new Gson().fromJson(responseString, LoginPojo.class);
                    String bed_confromred = loginPojo.getResponse().getBed_confirmed();
                    String referal_codeeee = loginPojo.getResponse().getRefferal_code();
                    Variables.referal_code = referal_codeeee;

                    userSession.StoreUserDetails(loginPojo.getResponse().getId(),
                            loginPojo.getResponse().getName(),
                            loginPojo.getResponse().getMobile(),
                            loginPojo.getResponse().getEmail_id(),
                            loginPojo.getResponse().getBed_confirmed(),
                            loginPojo.getToken(), loginPojo.getResponse().getProfile_pic(), password);

                    if (bed_confromred.equalsIgnoreCase("no")) {

                        nav_rl_rate.setVisibility(View.GONE);
                        nav_rl_vacant.setVisibility(View.GONE);
                        nav_rl_changeroom.setVisibility(View.GONE);
                        nav_rl_complaint.setVisibility(View.GONE);
                        nav_rl_foodpolling.setVisibility(View.GONE);
                        tv_favorite.setText("Favourites");

                    } else {

                        if (loginPojo.getResponse().getBooking().equalsIgnoreCase("done")) {

                            if (loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages() != null) {
                                laguages = loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages();

                            }


                            if (loginPojo.getResponse().getBooking_details().getStatus().equalsIgnoreCase("approved") || loginPojo.getResponse().getBooking_details().getStatus().equalsIgnoreCase("vacated")) {
                                isApproved = "yes";

                                userSession.storeHostel_details("yes",
                                        loginPojo.getResponse().getBooking_details().getHostel_id(),
                                        loginPojo.getResponse().getBooking_details().getOwner_id(),
                                        loginPojo.getResponse().getBooking_details().getFloor(),
                                        loginPojo.getResponse().getBooking_details().getRoom_id(),
                                        loginPojo.getResponse().getBooking_details().getBed_id(),
                                        loginPojo.getResponse().getBooking_details().getShare(),
                                        loginPojo.getResponse().getBooking_details().getBed_name(),
                                        loginPojo.getResponse().getBooking_details().getRoom_name(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getName(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getMobile(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages(),
                                        loginPojo.getResponse().getBooking_details().getOwner_details().getProfile_pic(),
                                        loginPojo.getResponse().getBooking_details().getPresent_booking_id());


                            } else {
                                isApproved = "no";


                            }
                        } else {

                            userSession.storeHostel_details("no", "", "", "", "", "",
                                    "", "", "", "", "", "", "", ""
                            );


                        }


                        if (isApproved.equalsIgnoreCase("yes")) {
                            nav_rl_rate.setVisibility(View.VISIBLE);
                            nav_rl_vacant.setVisibility(View.VISIBLE);
                            nav_rl_changeroom.setVisibility(View.VISIBLE);
                            nav_rl_complaint.setVisibility(View.VISIBLE);
                            //nav_rl_foodpolling.setVisibility(View.VISIBLE);
                            tv_favorite.setText("My Property");
                        } else {
                            nav_rl_rate.setVisibility(View.GONE);
                            nav_rl_vacant.setVisibility(View.GONE);
                            nav_rl_changeroom.setVisibility(View.GONE);
                            nav_rl_complaint.setVisibility(View.GONE);
                            nav_rl_foodpolling.setVisibility(View.GONE);
                            tv_favorite.setText("Favourites");
                        }

                    }

                } else {

                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    userSession.logoutUser();
                    Intent i = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }


            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    HomeActivity.this,
                                    GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

