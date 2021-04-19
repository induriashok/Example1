package com.joystays.joy.activities;

import android.app.ActivityOptions;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;

import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.gson.Gson;
import com.joystays.joy.Adapter.BannersAdapter;
import com.joystays.joy.Adapter.FecilitiesAdapter;
import com.joystays.joy.Adapter.HostelPricesAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.HosteldetailsContract;
import com.joystays.joy.mvp.presenter.activity.HostelDetailsImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.HostelDetailsPojo;
import com.joystays.joy.pojos.HostelPricesPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.GPSTracker;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

import org.intellij.lang.annotations.Language;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class HostelDetailsActivity extends BaseAbstractActivity<HostelDetailsImpl> implements HosteldetailsContract.IView, APIResponseCallback {
    private ImageView iv_call, back_arrow, close, iv_selectdate, iv_fav, iv_banner, iv_owner_pic;
    private LinearLayout location_linear, linear_rating, linear_booknow, ll_status;
    private TextView final_count, tv_ok, tv_hostel_name, tv_days,
            tv_hostel_code, tv_rating, tv_rating_status, tv_rating_count, tv_hostel_address, tv_checkout, tv_checkin;
    private LinearLayout linear_ac_rooms, linear_non_ac_rooms, linear_daily_basis, linear_monthly_basis, ll_data;
    private TextView ctvn_ac_rooms, ctvn_non_ac_rooms, ctvn_daily_basis, page_count, ctvn_monthly_basis, nodata_tv, tv_noprices, package_days_tv;
    private String hostel_id, user_id, token, owner_languages = "Telugu,English";
    private UserSession userSession;
    private LinearLayout ll_basis;
    private String ac_status = "non_ac", package_status = "daily", ac_availability;
    private HostelDetailsPojo hostelDetailsPojo;
    private RecyclerView rview_fecilities, rview_prices;
    private FecilitiesAdapter fecilitiesAdapter;
    private HostelPricesAdapter pricesAdapter;
    private TableLayout tb_prices;
    private String gender_type, check_in = "", check_out, new_check_in, non_ac_availability;
    private LinearLayout ll_check_out, ll_check_in, rating_complete;
    private HostelPricesPojo hostelPricesPojo;
    private TextView tv_owner_name, tv_owner_languages;
    private String hostel_ac_availability = "", str_icon, str_descrption;
    int current_day, current_month, current_year, check_in_day, check_in_month, check_in_year, check_out_day, check_out_month, check_out_year;
    private Dialog dialog2;
    private String ac_ratings, bfast_ratings, hostelstaff_ratings, tv_ratings, washroom_ratings, wifi_ratings, rating = "", hostel_name;
    private GPSTracker gpsTracker;
    private Double latitude, longitude;
    private String from_date_status = "false";
    private String compare_from_to_dates = "";
    private String calender_check_in = "no", calender_check_out = "no", check_status = "";
    private ViewPager editors_viewpager;
    private BannersAdapter bannersAdapter;
    AlertDialog.Builder builder, builder2;
    private Dialog dialog;


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

        userSession = new UserSession(HostelDetailsActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        owner_languages = userSession.getUserDetails().get("owner_languages");

        Intent intent = getIntent();
        hostel_id = intent.getStringExtra("hostel_id");
        ac_availability = intent.getStringExtra("ac_status");
        gender_type = intent.getStringExtra("gender_type");
        non_ac_availability = intent.getStringExtra("non_ac_status");
        System.out.println("ac_availability" + ac_availability);


        gpsTracker = new GPSTracker(HostelDetailsActivity.this);
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            Toast.makeText(HostelDetailsActivity.this, "Turn on Location", Toast.LENGTH_SHORT).show();
        }


        linear_booknow = findViewById(R.id.linear_bknow);
        iv_selectdate = findViewById(R.id.iv_selectdate);
        back_arrow = findViewById(R.id.back_arrow);
        iv_call = findViewById(R.id.iv_call);
        location_linear = findViewById(R.id.location_linear);
        linear_rating = findViewById(R.id.linear_rating);
        ll_basis = findViewById(R.id.ll_basis);
        tv_hostel_name = findViewById(R.id.tv_hostel_name);
        tv_hostel_code = findViewById(R.id.tv_hostel_code);
        iv_fav = findViewById(R.id.iv_fav);
        tv_rating = findViewById(R.id.tv_rating);
        tv_rating_status = findViewById(R.id.tv_rating_status);
        tv_rating_count = findViewById(R.id.tv_rating_count);
        tv_hostel_address = findViewById(R.id.tv_hostel_address);
        rview_fecilities = findViewById(R.id.rview_fecilities);
        rview_prices = findViewById(R.id.rview_prices);
        tv_checkout = findViewById(R.id.tv_checkout);
        tv_checkin = findViewById(R.id.tv_checkin);
        ll_check_out = findViewById(R.id.ll_check_out);
        ll_check_in = findViewById(R.id.ll_check_in);
        iv_banner = findViewById(R.id.iv_banner);
        tv_owner_name = findViewById(R.id.tv_owner_name);
        tv_owner_languages = findViewById(R.id.tv_owner_languages);
        ll_status = findViewById(R.id.ll_status);
        ll_status.setVisibility(View.GONE);
        iv_owner_pic = findViewById(R.id.iv_owner_pic);
        rating_complete = findViewById(R.id.rating_complete);
        // ll_data = findViewById(R.id.ll_data);
        nodata_tv = findViewById(R.id.nodata_tv);
        tv_noprices = findViewById(R.id.tv_noprices);
        package_days_tv = findViewById(R.id.package_days_tv);
        page_count = findViewById(R.id.page_count);
        final_count = findViewById(R.id.final_count);
        tv_days = findViewById(R.id.tv_days);
        package_days_tv.setVisibility(View.GONE);
        tv_days.setVisibility(View.GONE);

        rview_fecilities.setLayoutManager(new GridLayoutManager(this, 4));
        tb_prices = findViewById(R.id.tb_prices);
        editors_viewpager = findViewById(R.id.editors_viewpager);

        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        current_day = day;
        current_month = month;
        current_year = year;

        check_in = year + "-" + (current_month + 1) + "-" + current_day;

        //    check_in = day + "-" + (month + 1) + "-" + year;
        // tv_checkin.setText(check_in);
        tv_checkin.setText(current_day + "-" + (current_month + 1) + "-" + current_year);

        if (month + 1 == 12) {
            check_out = 1 + "-" + 1 + "-" + (year + 1);
            tv_checkout.setText(check_out);
        } else {
            check_out = 1 + "-" + (month + 2) + "-" + year;
            tv_checkout.setText(check_out);
        }
        userSession = new UserSession(HostelDetailsActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");

        ctvn_ac_rooms = findViewById(R.id.ctvn_ac_rooms);
        ctvn_non_ac_rooms = findViewById(R.id.ctvn_non_ac_rooms);
        ctvn_daily_basis = findViewById(R.id.ctvn_daily_basis);
        ctvn_monthly_basis = findViewById(R.id.ctvn_monthly_basis);

        linear_monthly_basis = findViewById(R.id.linear_monthly_basis);
        linear_daily_basis = findViewById(R.id.linear_daily_basis);
        linear_non_ac_rooms = findViewById(R.id.linear_non_ac_rooms);
        linear_ac_rooms = findViewById(R.id.linear_ac_rooms);

        if (ac_availability.equalsIgnoreCase("yes")) {
            linear_ac_rooms.setVisibility(View.VISIBLE);
        } else {
            linear_ac_rooms.setVisibility(View.INVISIBLE);
        }

        if (non_ac_availability.equalsIgnoreCase("yes")) {
            linear_non_ac_rooms.setVisibility(View.VISIBLE);
        } else {
            linear_non_ac_rooms.setVisibility(View.INVISIBLE);
        }


        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + hostelDetailsPojo.getResponse().getOwner_details().getMobile();
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
        ll_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDisplayRatings();
            }
        });
        iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSetFavouriteWs();
            }
        });
        ll_check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calender_check_in = "yes";
                check_status = "check_in";

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HostelDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                check_in_day = dayOfMonth;
                                check_in_month = monthOfYear;
                                check_in_year = year;


                                tv_checkin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                check_in = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;


                                if (monthOfYear + 1 == 12) {
                                    tv_checkout.setText(1 + "-" + 1 + "-" + (year + 1));
                                    check_out = year + 1 + "-" + 1 + "-" + 1;
                                } else {
                                    tv_checkout.setText(1 + "-" + (monthOfYear + 2) + "-" + year);
                                    check_out = year + "-" + (monthOfYear + 2) + "-" + 1;

                                }

                                if (package_status.equalsIgnoreCase("daily")) {

                                    package_days_tv.setVisibility(View.VISIBLE);
                                    tv_days.setVisibility(View.VISIBLE);
                                    iscallDateComapare();
                                } else {

                                }


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                Calendar cal = Calendar.getInstance();
                Date today = cal.getTime();
                cal.add(Calendar.YEAR, 1);


                datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                datePickerDialog.show();


            }
        });


    /*    try {
            String string = new_check_in; //assuming input
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date dt = null;
            dt = sdf.parse(string);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.add(Calendar.MONTH, 1);  //adding a month directly - gives the start of next month.
            String firstDate = sdf.format(c.getTime());
            System.out.println("firstDate" + firstDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        ll_check_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                calender_check_out = "yes";
                check_status = "check_out";

                if (package_status.equalsIgnoreCase("monthly")) {
                    Toast.makeText(HostelDetailsActivity.this, "Dear user, you can't change checkout date for Monthly basis", Toast.LENGTH_LONG).show();
                } else {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(HostelDetailsActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {


                                    check_out_day = dayOfMonth;
                                    check_out_month = monthOfYear;
                                    check_out_year = year;


                                    tv_checkout.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    check_out = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;


                                    if (package_status.equalsIgnoreCase("daily")) {

                                        package_days_tv.setVisibility(View.VISIBLE);
                                        iscallDateComapare();

                                    } else {

                                    }


                                }
                            }, mYear, mMonth, mDay);

                    // }, check_in_year, check_in_month, check_in_day);

                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePickerDialog.show();
                }


            }
        });
        linear_ac_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_ac_rooms.setBackground(getResources().getDrawable(R.drawable.tarrif_green));
                linear_non_ac_rooms.setBackground(getResources().getDrawable(R.drawable.tarrif_gray));

                ctvn_ac_rooms.setTextColor(getResources().getColor(R.color.white));
                ctvn_non_ac_rooms.setTextColor(getResources().getColor(R.color.text_black));
                ll_basis.setVisibility(View.VISIBLE);
                ac_status = "ac";
                callHostelDetails();
            }
        });
        linear_non_ac_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_ac_rooms.setBackground(getResources().getDrawable(R.drawable.tarrif_gray));
                linear_non_ac_rooms.setBackground(getResources().getDrawable(R.drawable.tarrif_green));

                ctvn_ac_rooms.setTextColor(getResources().getColor(R.color.text_black));
                ctvn_non_ac_rooms.setTextColor(getResources().getColor(R.color.white));

                ll_basis.setVisibility(View.VISIBLE);
                ac_status = "non_ac";
                callHostelDetails();

            }
        });
        linear_daily_basis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear_daily_basis.setBackground(getResources().getDrawable(R.drawable.tarrif_green));
                linear_monthly_basis.setBackground(getResources().getDrawable(R.drawable.tarrif_gray));

                ctvn_daily_basis.setTextColor(getResources().getColor(R.color.white));
                ctvn_monthly_basis.setTextColor(getResources().getColor(R.color.text_black));
                package_status = "daily";
                ll_check_out.setClickable(true);

                package_days_tv.setVisibility(View.VISIBLE);
                callHostelDetails();

            }
        });

        linear_monthly_basis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear_daily_basis.setBackground(getResources().getDrawable(R.drawable.tarrif_gray));
                linear_monthly_basis.setBackground(getResources().getDrawable(R.drawable.tarrif_green));

                ctvn_daily_basis.setTextColor(getResources().getColor(R.color.text_black));
                ctvn_monthly_basis.setTextColor(getResources().getColor(R.color.white));
                package_days_tv.setVisibility(View.GONE);

                ll_check_out.setClickable(true);


                package_status = "monthly";
                callHostelDetails();
            }
        });

        linear_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_status = "book_now";
                //toaccept today also for dailybais
                if (calender_check_in.equalsIgnoreCase("no")) {
                    if (compare_from_to_dates.equalsIgnoreCase("false")) {
                        Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                    } else {
                        if (hostel_ac_availability.equalsIgnoreCase("yes")) {

                            String new_check_in = check_in;
                            System.out.println("zxhfgjmgh," + new_check_in);




                            Intent intent = new Intent(HostelDetailsActivity.this, SelectFloorandRoomActivity.class);
                            intent.putExtra("floor_count", hostelDetailsPojo.getResponse().getFloors().size());
                            intent.putExtra("hostel_id", hostel_id);
                            intent.putExtra("owner_id", hostelDetailsPojo.getResponse().getOwner_id());
                            intent.putExtra("owner_pic", hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic());
                            intent.putExtra("room_type", ac_status);
                            intent.putExtra("package_type", package_status);
/*                            intent.putExtra("from_date", check_in);
                            intent.putExtra("to_date", check_out);*/
                            intent.putExtra("from_date", tv_checkin.getText().toString());
                            intent.putExtra("to_date", tv_checkout.getText().toString());
                            intent.putExtra("gender_type", gender_type);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                            context.startActivity(intent, options.toBundle());
                            System.out.println("gyghj"+ac_status);
                            // finish();
                        } else {
                            Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (isvalidFromDate()) {
                        if (iscallDateComapare()) {
                            if (compare_from_to_dates.equalsIgnoreCase("false")) {
                                Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                            } else {
                                if (hostel_ac_availability.equalsIgnoreCase("yes")) {

                                    Intent intent = new Intent(HostelDetailsActivity.this, SelectFloorandRoomActivity.class);
                                    intent.putExtra("floor_count", hostelDetailsPojo.getResponse().getFloors().size());
                                    intent.putExtra("hostel_id", hostel_id);
                                    intent.putExtra("owner_id", hostelDetailsPojo.getResponse().getOwner_id());
                                    intent.putExtra("room_type", ac_status);
                                    intent.putExtra("owner_pic", hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic());
                                    intent.putExtra("package_type", package_status);
                                    intent.putExtra("from_date", check_in);
                                    intent.putExtra("to_date", check_out);
                                    intent.putExtra("gender_type", gender_type);

                                    //  intent.putExtra("share",  hostelDetailsPojo.getResponse()());
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                                    context.startActivity(intent, options.toBundle());
                                    System.out.println("gyghgggj"+ac_status);

                                    // finish();

                                } else {
                                    Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }

                    } else {

                    }
                }


                //intila code
            /*    if (isvalidFromDate()) {

                    if (package_status.equalsIgnoreCase("monthly") && calender_check_in.equalsIgnoreCase("no")) {
                        if (compare_from_to_dates.equalsIgnoreCase("false")) {
                            Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                        } else {
                            if (hostel_ac_availability.equalsIgnoreCase("yes")) {

                                String new_check_in = check_in;
                                System.out.println("zxhfgjmgh,"+new_check_in);

                                check_in="";

                                Intent intent = new Intent(HostelDetailsActivity.this, SelectFloorandRoomActivity.class);
                                intent.putExtra("floor_count", hostelDetailsPojo.getResponse().getFloors().size());
                                intent.putExtra("hostel_id", hostel_id);
                                intent.putExtra("owner_id", hostelDetailsPojo.getResponse().getOwner_id());
                                intent.putExtra("owner_pic", hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic());
                                intent.putExtra("room_type", ac_status);
                                intent.putExtra("package_type", package_status);
                                intent.putExtra("from_date", check_in);
                                intent.putExtra("to_date", check_out);
                                intent.putExtra("gender_type", gender_type);
                                ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                                context.startActivity(intent, options.toBundle());
                                // finish();
                            } else {
                                Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        if (iscallDateComapare()) {
                            if (compare_from_to_dates.equalsIgnoreCase("false")) {
                                Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                            } else {
                                if (hostel_ac_availability.equalsIgnoreCase("yes")) {

                                    Intent intent = new Intent(HostelDetailsActivity.this, SelectFloorandRoomActivity.class);
                                    intent.putExtra("floor_count", hostelDetailsPojo.getResponse().getFloors().size());
                                    intent.putExtra("hostel_id", hostel_id);
                                    intent.putExtra("owner_id", hostelDetailsPojo.getResponse().getOwner_id());
                                    intent.putExtra("room_type", ac_status);
                                    intent.putExtra("owner_pic", hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic());
                                    intent.putExtra("package_type", package_status);
                                    intent.putExtra("from_date", check_in);
                                    intent.putExtra("to_date", check_out);
                                    intent.putExtra("gender_type", gender_type);

                                    //  intent.putExtra("share",  hostelDetailsPojo.getResponse()());
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                                    context.startActivity(intent, options.toBundle());
                                    // finish();

                                } else {
                                    Toast.makeText(context, "No rooms in selected category", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }

                    }
                }*/


            }
        });


        linear_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog2 = new Dialog(HostelDetailsActivity.this);
                dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog2.setContentView(R.layout.rating_dialog);
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Window window = dialog2.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM | Gravity.BOTTOM);

                TextView text_details, rating_text, ac_rating_tv, bfast_rating_tv, wifi_rating_tv, tv_rating_tv, washroom_rating_tv, hostel_staff_rating_tv;
                ImageView circle_image;
                close = dialog2.findViewById(R.id.close);
                text_details = dialog2.findViewById(R.id.text_details);
                rating_text = dialog2.findViewById(R.id.rating_text);
                ac_rating_tv = dialog2.findViewById(R.id.ac_rating_tv);
                bfast_rating_tv = dialog2.findViewById(R.id.bfast_rating_tv);
                wifi_rating_tv = dialog2.findViewById(R.id.wifi_rating_tv);
                tv_rating_tv = dialog2.findViewById(R.id.tv_rating_tv);
                washroom_rating_tv = dialog2.findViewById(R.id.washroom_rating_tv);
                hostel_staff_rating_tv = dialog2.findViewById(R.id.hostel_staff_rating_tv);
                circle_image = dialog2.findViewById(R.id.circle_image);


                //text_details.setText();

                rating_text.setText(rating);
                ac_rating_tv.setText(ac_ratings);
                bfast_rating_tv.setText(bfast_ratings);
                wifi_rating_tv.setText(wifi_ratings);
                tv_rating_tv.setText(tv_ratings);
                washroom_rating_tv.setText(washroom_ratings);
                hostel_staff_rating_tv.setText(hostelstaff_ratings);

                Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + hostelDetailsPojo.getResponse().getImage()).into(circle_image);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });

                dialog2.show();
            }
        });

//        wifi_linear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog2 = new Dialog(HostelDetailsActivity.this);
//                dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog2.setContentView(R.layout.facilities_item_dialog);
//                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                Window window = dialog2.getWindow();
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                        WindowManager.LayoutParams.WRAP_CONTENT);
//                window.setGravity(Gravity.CENTER | Gravity.CENTER);
//
//                tv_ok = dialog2.findViewById(R.id.tv_ok);
//                tv_ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog2.dismiss();
//                    }
//                });
//                dialog2.show();
//            }
//        });
        location_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   //startActivity(new Intent(HostelDetailsActivity.this, MapActivity.class));
                Intent intent = new Intent(HostelDetailsActivity.this, MapActivity.class);
                intent.putExtra("hostelname",hostel_name);
                startActivity(intent);
*/

                String lat = hostelDetailsPojo.getResponse().getLat();
                String lng = hostelDetailsPojo.getResponse().getLng();
                String slat = String.valueOf(latitude);
                String slng = String.valueOf(longitude);
                String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + slat + "," + slng + "&daddr=" + lat + "," + lng;

                Intent maps_intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(maps_intent);


            }
        });
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callHostelDetails();
    }

    private void openDialogDisplayRatings() {
        dialog = new Dialog(HostelDetailsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ratings_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);
        ImageView iv_dailog_close, iv_owner_img;
        TextView tv_rating_dailog;
        iv_dailog_close = dialog.findViewById(R.id.iv_dailog_close);
        iv_owner_img = dialog.findViewById(R.id.iv_owner_img);
        tv_rating_dailog = dialog.findViewById(R.id.tv_rating_dailog);

        RatingBar rb_room_rating, rb_owner_rating, rb_overall;

        rb_room_rating = dialog.findViewById(R.id.rb_room_rating);
        rb_owner_rating = dialog.findViewById(R.id.rb_owner_rating);
        rb_overall = dialog.findViewById(R.id.rb_overall);


        rb_overall.setRating(Float.parseFloat(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings()));
        rb_room_rating.setRating(Float.parseFloat(hostelDetailsPojo.getResponse().getRatings().getRoom_status_ratings()));
        rb_owner_rating.setRating(Float.parseFloat(hostelDetailsPojo.getResponse().getRatings().getProperty_manager_response_ratings()));


        rb_overall.setIsIndicator(true);
        rb_room_rating.setIsIndicator(true);
        rb_owner_rating.setIsIndicator(true);

        iv_dailog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic())
                .error(R.drawable.no_image)
                .into(iv_owner_img);
        tv_rating_dailog.setText(rating);
        dialog.show();

    }


    private boolean isvalidFromDate() {

        try {

            Date from_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String from = sdf.format(from_date);
            Date from_convert = sdf.parse(from);
            //  check_in = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            Date to_date = sdf.parse(check_in_year + "-" + (check_in_month + 1) + "-" + check_in_day); //date2
            //  Date from_date = sdf.parse(check_in_year + "-" + (check_in_month) + "-" + check_in_day); //date2
            int dateDifference = from_date.compareTo(to_date);

            int years_diiferece = getDiffYears(from_date, to_date);
            // long diffInMilliSec = from_date.getTime() - to_date.getTime();
            long diffInMilliSec = from_convert.getTime() - to_date.getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;

            // long years = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) / 365l;


            // int years_diff = (int) years;
            int days_diff = (int) days;
            System.out.println("dayfhdfj");
            //package_days_tv.setText(days_diff);

            Log.d("Differece Date", days_diff + "Date" + from_date + " to" + to_date);


            if (package_status.equalsIgnoreCase("daily")) {
                if (days_diff == 0 || days_diff == -1 && current_year == check_in_year) {

                    return true;
                } else {
                    callalertdialoguedaily();

                    //  Toast.makeText(context, "Dear user , you are allowed to book one day before only ", Toast.LENGTH_SHORT).show();
                    return false;
                }


            } else {
                if (days < -2) {
                    callalertdialoguemontlhy();

                    // Toast.makeText(context, "Dear user , you are allowed to book three days before only", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return true;
                }

                   /* if (days_diff == 0) {
                        Toast.makeText(context, "Dear user , you are allowed to book one day before only", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        return true;
                    }*/
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return false;
    }

    private void callalertdialoguemontlhy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder2 = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder2 = new AlertDialog.Builder(context);
        }
        builder2.setTitle("Alert")
                .setMessage("Dear user , you are allowed to book three days before only in Monthly Basis")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            dialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        AlertDialog alert = builder2.create();
        alert.show();
    }

    private void callalertdialoguedaily() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Alert")
                .setMessage("Dear user , you are allowed to book one day before only in Daily basis")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            dialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();


    }

    private boolean iscallDateComapare() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from_date = sdf.parse(check_in); //date1
            Date to_date = sdf.parse(check_out); //date2
            int dateDifference = from_date.compareTo(to_date);

            int years_diiferece = getDiffYears(from_date, to_date);
            long diffInMilliSec = from_date.getTime() - to_date.getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;

            long years = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) / 365l;

            if (check_status.equalsIgnoreCase("book_now")) {
                int years_diff = (int) years;
                int days_diff = (int) days;
                Log.d("Differece from to", days_diff + "Date" + from_date + " to" + to_date);


                if (days_diff > 0) {
                    Toast.makeText(HostelDetailsActivity.this, "Check out date must be greater than check in date", Toast.LENGTH_SHORT).show();
                    compare_from_to_dates = "false";
                    return false;
                } else {
                    compare_from_to_dates = "true";
                    return true;
                }
            } else if (check_status.equalsIgnoreCase("check_in")) {


                String remaing_days = String.valueOf(days).substring(1);
                System.out.println("days_diff" + days);

                if (remaing_days.length() == 1) {
                    package_days_tv.setText(0 + remaing_days);

                } else {
                    package_days_tv.setText(remaing_days);

                }

            } else if (check_status.equalsIgnoreCase("check_out")) {

                String remaing_days = String.valueOf(days).substring(1);

                int years_diff = (int) years;
                int days_diff = (int) days;
                Log.d("Differece from to", days_diff + "Date" + from_date + " to" + to_date);


                if (days_diff >= 0) {
                    Toast.makeText(HostelDetailsActivity.this, "Check out date must be greater than check in date", Toast.LENGTH_SHORT).show();

                    package_days_tv.setVisibility(View.GONE);
                    return false;
                } else {
                    package_days_tv.setVisibility(View.VISIBLE);
                    if (remaing_days.length() == 1) {
                        package_days_tv.setText(0 + remaing_days);

                    } else {
                        package_days_tv.setText(remaing_days);

                    }
                    return true;
                }


            } else {

            }


/* if (dateDifference > 0) {
str_day = "";
str_month = "";
str_year = "";
new postRegister().execute();

} else if (dateDifference < 0) {
Toast.makeText(CreateAccount.this, "You selected future date", Toast.LENGTH_SHORT).show();
} else {
Toast.makeText(CreateAccount.this, "You selected today date", Toast.LENGTH_SHORT).show();
}*/

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }


    private void callSetFavouriteWs() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostelDetailsPojo.getResponse().getId());
        presenter.setFavourite(this, this, requestBody);

    }

    private void callHostelDetails() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        // requestBody.put("user_id", user_id);
        requestBody.put("user_id", user_id);
        //  requestBody.put("hostel_id", hostel_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("room_type", ac_status);
        requestBody.put("package_type", package_status);
        // requestBody.put("package_type", "monthly");
        presenter.hostelDetails(context, this, requestBody);
    }

    private void callHostelPricesWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        // requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        //  requestBody.put("hostel_id", "1");
        requestBody.put("room_type", ac_status);
        requestBody.put("package_type", package_status);
        // requestBody.put("package_type", "daily");
        presenter.hostelPrices(context, this, requestBody);

    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_check_availability, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new HostelDetailsImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.HOSTEL_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    ll_status.setVisibility(View.VISIBLE);
                    nodata_tv.setVisibility(View.GONE);
                    hostelDetailsPojo = new Gson().fromJson(responseString, HostelDetailsPojo.class);
                    String rating_status = hostelDetailsPojo.getResponse().getAll_ratings();

                    if (rating_status.equalsIgnoreCase("0")) {
                        rating_complete.setVisibility(View.GONE);
                    } else {
                        rating_complete.setVisibility(View.VISIBLE);

                    }

                    hostel_name = hostelDetailsPojo.getResponse().getName();
                    Variables.hostelname = hostel_name;
                    Variables.hostel_number = hostelDetailsPojo.getResponse().getOwner_details().getMobile();
                    tv_hostel_name.setText(hostelDetailsPojo.getResponse().getName());
                    tv_hostel_address.setText(hostelDetailsPojo.getResponse().getAddress());
                    tv_hostel_code.setText("JOYH" + hostelDetailsPojo.getResponse().getId());
//                    tv_rating_count.setText(hostelDetailsPojo.getResponse().getRatings_count() + " Ratings");
//                    tv_rating.setText(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings());
//                    if (Integer.parseInt(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings()) > 4) {
//                        tv_rating_status.setText("Very Good");
//                    } else if (Integer.parseInt(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings()) < 4 && Integer.parseInt(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings()) > 3) {
//                        tv_rating_status.setText("Average");
//                    } else {
//                        tv_rating_status.setText("Poor");
//                    }

                    tv_owner_name.setText("Owner " + hostelDetailsPojo.getResponse().getOwner_details().getName());
                    tv_owner_languages.setText(hostelDetailsPojo.getResponse().getOwner_details().getLanguages());


                    Variables.owner_name = hostelDetailsPojo.getResponse().getOwner_details().getName();
                    Variables.owner_languages = hostelDetailsPojo.getResponse().getOwner_details().getLanguages();
                    Variables.owner_number = hostelDetailsPojo.getResponse().getOwner_details().getMobile();

                    Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + hostelDetailsPojo.getResponse().getImage())
                            .error(R.drawable.dummy_image)
                            .into(iv_banner);


                    if (hostelDetailsPojo.getResponse().getFavourite().equalsIgnoreCase("yes")) {
                        iv_fav.setBackgroundResource(R.drawable.favorite_red_icon);
                    } else {
                        iv_fav.setBackgroundResource(R.drawable.un_fav);
                    }


                    iv_banner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(HostelDetailsActivity.this, ZoomImageActivity.class);
                            i.putExtra("image", hostelDetailsPojo.getResponse().getImage());
                            startActivity(i);

                        }
                    });


                    fecilitiesAdapter = new FecilitiesAdapter(this, hostelDetailsPojo.getResponse().getFacilities());
                    rview_fecilities.setAdapter(fecilitiesAdapter);

                    fecilitiesAdapter.setOnItemClickListener(new FecilitiesAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.wifi_linear:
                                    str_icon = hostelDetailsPojo.getResponse().getFacilities().get(position).getIcon();
                                    str_descrption = hostelDetailsPojo.getResponse().getFacilities().get(position).getDescription();
                                    openpopupdialogue();
                                    break;
                            }
                        }
                    });

                    Variables.owner_image = hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic();

                    Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + hostelDetailsPojo.getResponse().getOwner_details().getProfile_pic())
                            .error(R.drawable.no_image)
                            .into(iv_owner_pic);

                    String size = String.valueOf(hostelDetailsPojo.getResponse().getHostel_banners().size());
                    final_count.setText(size);

                    bannersAdapter = new BannersAdapter(HostelDetailsActivity.this, hostelDetailsPojo.getResponse().getHostel_banners());
                    editors_viewpager.setAdapter(bannersAdapter);
                    editors_viewpager.setFocusable(true);
                    editors_viewpager.setClipToPadding(false);

                  /*  editors_viewpager.setPadding(20, 0, 120, 0);
                    editors_viewpager.setPageMargin(10);
*/


                    editors_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            System.out.println("fdjfgjk" + position);
                            String count = String.valueOf(position);
                            page_count.setText(String.valueOf(position + 1));

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                    callHostelPricesWs();

                   /* ac_ratings = hostelDetailsPojo.getResponse().getRatings().getAc_ratings();
                    bfast_ratings = hostelDetailsPojo.getResponse().getRatings().getBreakfast_ratings();
                    hostelstaff_ratings = hostelDetailsPojo.getResponse().getRatings().getHostelstaff_ratings();
                    tv_ratings = hostelDetailsPojo.getResponse().getRatings().getTv_ratings();
                    washroom_ratings = hostelDetailsPojo.getResponse().getRatings().getWashroom_ratings();
                    wifi_ratings = hostelDetailsPojo.getResponse().getRatings().getWifi_ratings();
*/
                    ac_ratings = hostelDetailsPojo.getResponse().getRatings().getAc_count();
                    bfast_ratings = hostelDetailsPojo.getResponse().getRatings().getBreakfast_count();
                    hostelstaff_ratings = hostelDetailsPojo.getResponse().getRatings().getHostelstaff_count();
                    tv_ratings = hostelDetailsPojo.getResponse().getRatings().getTv_count();
                    washroom_ratings = hostelDetailsPojo.getResponse().getRatings().getWashroom_count();
                    wifi_ratings = hostelDetailsPojo.getResponse().getRatings().getWifi_count();


                    //float over_all_rating = Float.parseFloat(hostelDetailsPojo.getResponse().getRatings().getOverall_ratings());
                    float over_all_rating = Float.parseFloat(hostelDetailsPojo.getResponse().getAll_ratings());
                    rating = String.valueOf(over_all_rating);
                    tv_rating.setText(rating);


                    if (over_all_rating > 4) {
                        tv_rating_status.setText("Very Good");
                        ll_status.setBackgroundResource(R.drawable.box_green_two);
                        tv_rating.setBackgroundResource(R.drawable.box_green);


                    } else if (over_all_rating >= 3) {
                        tv_rating_status.setText("Good");
                        ll_status.setBackgroundResource(R.drawable.box_green_two);
                        tv_rating.setBackgroundResource(R.drawable.box_green);

                    } else if (over_all_rating < 3) {
                        tv_rating_status.setText("Poor");
                        ll_status.setBackgroundResource(R.drawable.box_green_bad);
                        tv_rating.setBackgroundResource(R.drawable.box_green_red);
                    }


                } else {
                    //ll_data.setVisibility(View.GONE);
                    nodata_tv.setVisibility(View.VISIBLE);
                }
            } else if (NetworkConstants.RequestCode.HOSTEL_PRICES == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    rview_prices.setVisibility(View.VISIBLE);
                    tv_noprices.setVisibility(View.GONE);
                    hostelPricesPojo = new Gson().fromJson(responseString, HostelPricesPojo.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    rview_prices.setLayoutManager(mLayoutManager);
                    pricesAdapter = new HostelPricesAdapter(context, hostelPricesPojo);
                    rview_prices.setAdapter(pricesAdapter);
                    hostel_ac_availability = "yes";
                } else {
                    rview_prices.setVisibility(View.GONE);
                    hostel_ac_availability = "no";
                    tv_noprices.setVisibility(View.VISIBLE);

                    if (package_status.equalsIgnoreCase("monthly")) {
                        tv_noprices.setText("no rooms in monthly basis");
                    } else {
                        tv_noprices.setText("no rooms in daily basis");
                    }


                }
            } else if (NetworkConstants.RequestCode.SET_FAV == requestId) {

                if (jsonObject.optBoolean("status") == true) {


                    if (jsonObject.optString("message").equalsIgnoreCase("Added to favourite!")) {
                        iv_fav.setBackgroundResource(R.drawable.favorite_red_icon);
                        Toast.makeText(this, "Hostel added to favourites", Toast.LENGTH_SHORT).show();
                    } else {
                        iv_fav.setBackgroundResource(R.drawable.un_fav);
                        Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(context, "Something wrong ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openpopupdialogue() {
        dialog2 = new Dialog(HostelDetailsActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_select_category);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);

        TextView msg_tv;
        ImageView iv_icon;
        LinearLayout ll_ok;

        msg_tv = dialog2.findViewById(R.id.msg_tv);
        iv_icon = dialog2.findViewById(R.id.iv_icon);
        ll_ok = dialog2.findViewById(R.id.ll_ok);

        ll_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();

            }
        });

        msg_tv.setText(str_descrption);
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + str_icon).into(iv_icon);


        dialog2.show();


    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


   /* @Override
    protected void onResume() {
        super.onResume();
        check_in="";
        Toast.makeText(this, "check_in empty string", Toast.LENGTH_SHORT).show();



    }*/
}
