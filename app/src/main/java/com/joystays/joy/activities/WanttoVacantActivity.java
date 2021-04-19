package com.joystays.joy.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.customfonts.CustomTextViewNormal;
import com.joystays.joy.mvp.contract.activity.VacateRequestContract;
import com.joystays.joy.mvp.presenter.activity.VacateRequestImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Variables;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.joystays.joy.activities.HostelDetailsActivity.getDiffYears;

public class WanttoVacantActivity extends BaseAbstractActivity<VacateRequestImpl> implements VacateRequestContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow, iv_vacate_extend;
    private CustomTextViewNormal ctvn_vacant, ctvn_mytrip;
    private LinearLayout linear_mytrip, linear_vacant, linear_sent, ll_extend_trip, ll_trip_request, ll_my_trip_details, ll_vacate_request, ll_my_vacate_req, ll_vacate_extend;
    private ImageView iv_vacate_from, ic_from_my_trip, ic_to_my_trip, ic_extend_my_trip;
    private TextView tv_to_trip, tv_vacate_req_date, tv_from_trip, tv_extend_trip_days,tv_text_daily,
            tv_my_trip_from, tv_my_trip_to, tv_inform, tv_trip_extended, tv_request_extended_to, tv_vacate_req, tv_vacate_extended;
    private String str_trip_from_date = "", str_trip_to_date = "", str_trip_extend_date = "", user_id, token, msg = "",
            str_vacate_extend_date = "", str_vacate_from_date = "", msg_vacate = "",trip_todate;

    int current_day, current_month, current_year, check_in_day, check_in_month, check_in_year,check_out_day, check_out_month, check_out_year;


    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);

    private String trip_type = "trip";
    private EditText et_msg, et_trip_extend, et_vacate_msg, et_extend_vacate_msg;
    private UserSession userSession;
    private String str_trip_id, hostel_id, owner_id, room_id, floor, bed_id,compare_from_to_dates,booking_id;


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
        linear_sent = findViewById(R.id.linear_sent);
        back_arrow = findViewById(R.id.back_arrow);
        ctvn_vacant = findViewById(R.id.ctvn_vacant);
        ctvn_mytrip = findViewById(R.id.ctvn_mytrip);
        linear_mytrip = findViewById(R.id.linear_mytrip);
        linear_vacant = findViewById(R.id.linear_vacant);
        iv_vacate_from = findViewById(R.id.iv_vacate_from);
        ic_from_my_trip = findViewById(R.id.ic_from_my_trip);
        ic_to_my_trip = findViewById(R.id.ic_to_my_trip);
        ic_extend_my_trip = findViewById(R.id.ic_extend_my_trip);
        tv_to_trip = findViewById(R.id.tv_to_trip);
        tv_from_trip = findViewById(R.id.tv_from_trip);
        tv_extend_trip_days = findViewById(R.id.tv_extend_trip_days);
        tv_my_trip_from = findViewById(R.id.tv_my_trip_from);
        tv_my_trip_to = findViewById(R.id.tv_my_trip_to);
        et_msg = findViewById(R.id.et_msg);
        et_trip_extend = findViewById(R.id.et_trip_extend);
        ll_extend_trip = findViewById(R.id.ll_extend_trip);
        ll_trip_request = findViewById(R.id.ll_trip_request);
        ll_my_trip_details = findViewById(R.id.ll_my_trip_details);
        tv_inform = findViewById(R.id.tv_inform);
        tv_trip_extended = findViewById(R.id.tv_trip_extended);
        et_vacate_msg = findViewById(R.id.et_vacate_msg);
        ll_vacate_request = findViewById(R.id.ll_vacate_request);
        ll_my_vacate_req = findViewById(R.id.ll_my_vacate_req);
        tv_vacate_req = findViewById(R.id.tv_vacate_req);
        tv_vacate_extended = findViewById(R.id.tv_vacate_extended);
        tv_request_extended_to = findViewById(R.id.tv_request_extended_to);
        ll_vacate_extend = findViewById(R.id.ll_vacate_extend);
        et_extend_vacate_msg = findViewById(R.id.et_extend_vacate_msg);
        iv_vacate_extend = findViewById(R.id.iv_vacate_extend);
        tv_vacate_req_date = findViewById(R.id.tv_vacate_req_date);
        tv_text_daily = findViewById(R.id.tv_text_daily);
        ll_vacate_request.setVisibility(View.GONE);
        linear_mytrip.setVisibility(View.VISIBLE);
        ll_my_vacate_req.setVisibility(View.GONE);


        userSession = new UserSession(WanttoVacantActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        owner_id = userSession.getUserDetails().get("owner_id");
        room_id = userSession.getUserDetails().get("room_id");
        floor = userSession.getUserDetails().get("floor");
        bed_id = userSession.getUserDetails().get("bed_id");
        booking_id = userSession.getUserDetails().get("booking_id");
        System.out.println("booking_id"+booking_id);
        callMytripsWS();

    }

    private void callMytripsWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.my_trips(context, this, requestBody);
    }

    private void callMyVacateRequestWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.my_vacant_request(context, this, requestBody);
    }

    private void callTripExtendRequestWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("trip_id", str_trip_id);
        requestBody.put("extend_trip_date", str_trip_extend_date);
        requestBody.put("second_message", msg);
        presenter.extend_trip_request(context, this, requestBody);
    }

    private void callVacateExtendRequestWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);

        requestBody.put("vacant_id", str_trip_id);
        requestBody.put("extend_trip_date", str_vacate_extend_date);
        requestBody.put("extend_trip_amount", str_trip_extend_date);
        requestBody.put("second_message", msg);
        presenter.extend_vacant_request(context, this, requestBody);

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        linear_sent.setOnClickListener(this);
        ctvn_mytrip.setOnClickListener(this);
        ctvn_vacant.setOnClickListener(this);
        back_arrow.setOnClickListener(this);
        iv_vacate_from.setOnClickListener(this);
        ic_from_my_trip.setOnClickListener(this);
        ic_to_my_trip.setOnClickListener(this);
        ic_extend_my_trip.setOnClickListener(this);
        iv_vacate_extend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_sent:
                if (trip_type.equalsIgnoreCase("trip")) {

                    if (!str_trip_from_date.isEmpty()) {
                        msg = et_msg.getText().toString();


                        if (isvalidFromDate()) {

                            if (iscallDateComapare()) {
                                callTripRequestWs();

                            }
                        }


                    } else {
                        msg = et_trip_extend.getText().toString();

                        if (str_trip_extend_date.isEmpty()) {
                            Toast.makeText(context, "Select date for extension", Toast.LENGTH_SHORT).show();
                        } else {
                            if (isextendcallDateComapare()) {
                                callTripExtendRequestWs();

                            }
                        }

                    }
                } else {


                    if (!str_vacate_from_date.isEmpty()) {
                        msg_vacate = et_vacate_msg.getText().toString();
                        callVacateWs();
                    } else {
                        msg_vacate = et_extend_vacate_msg.getText().toString();
                       // callVacateExtendRequestWs();
                        Toast.makeText(context, "Please Select Vacant Date", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.ctvn_mytrip:
                trip_type = "trip";
                linear_mytrip.setVisibility(View.VISIBLE);
                linear_vacant.setVisibility(View.GONE);
                ctvn_mytrip.setBackground(getResources().getDrawable(R.drawable.tarrif_green));
                ctvn_vacant.setBackgroundColor(getResources().getColor(R.color.white));
                ctvn_vacant.setTextColor(getResources().getColor(R.color.text_black));
                ctvn_mytrip.setTextColor(getResources().getColor(R.color.white));
                break;

            case R.id.ctvn_vacant:
                trip_type = "vacate";
                linear_mytrip.setVisibility(View.GONE);
                linear_vacant.setVisibility(View.VISIBLE);
                ctvn_vacant.setBackground(getResources().getDrawable(R.drawable.tarrif_green));
                ctvn_mytrip.setBackgroundColor(getResources().getColor(R.color.white));
                ctvn_vacant.setTextColor(getResources().getColor(R.color.white));
                ctvn_mytrip.setTextColor(getResources().getColor(R.color.text_black));

                if(Variables.packagetype.equalsIgnoreCase("daily")){
                    ll_vacate_request.setVisibility(View.GONE);
                    ll_my_vacate_req.setVisibility(View.GONE);
                    tv_text_daily.setVisibility(View.VISIBLE);


                }else{
                    tv_text_daily.setVisibility(View.GONE);
                    ll_vacate_request.setVisibility(View.VISIBLE);

                }



                callMyVacateRequestWs();
                break;

            case R.id.back_arrow:
                onBackPressed();
                break;
            case R.id.iv_vacate_from:
                DatePickerDialog datePickerDialog_vacate = new DatePickerDialog(WanttoVacantActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                tv_vacate_req_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                str_vacate_from_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog_vacate.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog_vacate.show();

                break;


            case R.id.iv_vacate_extend:
                DatePickerDialog datePickerDialog_vacate_extend = new DatePickerDialog(WanttoVacantActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                tv_request_extended_to.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                str_vacate_extend_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog_vacate_extend.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog_vacate_extend.show();

                break;
            case R.id.ic_from_my_trip:
                DatePickerDialog datePickerDialog = new DatePickerDialog(WanttoVacantActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tv_from_trip.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                str_trip_from_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                                check_in_day = dayOfMonth;
                                check_in_month = monthOfYear;
                                check_in_year = year;



                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.ic_to_my_trip:
                DatePickerDialog datePickerDialog_trip = new DatePickerDialog(WanttoVacantActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tv_to_trip.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                str_trip_to_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                check_out_day = dayOfMonth;
                                check_out_month = monthOfYear;
                                check_out_year = year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog_trip.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog_trip.show();
                break;
            case R.id.ic_extend_my_trip:
                DatePickerDialog datePickerDialog_extend_trip = new DatePickerDialog(WanttoVacantActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tv_extend_trip_days.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                str_trip_extend_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog_extend_trip.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog_extend_trip.show();
                break;
        }
    }

    private boolean isextendcallDateComapare() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from_date = sdf.parse(trip_todate); //date1
            Date to_date = sdf.parse(str_trip_extend_date); //date2
            int dateDifference = from_date.compareTo(to_date);

            int years_diiferece = getDiffYears(from_date, to_date);
            long diffInMilliSec = from_date.getTime() - to_date.getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;

            long years = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) / 365l;

            int years_diff = (int) years;
            int days_diff = (int) days;
            Log.d("Differece from to", days_diff + "Date" + from_date + " to" + to_date);


            if (days_diff > 0) {
                Toast.makeText(WanttoVacantActivity.this, "Extend date must be greater than Trip Ended date", Toast.LENGTH_SHORT).show();
                compare_from_to_dates = "false";
                return false;
            } else {
                compare_from_to_dates = "true";
                return true;
            }





        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean iscallDateComapare() {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from_date = sdf.parse(str_trip_from_date); //date1
            Date to_date = sdf.parse(str_trip_to_date); //date2
            int dateDifference = from_date.compareTo(to_date);

            int years_diiferece = getDiffYears(from_date, to_date);
            long diffInMilliSec = from_date.getTime() - to_date.getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;

            long years = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) / 365l;

            int years_diff = (int) years;
            int days_diff = (int) days;
            Log.d("Differece from to", days_diff + "Date" + from_date + " to" + to_date);


            if (days_diff > 0) {
                Toast.makeText(WanttoVacantActivity.this, "End date must be greater than From date", Toast.LENGTH_SHORT).show();
                compare_from_to_dates = "false";
                return false;
            } else {
                compare_from_to_dates = "true";
                return true;
            }





        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;


    }

    private boolean isvalidFromDate() {

        try {

            Date from_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //  check_in = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            Date to_date = sdf.parse(check_in_year + "-" + (check_in_month + 1) + "-" + check_in_day); //date2
            //  Date from_date = sdf.parse(check_in_year + "-" + (check_in_month) + "-" + check_in_day); //date2
            int dateDifference = from_date.compareTo(to_date);

            int years_diiferece = getDiffYears(from_date, to_date);
            long diffInMilliSec = from_date.getTime() - to_date.getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;

            // long years = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) / 365l;


            // int years_diff = (int) years;
            int days_diff = (int) days;
            //package_days_tv.setText(days_diff);

            Log.d("Differece Date", days_diff + "Date" + from_date + " to" + to_date);

            if (days_diff == 0) {

                return true;
            } else {

                Toast.makeText(context, "Dear user , you are allowed to Notice one day before only ", Toast.LENGTH_SHORT).show();
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }





        return false;
    }

    private void validatedata() {
        try{

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


            String str1 = str_trip_from_date;
            System.out.println("str_trip_from_date"+str_trip_from_date );
            Date date1 = formatter.parse(str1);

            String str2 = str_trip_to_date;
            Date date2 = formatter.parse(str2);

            if (date1.compareTo(date2)<0)
            {
                Toast.makeText(context, "date2 is Greater than my date1", Toast.LENGTH_SHORT).show();

                callTripExtendRequestWs();
            }else{
                Toast.makeText(context, "date2 is less than my date1", Toast.LENGTH_SHORT).show();


            }

        }catch (ParseException e1){
            e1.printStackTrace();
        }


    }


    private void callVacateWs() {
        Map<String, String> requestBody = new HashMap<>();

//        requestBody.put("token", token);
//        requestBody.put("user_id", "8");
//        requestBody.put("hostel_id", "1");
//        requestBody.put("owner_id", "1");
//        //  requestBody.put("hostel_id", "1");
//        requestBody.put("floor", "0");
//        requestBody.put("room_id", "1");
//        requestBody.put("bed_id", "1");
//        requestBody.put("from_date", str_vacate_from_date);
//        requestBody.put("first_message", msg_vacate);

        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("owner_id", owner_id);
        //  requestBody.put("hostel_id", "1");
        requestBody.put("floor", floor);
        requestBody.put("room_id", room_id);
        requestBody.put("bed_id", bed_id);
        requestBody.put("from_date", str_vacate_from_date);
        requestBody.put("first_message", msg_vacate);
        requestBody.put("booking_id", booking_id);
        presenter.vacateRequest(context, this, requestBody);
        System.out.println("requestBody"+requestBody);
    }


    private void callTripRequestWs() {




        Map<String, String> requestBody = new HashMap<>();


      /*  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
                "user_id":"2",
                "owner_id":"1",
                "hostel_id":"1",
                "floor":"0",
                "room_id":"1",
                "bed_id":"1",
                "from_date":"2019-10-31",
                "first_message":"Test Message"*/

        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("owner_id", owner_id);
        //  requestBody.put("hostel_id", "1");
        requestBody.put("floor", floor);
        requestBody.put("room_id", room_id);
        requestBody.put("bed_id", bed_id);
        requestBody.put("from_date", str_trip_from_date);
        requestBody.put("to_date", str_trip_to_date);

        requestBody.put("first_message", msg);

        // requestBody.put("package_type", "daily");
        presenter.triprequest(context, this, requestBody);
    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_wantto_vacant, null);
        return view;
    }


    @Override
    public void setPresenter() {
        presenter = new VacateRequestImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                //   Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.VACATE_REQUEST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    str_trip_from_date = "";
                    Toast.makeText(context, "Vacate request posted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WanttoVacantActivity.this,HomeActivity.class);
                    intent.putExtra("to", "home");
                    startActivity(intent);


                    // callMyVacateRequestWs();
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            } else if (NetworkConstants.RequestCode.MY_VACANT_REQUEST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    str_vacate_from_date = "";

                    if(Variables.packagetype.equalsIgnoreCase("daily")){
                        ll_my_vacate_req.setVisibility(View.GONE);
                        ll_vacate_request.setVisibility(View.GONE);
                        ll_vacate_extend.setVisibility(View.GONE);
                        linear_sent.setVisibility(View.GONE);
                        tv_text_daily.setVisibility(View.VISIBLE);



                    }else{
                        ll_my_vacate_req.setVisibility(View.VISIBLE);
                        ll_vacate_request.setVisibility(View.GONE);
                        ll_vacate_extend.setVisibility(View.GONE);
                        linear_sent.setVisibility(View.GONE);
                        tv_text_daily.setVisibility(View.GONE);


                    }

                    tv_vacate_req.setText("From: " + jsonObject.getJSONObject("response").optString("from_date").substring(0, 10));
                    str_trip_id = jsonObject.getJSONObject("response").optString("id");

                    if (jsonObject.getJSONObject("response").optString("extend_trip_days") == null || jsonObject.getJSONObject("response").optString("extend_trip_days").isEmpty() || jsonObject.getJSONObject("response").optString("extend_trip_days").equalsIgnoreCase("0")) {

                        tv_vacate_extended.setText("Vacation extended : No");


                    } else {
                        tv_vacate_extended.setText("Vacation extended : Yes");
                        //    tv_request_extended_to.setText("Trip extended : " + jsonObject.getJSONObject("response").optString("extend_trip_date").substring(0, 10));

                    }


                } else {
                    ll_my_vacate_req.setVisibility(View.GONE);
                    ll_vacate_request.setVisibility(View.VISIBLE);
                    ll_vacate_extend.setVisibility(View.GONE);
                    linear_sent.setVisibility(View.VISIBLE);
                }

            } else if (NetworkConstants.RequestCode.TRIP_REQUEST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    str_trip_from_date = "";
                    Toast.makeText(context, "Trip request posted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WanttoVacantActivity.this,HomeActivity.class);
                    intent.putExtra("to", "home");
                    startActivity(intent);
                   // callMytripsWS();
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            } else if (NetworkConstants.RequestCode.EXTEND_TRIP == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    str_trip_from_date = "";
                    et_trip_extend.setText("");
                    Toast.makeText(context, "Extend Trip request posted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WanttoVacantActivity.this,HomeActivity.class);
                    intent.putExtra("to", "home");
                    startActivity(intent);
                    //callMytripsWS();
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                }
            } else if (NetworkConstants.RequestCode.MY_TRIPS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {


                    ll_extend_trip.setVisibility(View.VISIBLE);
                    ll_trip_request.setVisibility(View.GONE);
                    ll_my_trip_details.setVisibility(View.VISIBLE);
                    tv_inform.setVisibility(View.GONE);
                    tv_my_trip_from.setText("From: " + jsonObject.getJSONObject("response").optString("from_date").substring(0, 10));
                    tv_my_trip_to.setText("To: " + jsonObject.getJSONObject("response").optString("to_date").substring(0, 10));
                    trip_todate =  jsonObject.getJSONObject("response").optString("to_date").substring(0, 10);
                    str_trip_id = jsonObject.getJSONObject("response").optString("id");

                    if (jsonObject.getJSONObject("response").optString("extend_trip_date") == null || jsonObject.getJSONObject("response").optString("extend_trip_date").isEmpty()) {

                        tv_trip_extended.setText("Trip extended : No");

                    } else {
                        tv_trip_extended.setText("Trip extended : Yes");

                        tv_extend_trip_days.setText("Trip extended : " + jsonObject.getJSONObject("response").optString("extend_trip_date").substring(0, 10));

                    }
//                    if (jsonObject.getJSONObject("response").optString("extend_trip_date") != null || !jsonObject.getJSONObject("response").optString("extend_trip_date").isEmpty()) {
//                        tv_extend_trip_days.setText("Trip extended : " + jsonObject.getJSONObject("response").optString("extend_trip_date"));
//                    } else {
//                        tv_extend_trip_days.setText("Trip extended : No");
//                    }
                } else {

                    ll_extend_trip.setVisibility(View.GONE);
                    ll_trip_request.setVisibility(View.VISIBLE);
                    ll_my_trip_details.setVisibility(View.GONE);
                    tv_inform.setVisibility(View.VISIBLE);
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
