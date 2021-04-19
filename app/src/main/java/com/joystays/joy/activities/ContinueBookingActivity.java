package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ContinueBookingActContract;
import com.joystays.joy.mvp.presenter.activity.ContinueBookingImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.CheckoutCalculationsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ContinueBookingActivity extends BaseAbstractActivity<ContinueBookingImpl> implements ContinueBookingActContract.IView, View.OnClickListener, APIResponseCallback {
    private TextView ctvb_cancel, tv_owner_name, continue_book, tv_language, new_total_amount,tv_wt_amount,
            tv_package_type, tv_joy_amount, tv_from_date, tv_to_date,tv_owner_amount,tv_joydiscount_amount,
            tv_caution_amount, tv_refund_amount, tv_total_amount, tv_no_days, tv_per_day,tv_refreal_amount;
    private ImageView iv_call;
    private ImageView back_arrow, owner_iv;
    private String hostel_id, user_id, token,owner_languges="Telugu,English",final_amount,discount_amount;
    private UserSession userSession;
    private String owner_id, room_type, package_type, from_date, to_date, share_type, owner_pic,data;
    private CheckoutCalculationsPojo checkoutCalculationsPojo;
    int complete_amount;
    LinearLayout ll_note,ll_walte;

    String referal_amountused="",referal_amoun_used="";

    public static final String DATE_DASH_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "MM-dd-yyyy";



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

        userSession = new UserSession(ContinueBookingActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        owner_languges = userSession.getUserDetails().get("owner_languages");


        final Intent intent = getIntent();
        hostel_id = intent.getStringExtra("hostel_id");
        owner_id = intent.getStringExtra("owner_id");
        room_type = intent.getStringExtra("room_type");
        package_type = intent.getStringExtra("package_type");
        from_date = intent.getStringExtra("from_date");
        to_date = intent.getStringExtra("to_date");
        share_type = intent.getStringExtra("share_type");
        owner_pic = intent.getStringExtra("owner_pic");
        back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ctvb_cancel = findViewById(R.id.ctvb_cancel);
        continue_book = findViewById(R.id.continue_book);
        tv_package_type = findViewById(R.id.tv_package_type);
        iv_call = findViewById(R.id.iv_call);
        tv_joy_amount = findViewById(R.id.tv_joy_amount);
        tv_caution_amount = findViewById(R.id.tv_caution_amount);
        tv_refund_amount = findViewById(R.id.tv_refund_amount);
        tv_total_amount = findViewById(R.id.tv_total_amount);
        tv_per_day = findViewById(R.id.tv_per_day);
        tv_no_days = findViewById(R.id.tv_no_days);
        tv_to_date = findViewById(R.id.tv_to_date);
        tv_from_date = findViewById(R.id.tv_from_date);
        new_total_amount = findViewById(R.id.new_total_amount);
        tv_owner_amount = findViewById(R.id.tv_owner_amount);
        ll_note = findViewById(R.id.ll_note);
        tv_wt_amount = findViewById(R.id.tv_wt_amount);
        tv_refreal_amount = findViewById(R.id.tv_refreal_amount);
        ll_walte = findViewById(R.id.ll_walte);


        tv_owner_name = findViewById(R.id.tv_owner_name);
        tv_language = findViewById(R.id.tv_language);
        owner_iv = findViewById(R.id.owner_iv);
        tv_joydiscount_amount = findViewById(R.id.tv_joydiscount_amount);







      /*  try
        {
            String dateStr,dateStr2;
            Date date = new SimpleDateFormat( DATE_FORMAT , Locale.ENGLISH ).parse(from_date);
            DateFormat formatter = new SimpleDateFormat( DATE_DASH_FORMAT , Locale.getDefault() );
            dateStr = formatter.format( date.getTime() );


            Date date2 = new SimpleDateFormat( DATE_FORMAT , Locale.ENGLISH ).parse(to_date);
            DateFormat formatter2 = new SimpleDateFormat( DATE_DASH_FORMAT , Locale.getDefault() );
            dateStr2 = formatter2.format( date2.getTime() );


        }
        catch( ParseException e ) {
            e.printStackTrace();
        }
*/
        tv_from_date.setText(from_date);
        tv_to_date.setText(to_date);





        if(package_type.equalsIgnoreCase("daily")){
            ll_note.setVisibility(View.GONE);
        }else{
            ll_note.setVisibility(View.VISIBLE);

        }

        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + owner_pic)
                .error(R.drawable.no_image)
                .into(owner_iv);

      //  tv_owner_name.setText("Owner Name: " + Variables.owner_name);
        tv_owner_name.setText(Variables.owner_name);
        tv_language.setText(Variables.owner_languages);
      //  tv_language.setText(owner_languges);


        ctvb_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        continue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data.equalsIgnoreCase("yes")) {

                    if (userSession.getUserDetails().get("approved").equalsIgnoreCase("yes")) {
                        Toast.makeText(context, "You are not allowed to book another hostel", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent1 = new Intent(ContinueBookingActivity.this, TermsAndConditions.class);
                       // intent1.putExtra("total_amount", checkoutCalculationsPojo.getResponse().getFinal_price() + "");
                       // intent1.putExtra("total_amount", final_amount);
                       // intent1.putExtra("total_amount", String.valueOf(complete_amount));
                        intent1.putExtra("total_amount", new_total_amount.getText().toString());
                        System.out.println("total_amount"+new_total_amount.getText().toString());
                        intent1.putExtra("hostel_id", hostel_id);
                        intent1.putExtra("owner_id", owner_id);
                        intent1.putExtra("token", token);
                        intent1.putExtra("joy_wallet", checkoutCalculationsPojo.getResponse().getWallet_amount());
                        intent1.putExtra("room_type", room_type);
                        intent1.putExtra("package_type", package_type);
                        intent1.putExtra("from_date", from_date);
                        intent1.putExtra("to_date", to_date);
                        intent1.putExtra("share_type", share_type);
                        intent1.putExtra("owner_pic", owner_pic);
                        intent1.putExtra("advance_amount", checkoutCalculationsPojo.getResponse().getAdvance());
                        intent1.putExtra("refundable_amount", checkoutCalculationsPojo.getResponse().getRefundable_amount());
                        startActivity(intent1);
                        System.out.println("gyghgggj"+room_type);

                    }
                }else{
                    Toast.makeText(context, "Something went wrong please try again", Toast.LENGTH_SHORT).show();

                }
            }
        });

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + Variables.owner_number;
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }

            }
        });

        callCheckoutCalculationsWs();

    }

    private void callCheckoutCalculationsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("owner_id", owner_id);
        requestBody.put("share", share_type);
        requestBody.put("room_type", room_type);
        requestBody.put("package_type", package_type);
        requestBody.put("from_date", from_date);
        requestBody.put("to_date", to_date);
        requestBody.put("hostel_id", hostel_id);
//        requestBody.put("token", token);
//        requestBody.put("user_id", "1");
//        requestBody.put("owner_id", "1");
//        requestBody.put("share", "1");
//        requestBody.put("room_type", "ac");
//        requestBody.put("package_type", "daily");
//        requestBody.put("from_date", from_date);
//        requestBody.put("to_date", to_date);
//        // requestBody.put("hostel_id", hostel_id);
//        requestBody.put("hostel_id", "1");


        presenter.checkout(context, this, requestBody);
        System.out.println("requestBodyhhhh" + requestBody);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_continue_booking, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new ContinueBookingImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.CHECKOUT_CALCULATIONS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    data = "yes";
                    checkoutCalculationsPojo = new Gson().fromJson(responseString, CheckoutCalculationsPojo.class);

                    //  tv_package_type.setText(checkoutCalculationsPojo.getResponse().getPackage_type());
                    String output = checkoutCalculationsPojo.getResponse().getPackage_type().substring(0, 1).toUpperCase() + checkoutCalculationsPojo.getResponse().getPackage_type().substring(1);
                    tv_package_type.setText(output);

                    tv_joy_amount.setText(checkoutCalculationsPojo.getResponse().getJoy_price());
                    tv_caution_amount.setText(checkoutCalculationsPojo.getResponse().getAdvance());
                    tv_total_amount.setText(checkoutCalculationsPojo.getResponse().getDays_amount() + "");
                    tv_no_days.setText(checkoutCalculationsPojo.getResponse().getDays() + "");
                    tv_per_day.setText(checkoutCalculationsPojo.getResponse().getDay_price() + "");
                    tv_owner_amount.setText(checkoutCalculationsPojo.getResponse().getOwner() + "");

                    if(package_type.equalsIgnoreCase("daily")){
                        ll_walte.setVisibility(View.GONE);

                        if(checkoutCalculationsPojo.getResponse().getWallet_amount()==null){
                            tv_wt_amount.setText("0");

                        }else{
                            tv_wt_amount.setText(checkoutCalculationsPojo.getResponse().getWallet_amount());

                        }

                    }else{
                        ll_walte.setVisibility(View.VISIBLE);

                        if(checkoutCalculationsPojo.getResponse().getWallet_amount()==null){
                            tv_wt_amount.setText("0");

                        }else{
                            tv_wt_amount.setText(checkoutCalculationsPojo.getResponse().getWallet_amount());

                        }
                    }




                    tv_refund_amount.setText("Advance Rs" + checkoutCalculationsPojo.getResponse().getAdvance() + "(" + checkoutCalculationsPojo.getResponse().getRefundable_amount() + "Refundable)");

                  int room_price = checkoutCalculationsPojo.getResponse().getFinal_price();
                  int advance_price = Integer.parseInt(checkoutCalculationsPojo.getResponse().getAdvance());
                  int joy_price = Integer.parseInt(checkoutCalculationsPojo.getResponse().getJoy_price());
                  int joy_owneramount = Integer.parseInt(checkoutCalculationsPojo.getResponse().getOwner());

                    int joy_referalamount=0;
                    if(checkoutCalculationsPojo.getResponse().getReferral_amount()==null){
                        tv_refreal_amount.setText("0");
                         joy_referalamount = 0;


                    }else{
                        tv_refreal_amount.setText(checkoutCalculationsPojo.getResponse().getReferral_amount());
                        joy_referalamount = Integer.parseInt(checkoutCalculationsPojo.getResponse().getReferral_amount());

                    }



                     complete_amount = room_price+advance_price;
                     final_amount = String.valueOf(complete_amount - joy_price);

                      discount_amount = String.valueOf(joy_owneramount - joy_price);



                      if(complete_amount>=1000){
                          referal_amountused="yes";
                          int new_amount = complete_amount-joy_referalamount;
                          new_total_amount.setText(new_amount + "");
                          Variables.rferal_amountused =checkoutCalculationsPojo.getResponse().getReferral_amount();


                      }else{
                          referal_amountused="no";
                          new_total_amount.setText(complete_amount + "");
                          Variables.rferal_amountused ="";


                      }

                    tv_joydiscount_amount.setText(discount_amount + "");





                }else{
                    data = "no";

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
