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
import android.util.Log;
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
import com.joystays.joy.mvp.contract.activity.OwnerConformationActContract;
import com.joystays.joy.mvp.presenter.activity.OwnerConfirmationImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.BookingDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerConformationActivity extends BaseAbstractActivity<OwnerConfirmationImpl> implements OwnerConformationActContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow,owner_iv;
    private String token;
    private UserSession userSession;
    private LinearLayout linear_k, ll_cancel, ll_call;
    private TextView tv_owner_name, tv_ratings, tv_owner_number, tv_hostel_code, tv_otp, tv_room_no, tv_share, tv_plan,
            tv_owner_languages,owner_name,tv_paymenttext;
    private BookingDetailsPojo bookingDetailsPojo;
    AlertDialog.Builder builder;
    Dialog dialog2;



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
        back_arrow = findViewById(R.id.back_arrow);
        linear_k = findViewById(R.id.linear_k);
        ll_call = findViewById(R.id.ll_call);
        ll_cancel = findViewById(R.id.ll_cancel);
        tv_owner_name = findViewById(R.id.tv_owner_name);
        tv_owner_number = findViewById(R.id.tv_owner_number);
        tv_hostel_code = findViewById(R.id.tv_hostel_code);
        tv_otp = findViewById(R.id.tv_otp);
        tv_room_no = findViewById(R.id.tv_room_no);
        tv_ratings = findViewById(R.id.tv_ratings);
        tv_share = findViewById(R.id.tv_share);
        tv_plan = findViewById(R.id.tv_plan);
        tv_owner_languages = findViewById(R.id.tv_owner_languages);
        owner_name = findViewById(R.id.owner_name);
        tv_paymenttext = findViewById(R.id.tv_paymenttext);
        owner_iv = findViewById(R.id.owner_iv);

        tv_owner_languages.setText(Variables.owner_languages);
        userSession = new UserSession(OwnerConformationActivity.this);
        token = userSession.getUserDetails().get("token");
        callBookingDetailsWs();

        linear_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OwnerConformationActivity.this, HomeActivity.class);
                intent.putExtra("to", "home");
                startActivity(intent);


            }
        });
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialogue();




            }
        });
        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + bookingDetailsPojo.getResponse().get(0).getOwner_mobile()));
                startActivity(intent);
            }
        });
    }

/*    private void cancelalret() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
//AlertDialog.Builder builder = new AlertDialog.Builder(ActivityHome.this);
        builder.setTitle("Alert")
                .setMessage("Are you sure want to Cancel?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            callCancelBookingWs();

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
        //super.onBackPressed();

    }*/
    private void openDialogue() {
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

                            callCancelBookingWs();

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


    private void callBookingDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("UBID", Variables.booking_id);
        presenter.bookingDetails(context, this, requestBody);
    }

    private void callCancelBookingWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("UBID", Variables.booking_id);
        presenter.cancelBooking(context, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_owner_conformation, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new OwnerConfirmationImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.BOOKING_DETAILS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {

                    bookingDetailsPojo = new Gson().fromJson(responseString, BookingDetailsPojo.class);
                    tv_otp.setText(bookingDetailsPojo.getResponse().get(0).getOtp());
                    tv_owner_name.setText(bookingDetailsPojo.getResponse().get(0).getOwner_name());
                    tv_owner_number.setText(bookingDetailsPojo.getResponse().get(0).getOwner_mobile());
                    tv_plan.setText(bookingDetailsPojo.getResponse().get(0).getPackage_type());
                    tv_ratings.setText(bookingDetailsPojo.getResponse().get(0).getHostel_ratings());
                    tv_room_no.setText("Room Name : " + bookingDetailsPojo.getResponse().get(0).getRoom_name());
                    tv_share.setText(bookingDetailsPojo.getResponse().get(0).getShare());
                    tv_hostel_code.setText("Joy" +bookingDetailsPojo.getResponse().get(0).getHostel_id());

                    tv_paymenttext.setText(bookingDetailsPojo.getResponse().get(0).getAmount() + " Rs of Payment done sucessfully to PropertyManger ");
                  /*  Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL +bookingDetailsPojo.getResponse().get(0).getHostel_image())
                            .error(R.drawable.dummy_image)
                            .into(owner_iv);
*/

                    Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL +Variables.owner_image)
                            .error(R.drawable.no_image)
                            .into(owner_iv);
                  /*  if (Variables.owner_image.isEmpty() ||Variables.owner_image.equalsIgnoreCase(null)) {


                        owner_iv.setImageDrawable(getResources().getDrawable(R.drawable.no_banner));


                    }else{

                    }*/



                }
            } else if (NetworkConstants.RequestCode.CANCEL_BOOKING == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                if (status) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(OwnerConformationActivity.this,HomeActivity.class);
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
