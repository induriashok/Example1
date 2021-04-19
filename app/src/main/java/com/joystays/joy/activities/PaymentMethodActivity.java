package com.joystays.joy.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gocashfree.cashfreesdk.CFPaymentService;
import com.google.gson.Gson;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.PaymentMethodContract;
import com.joystays.joy.mvp.presenter.activity.PaymentMethodImp;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.PaymentTokenPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CARD_CVV;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CARD_HOLDER;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CARD_MM;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CARD_NUMBER;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CARD_YYYY;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_PAYMENT_OPTION;

public class PaymentMethodActivity extends BaseAbstractActivity<PaymentMethodImp> implements PaymentMethodContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_pay, transaction_linear, linear_confirm, ll_cash;
    private RadioButton rb_cash, rb_online;
    private String payment_mode = "";
    private String hostel_id, user_id, token;
    private UserSession userSession;
    private String owner_id, room_type, package_type, from_date, to_date, share_type, total_amount,actual_room_type,refreal_amount,booking_id,
            from, joy_wallet, joy_wallet_used_amount, room_change_amount_id, UBID, owner_pic, refundable_amount, transaction_id, advance_amount;

    String merchantKey, userCredentials;
    private String salt = null;

    private static final String TAG = "PaymentREsponse";
    private PaymentTokenPojo paymentTokenPojo;

    private int reqestcode;
    private String floor_id, bed_id, room_id;

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


        userSession = new UserSession(PaymentMethodActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");

        floor_id = Variables.floor_id;
        room_id = Variables.room_id;
        bed_id = Variables.bed_id;


        final Intent intent = getIntent();

        System.out.println("floor_iddddd" + Variables.floor_id);
        from = intent.getStringExtra("from");
        back_arrow = findViewById(R.id.back_arrow);
        linear_pay = findViewById(R.id.linear_pay);
        transaction_linear = findViewById(R.id.transaction_linear);
        rb_cash = findViewById(R.id.rb_cash);
        rb_cash.setChecked(true);
        rb_online = findViewById(R.id.rb_online);
        ll_cash = findViewById(R.id.ll_cash);
        if (from.equalsIgnoreCase("terms")) {
            hostel_id = intent.getStringExtra("hostel_id");
            owner_id = intent.getStringExtra("owner_id");
            room_type = intent.getStringExtra("room_type");
            package_type = intent.getStringExtra("package_type");
            from_date = intent.getStringExtra("from_date");
            to_date = intent.getStringExtra("to_date");
            share_type = intent.getStringExtra("share_type");
            total_amount = intent.getStringExtra("total_amount");
            owner_pic = intent.getStringExtra("owner_pic");
            advance_amount = intent.getStringExtra("advance_amount");
            refundable_amount = intent.getStringExtra("refundable_amount");
            joy_wallet = intent.getStringExtra("joy_wallet");


            //Variables.owner_image = owner_pic;
            ll_cash.setVisibility(View.GONE);
            rb_online.setChecked(true);
            payment_mode = "online";
            reqestcode = 1001;
        } else {
            joy_wallet_used_amount = intent.getStringExtra("joy_wallet_used_amount");
            room_change_amount_id = intent.getStringExtra("room_change_amount_id");
            UBID = intent.getStringExtra("UBID");
            hostel_id = intent.getStringExtra("hostel_id");
            actual_room_type = intent.getStringExtra("room_type");
            package_type = intent.getStringExtra("package_type");
            refreal_amount = intent.getStringExtra("refreal_amount");
            booking_id = intent.getStringExtra("booking_id");
            owner_id = intent.getStringExtra("owner_id");
            floor_id = intent.getStringExtra("floor_id");
            room_id = intent.getStringExtra("room_id");
            bed_id = intent.getStringExtra("bed_id");
            share_type = intent.getStringExtra("share_type");
            advance_amount = intent.getStringExtra("advance_amount");
            refundable_amount = intent.getStringExtra("refundable_amount");
            ll_cash.setVisibility(View.VISIBLE);
            rb_cash.setChecked(true);
            payment_mode = "cash";
            reqestcode = 1002;



            if(actual_room_type.equalsIgnoreCase("no")){
                room_type="non_ac";
            }else{
                room_type="ac";

            }


        }


        rb_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_mode = "online";
                if (rb_online.isChecked()) {
                    rb_online.setChecked(true);
                } else {
                    rb_online.setChecked(false);
                }
                rb_cash.setChecked(false);
            }
        });
        rb_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_mode = "cash";
                if (rb_cash.isChecked()) {
                    rb_cash.setChecked(true);
                } else {
                    rb_cash.setChecked(false);
                }
                rb_online.setChecked(false);
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

/*

        linear_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (payment_mode.isEmpty()) {
                    Toast.makeText(PaymentMethodActivity.this, "Please select mode of payment", Toast.LENGTH_SHORT).show();


                } else if (from.equalsIgnoreCase("terms")) {
                   // callpaymentscreen();
                   callFreeCashPaymentWs();

//                    Intent intent1 = new Intent(PaymentMethodActivity.this, PaymentGateWayActivity.class);
//                    intent1.putExtra("hostel_id", hostel_id);
//                    intent1.putExtra("owner_id", owner_id);
//                    intent1.putExtra("room_type", room_type);
//                    intent1.putExtra("package_type", package_type);
//                    intent1.putExtra("from_date", from_date);
//                    intent1.putExtra("to_date", to_date);
//                    intent1.putExtra("share_type", share_type);
//                    intent1.putExtra("total_amount", total_amount);
//                    intent1.putExtra("owner_pic", owner_pic);
//                    intent1.putExtra("from", "terms");
//                    startActivity(intent1);

                } else {

                    if (payment_mode.equalsIgnoreCase("cash")) {
                        renewBookingWs();

                    } else {

                      //   callpaymentscreen();
                        callFreeCashPaymentWs();



                        Intent intent1 = new Intent(PaymentMethodActivity.this, PaymentGateWayActivity.class);
                        intent1.putExtra("joy_wallet_used_amount", joy_wallet_used_amount);
                        intent1.putExtra("room_change_amount_id", room_change_amount_id);
                        intent1.putExtra("from", "wallet");
                        intent1.putExtra("UBID", UBID);
                        startActivity(intent1);

                    }
                }

//
//
//                final Dialog dialog2 = new Dialog(PaymentMethodActivity.this);
//                dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog2.setContentView(R.layout.payment_dialog);
//                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                Window window = dialog2.getWindow();
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                        WindowManager.LayoutParams.MATCH_PARENT);
//                window.setGravity(Gravity.CENTER | Gravity.CENTER);
//
//                linear_confirm = dialog2.findViewById(R.id.linear_confirm);
//                linear_confirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(PaymentMethodActivity.this, SecureBookingDetailsActivity.class));
//                    }
//                });
//
//
//                dialog2.show();


            }
        });

*/


        linear_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  callFreeCashPaymentWs();

                //               startActivity(new Intent(PaymentMethodActivity.this,CashfreePaymentActivity.class));


                //  callPlaceOrderService();


                if (payment_mode.isEmpty()) {
                    Toast.makeText(PaymentMethodActivity.this, "Please select mode of payment", Toast.LENGTH_SHORT).show();
                } else if (from.equalsIgnoreCase("terms")) {
                    // callPlaceOrderService();

                    if (floor_id == null || floor_id.isEmpty() || bed_id == null || bed_id.isEmpty() || room_id == null || room_id.isEmpty()) {
                        Toast.makeText(context, "Something went wrong in Bed Selection, Kindly select bed again", Toast.LENGTH_LONG).show();
                    } else {
                        generatePaymentTokenWs();
                    }
                } else {
                    if (payment_mode.equalsIgnoreCase("cash")) {
                        renewBookingWs();
                    } else {
                        generatePaymentTokenWs();
                    }
                }
            }
        });
    }

    private void generatePaymentTokenWs() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("orderAmount", "1");
        presenter.generatePaymentToken(PaymentMethodActivity.this, this, requestBody);

    }

    private void callFreeCashPaymentWs() {

        String stage = "TEST";


        String appId = "com.joystays.joy";
        String orderId = "123456";
        String orderAmount = "1";
        String orderNote = "Test Order";
        String customerName = "Srikanth";
        String customerPhone = "8886032595";
        String customerEmail = "test@gmail.com";


        Map<String, String> params = new HashMap<>();

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, orderId);
        params.put(PARAM_ORDER_AMOUNT, orderAmount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);


        params.put(PARAM_PAYMENT_OPTION, "card");
        params.put(PARAM_CARD_NUMBER, "4434260000000008");//Replace Card number
        params.put(PARAM_CARD_MM, "05"); // Card Expiry Month in MM
        params.put(PARAM_CARD_YYYY, "2021"); // Card Expiry Year in YYYY
        params.put(PARAM_CARD_HOLDER, "John Doe"); // Card Holder name
        params.put(PARAM_CARD_CVV, "123"); // Card CVV


        for (Map.Entry entry : params.entrySet()) {
            Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
        }

        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);

        // Use the following method for initiating Payments
        // First color - Toolbar background
        // Second color - Toolbar text and back arrow color
        cfPaymentService.doPayment(this, params, "Lk9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.fm0nImZ2M0QWNmdDZjVTZ1IiOiQHbhN3XiwSMwkDNzcTN4UTM6ICc4VmIsIiUOlkI6ISej5WZyJXdDJXZkJ3biwiIxIiOiQnb19WbBJXZkJ3biwiI2UDNzITMiojIklkclRmcvJye.6PSgvuF1EhPvqscriPMBSdIH56d2uuZyIMU8qHQR8f3VmJOmsLskVVNEjdpgRKeoYb", stage, "#000000", "#FFFFFF", true);
    }


    private void renewBookingWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("UBID", UBID);
        requestBody.put("room_change_amount_id", room_change_amount_id);
        requestBody.put("joy_wallet_used_amount", joy_wallet_used_amount);
        requestBody.put("transaction_id", transaction_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("room_type", room_type);
        requestBody.put("package_type", package_type);
        requestBody.put("payment_mode", payment_mode);
        requestBody.put("referral_amount_used",refreal_amount);
        requestBody.put("booking_id",booking_id);
        requestBody.put("user_id",user_id);
        requestBody.put("owner_id",owner_id);
        requestBody.put("floor_id",floor_id);
        requestBody.put("room_id",room_id);
        requestBody.put("bed_id",bed_id);
        requestBody.put("share",share_type);
        requestBody.put("advance_amount",advance_amount);
        requestBody.put("refundable_amount",refundable_amount);
        presenter.renewBooking(PaymentMethodActivity.this, this, requestBody);
        System.out.println("requestBodyyyyyyyy"+requestBody);



    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_payment_method, null);
        return view;
    }

    private void callPlaceOrderService() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("owner_id", owner_id);
        requestBody.put("floor_id", floor_id);
        requestBody.put("room_id", room_id);
        requestBody.put("bed_id", bed_id);

//
//        requestBody.put("floor_id", "2");
//        requestBody.put("room_id", "2");
//        requestBody.put("bed_id", "2");


        requestBody.put("share", share_type);
        requestBody.put("package_type", package_type);
        requestBody.put("check_in", from_date);
        requestBody.put("check_out", to_date);
        requestBody.put("amount", total_amount);
        requestBody.put("payment_mode", payment_mode);
        requestBody.put("transaction_id", transaction_id);
        requestBody.put("advance_amount", advance_amount);
        requestBody.put("refundable_amount", refundable_amount);
        requestBody.put("joy_wallet_used_amount", joy_wallet);
        requestBody.put("referral_amount_used",Variables.rferal_amountused );
        presenter.placeOrder(PaymentMethodActivity.this, this, requestBody);
        System.out.println("requestBodysgrdy" + requestBody);
    }

    @Override
    public void setPresenter() {
        presenter = new PaymentMethodImp(this, this);
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
            } else if (NetworkConstants.RequestCode.PLACE_ORDER == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    JSONArray jsonArray = jsonObject.optJSONArray("response");

                    /*{"status":true,"message":"Order Placed Successfully!",
                            "response":{"id":"7","booking_id":"174930","user_id":"1","owner_id":"1","hostel_id":"1",
                            "floor_id":"1","room_id":"1","bed_id":"1","share":"1","package_type":"daily",
                            "check_in":"2019-12-05 10:00:00","check_out":"2019-12-31 12:00:00","amount":"11700",
                            "status":"pending","payment_mode":"cash","payment_status":"pending",
                            "current_month_status":"pending","last_payment_date":"2019-12-05 10:00:00",
                            "next_due_date":"2020-01-01 00:00:00","otp":"725900","created_on":"2019-12-05 15:46:05",
                            "modified_on":null}}*/

                    /*JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                    String hostel_id = jsonObject1.optString("hostel_id");
                    String owner_id = jsonObject1.optString("hostel_id");
                    String floor = jsonObject1.optString("floor_id");
                    String room_id = jsonObject1.optString("room_id");
                    String bed_id = jsonObject1.optString("bed_id");
                    String share = jsonObject1.optString("hostel_id");
                    String bed_name = jsonObject1.optString("hostel_id");
                    String room_name = jsonObject1.optString("hostel_id");
                    String owner_name = jsonObject1.optString("hostel_id");
                    String mobile = jsonObject1.optString("hostel_id");
                    String langusges = jsonObject1.optString("hostel_id");
                    String profilepic = jsonObject1.optString("hostel_id");
                    String booking_id = jsonObject1.optString("booking_id");

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

*/


                    Variables.booking_id = jsonArray.optJSONObject(0).optString("id");


                    // Variables.booking_id = jsonObject.optJSONObject("response").optString("UBID");

                    Toast.makeText(PaymentMethodActivity.this, "Placed Successfully", Toast.LENGTH_SHORT).show();
                    System.out.println("gdffgjghjkjhkhcfghn" + jsonArray);

                    Intent i = new Intent(PaymentMethodActivity.this, SecureBookingDetailsActivity.class);
                    startActivity(i);

                    System.out.println("rtutyikyu" + jsonArray);
                    // startActivity(new Intent(PaymentMethodActivity.this, SecureBookingDetailsActivity.class));

                    //startActivityForResult(intent);
                } else {
                    Toast.makeText(context, "Server Error Please Try again the Process", Toast.LENGTH_SHORT).show();

                }
            } else if (NetworkConstants.RequestCode.RENEW_BOOKING == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Toast.makeText(context, "Booking renewed Successfully!", Toast.LENGTH_SHORT).show();
                    //   Variables.booking_id = jsonObject.optJSONObject("response").optString("UBID");

                    Intent intent = new Intent(PaymentMethodActivity.this, HomeActivity.class);
                    intent.putExtra("to", "home");
                    startActivity(intent);

                }
            } else if (NetworkConstants.RequestCode.GENERATE_PAYMENT_TOKEN == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    //   Variables.booking_id = jsonObject.optJSONObject("response").optString("UBID");
                    paymentTokenPojo = new Gson().fromJson(responseString, PaymentTokenPojo.class);
                    Intent intent = new Intent(PaymentMethodActivity.this, CashfreePaymentActivity.class);
                    intent.putExtra("order_id", paymentTokenPojo.getResponse().getOrder_id());
                    intent.putExtra("amount", "1");
                    intent.putExtra("payment_token", paymentTokenPojo.getResponse().getToken());
                    //   startActivity(intent);
                    //   finish();

                    startActivityForResult(intent, reqestcode);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Prints all extras. Replace with app logic.
       /* if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d(TAG, key + " : " + bundle.getString(key));
                    }
                }
        }*/

        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");

                if (result.equalsIgnoreCase("Success")) {
                    transaction_id = data.getStringExtra("reference_id");

                    callPlaceOrderService();
                } else if (result.equalsIgnoreCase("fail")) {
                    Toast.makeText(context, "Transaction failed", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == 1002) {

            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");

                if (result.equalsIgnoreCase("Success")) {
                    renewBookingWs();
                } else if (result.equalsIgnoreCase("fail")) {
                    Toast.makeText(context, "Transaction failed", Toast.LENGTH_SHORT).show();
                }
            }
        }


//        if (requestCode == PayuConstants.PAYU_REQUEST_CODE) {
//            if (data != null) {
//
//                /**
//                 * Here, data.getStringExtra("payu_response") ---> Implicit response sent by PayU
//                 * data.getStringExtra("result") ---> Response received from merchant's Surl/Furl
//                 *
//                 * PayU sends the same response to merchant server and in app. In response check the value of key "status"
//                 * for identifying status of transaction. There are two possible status like, success or failure
//                 * */
//
//
//               /* new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
//                        .setCancelable(false)
//                        .setMessage("Payu's Data : " + data.getStringExtra("payu_response") + "\n\n\n Merchant's Data: " + data.getStringExtra("result"))
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//*/
//
//
//
//            /*    System.out.println("Payu's Data:"+data.getStringExtra("payu_response"));
//                System.out.println("\n\n\n Merchant's Data:"+data.getStringExtra("result"));
//*/
//                try {
//                    JSONObject jsonObject = new JSONObject(data.getStringExtra("payu_response"));
//
//                    transaction_id = jsonObject.getString("txnid");
//                    System.out.println("transaction_id" + transaction_id);
//
//                    String trans_status = jsonObject.getString("status");
//
//                    if (trans_status.equalsIgnoreCase("success")) {
//                        if (from.equalsIgnoreCase("terms")) {
//                            callPlaceOrderService();
//
//                        } else {
//                            renewBookingWs();
//
//                        }
//
//
//                    } else {
//                        Toast.makeText(this, "Oops,Transaction Failed", Toast.LENGTH_LONG).show();
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                Toast.makeText(this, getString(R.string.could_not_receive_data), Toast.LENGTH_LONG).show();
//            }
//        }
    }


    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}

