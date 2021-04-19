package com.joystays.joy.activities;

import android.app.AlertDialog;
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
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.BookingStatusAcContract;
import com.joystays.joy.mvp.presenter.activity.BookingStatusImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.UserBookingDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.GPSTracker;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BookingStatusActivity extends BaseAbstractActivity<BookingStatusImpl> implements BookingStatusAcContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow,call_icon;
    private TextView tv_otp, tv_hostel, tv_pacakagetype,tv_floor, tv_room, tv_bed, tv_paid_date, tv_paid_amount, tv_status,tv_nodata,tv_owner,
            tv_number,cancel_book,complinets,directions;
    private String user_id, token,str_msg,formattedDate;
    private UserSession userSession;
    private UserBookingDetailsPojo userBookingDetailsPojo;
    private LinearLayout ll_data;
    AlertDialog.Builder builder;
    Dialog dialog2;
    private GPSTracker gpsTracker;
    private Double latitude, longitude;


    String myFormat = "yyyy-MM-dd"; //In which you need put here
    String myFormat12 = "yyyy-MM-dd"; //In which you need put here


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


        gpsTracker = new GPSTracker(BookingStatusActivity.this);
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            Toast.makeText(BookingStatusActivity.this, "Turn on Location", Toast.LENGTH_SHORT).show();
        }



        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
        formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        System.out.println("Currentxbgtime => " + formattedDate);








        back_arrow = findViewById(R.id.back_arrow);
        tv_otp = findViewById(R.id.tv_otp);
        tv_hostel = findViewById(R.id.tv_hostel);
        tv_floor = findViewById(R.id.tv_floor);
        tv_room = findViewById(R.id.tv_room);
        tv_bed = findViewById(R.id.tv_bed);
        tv_paid_date = findViewById(R.id.tv_paid_date);
        tv_paid_amount = findViewById(R.id.tv_paid_amount);
        tv_status = findViewById(R.id.tv_status);
        ll_data = findViewById(R.id.ll_data);
        tv_owner = findViewById(R.id.tv_owner);
        tv_number = findViewById(R.id.tv_number);
        call_icon = findViewById(R.id.call_icon);
        cancel_book = findViewById(R.id.cancel_book);
        complinets = findViewById(R.id.complinets);
        directions = findViewById(R.id.directions);
        tv_pacakagetype = findViewById(R.id.tv_pacakagetype);
        ll_data.setVisibility(View.GONE);
        tv_otp.setVisibility(View.GONE);

        tv_nodata = findViewById(R.id.tv_nodata);

        userSession = new UserSession(BookingStatusActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");

        call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + userBookingDetailsPojo.getResponse().getOwner_details().getMobile()));
                startActivity(intent);
            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat = userBookingDetailsPojo.getResponse().getLat();
                String lng = userBookingDetailsPojo.getResponse().getLng();
                String slat = String.valueOf(latitude);
                String slng = String.valueOf(longitude);
                String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + slat + "," + slng + "&daddr=" + lat + "," + lng;

                Intent maps_intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(maps_intent);

            }
        });






        complinets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogue();
            }
        });

        cancel_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Alert")
                        .setMessage("Are you sure want to Cancel the Booking ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {

                                    cancelBokingWBS();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callBookingDetailsWs();
    }

    private void openDialogue() {
        dialog2 = new Dialog(BookingStatusActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_complint);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);

        TextView tv_send;
        final EditText msg_tv;

        msg_tv = dialog2.findViewById(R.id.msg_tv);
        tv_send = dialog2.findViewById(R.id.tv_send);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_msg = msg_tv.getText().toString().trim();
                if (str_msg.isEmpty()) {
                    Toast.makeText(context, "Please Enter Any Message", Toast.LENGTH_SHORT).show();

                } else {
                    sendcmplntWBS();

                }

            }
        });


        dialog2.show();


    }

    private void sendcmplntWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", user_id);
        requestBody.put("token", token);
        requestBody.put("hostel_id", userBookingDetailsPojo.getResponse().getHostel_id());
        requestBody.put("owner_id", userBookingDetailsPojo.getResponse().getOwner_id());
        requestBody.put("message", str_msg);
        presenter.complaint(BookingStatusActivity.this, this, requestBody);


    }

    private void cancelBokingWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("UBID", userBookingDetailsPojo.getResponse().getBooking_id());
        presenter.cancelBooking(context, this, requestBody);
    }

    private void callBookingDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_booking_details(this, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_booking_status, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new BookingStatusImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, this);
            } else if (NetworkConstants.RequestCode.USER_BOOKING_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    ll_data.setVisibility(View.VISIBLE);
                    tv_nodata.setVisibility(View.GONE);
                    tv_otp.setVisibility(View.VISIBLE);
                    userBookingDetailsPojo = new Gson().fromJson(responseString, UserBookingDetailsPojo.class);
                    tv_hostel.setText(": " + userBookingDetailsPojo.getResponse().getHostel_name());
                    tv_bed.setText(": " + userBookingDetailsPojo.getResponse().getBed_name());
                    tv_floor.setText(": Floor - " + userBookingDetailsPojo.getResponse().getFloor());
                    tv_otp.setText("OTP: " + userBookingDetailsPojo.getResponse().getOtp());
                    tv_paid_amount.setText(": " + userBookingDetailsPojo.getResponse().getAmount());
                    tv_paid_date.setText(": " + userBookingDetailsPojo.getResponse().getCreated_on().substring(0, 10));
                    tv_room.setText(": " + userBookingDetailsPojo.getResponse().getRoom_name());
                    tv_owner.setText(": " + userBookingDetailsPojo.getResponse().getOwner_details().getName());
                    tv_number.setText(": " + userBookingDetailsPojo.getResponse().getOwner_details().getMobile());
                    tv_pacakagetype.setText(": " + userBookingDetailsPojo.getResponse().getPackage_type());
                    String bstatus = userBookingDetailsPojo.getResponse().getStatus();





                    if(bstatus.equalsIgnoreCase("pending")){
                        tv_otp.setVisibility(View.VISIBLE);
                        cancel_book.setVisibility(View.VISIBLE);
                        tv_otp.setText("OTP: " + userBookingDetailsPojo.getResponse().getOtp());


                    }else{
                        tv_otp.setVisibility(View.GONE);
                        cancel_book.setVisibility(View.GONE);

                    }


                    if(userBookingDetailsPojo.getResponse().getVacate_date()!=null){


                        if(userBookingDetailsPojo.getResponse().getStatus().equalsIgnoreCase("vacated")){
                            try{

                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                String vacate_date = userBookingDetailsPojo.getResponse().getVacate_date();
                                Date date1 = formatter.parse(vacate_date);

                                Date date2 = formatter.parse(formattedDate);

                                if (date1.compareTo(date2)<=0)
                                {
                                    tv_status.setText(": " + userBookingDetailsPojo.getResponse().getStatus());
                                }else{
                                    tv_status.setText("Your Checkout request approved by PropertyManager. Your checkout date is on "+vacate_date );

                                }

                            }catch (ParseException e1){
                                e1.printStackTrace();
                            }
                        }else{
                            tv_status.setText("Your Vacant request hasebeen Pending please wait for PropertyManger Update" );

                        }

                    }else {

                        tv_status.setText(": " + userBookingDetailsPojo.getResponse().getStatus());


                    }


                }else{
                    ll_data.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                    tv_otp.setVisibility(View.GONE);
                }
            } else if (NetworkConstants.RequestCode.CANCEL_BOOKING == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {
                    cancel_book.setText("Cancelled");
                    cancel_book.setClickable(false);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookingStatusActivity.this,HomeActivity.class);
                    intent.putExtra("to", "home");
                    startActivity(intent);
                    finish();
                }
           } else if (NetworkConstants.RequestCode.USER_COMPLAINT == requestId) {
            boolean status = jsonObject.getBoolean("status");
            String message = jsonObject.getString("message");
            if (status) {
                Toast.makeText(context, "Complaint submitted successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(BookingStatusActivity.this,HomeActivity.class);
                i.putExtra("to", "home");
                startActivity(i);
                finish();
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
