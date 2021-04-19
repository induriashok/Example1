package com.joystays.joy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gocashfree.cashfreesdk.CFPaymentService;
import com.gocashfree.cashfreesdk.ui.gpay.GooglePayStatusListener;
import com.joystays.joy.R;
import com.joystays.joy.sharepref.UserSession;

import java.util.HashMap;
import java.util.Map;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

public class CashfreePaymentActivity extends AppCompatActivity {
    private static final String TAG = "CashfreePayment";
    private String amount, payment_token, order_id, name, email, number;
    private UserSession userSession;
    private String reference_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_free);

        Intent intent = getIntent();
        amount = intent.getStringExtra("amount");
        order_id = intent.getStringExtra("order_id");
        payment_token = intent.getStringExtra("payment_token");

        userSession = new UserSession(CashfreePaymentActivity.this);
        name = userSession.getUserDetails().get("name");
        email = userSession.getUserDetails().get("email");
        number = userSession.getUserDetails().get("mobileno");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Same request code for all payment APIs.
        Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d(TAG, "API Response : ");
        Log.d("CassFreeResponse", data.getExtras().toString());
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d(TAG, key + " : " + bundle.getString(key));


                        if(key.equalsIgnoreCase("referenceId")){
                            if (bundle.getString(key)!=null) {
                                reference_id=bundle.getString(key);
                            }

                        }

                        if (key.equalsIgnoreCase("txStatus")) {
                            if (bundle.getString(key).equalsIgnoreCase("SUCCESS")) {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result", "Success");
                                returnIntent.putExtra("reference_id", reference_id);
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                                break;
                            } else {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result", "fail");
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                                break;
                            }
                        }


                        //  Toast.makeText(CashfreePaymentActivity.this, "Order Placed Successful", Toast.LENGTH_SHORT).show();
                    }
                }
        } else {
        }
    }

    public void onClick(View view) {
        /*
         * stage allows you to switch between sandboxed and production servers
         * for CashFree Payment Gateway. The possible values are
         *
         * 1. TEST: Use the Test server. You can use this service while integrating
         *      and testing the CashFree PG. No real money will be deducted from the
         *      cards and bank accounts you use this stage. This mode is thus ideal
         *      for use during the development. You can use the cards provided here
         *      while in this stage: https://docs.cashfree.com/docs/resources/#test-data
         *
         * 2. PROD: Once you have completed the testing and integration and successfully
         *      integrated the CashFree PG, use this value for stage variable. This will
         *      enable live transactions
         */
        String stage = "PROD";

        //Show the UI for doGPayPayment and phonePePayment only after checking if the apps are ready for payment
        if (view.getId() == R.id.phonePe_exists) {
            Toast.makeText(
                    CashfreePaymentActivity.this,
                    CFPaymentService.getCFPaymentServiceInstance().doesPhonePeExist(CashfreePaymentActivity.this, stage) + "",
                    Toast.LENGTH_SHORT).show();
            return;
        } else if (view.getId() == R.id.gpay_ready) {
            CFPaymentService.getCFPaymentServiceInstance().isGPayReadyForPayment(CashfreePaymentActivity.this, new GooglePayStatusListener() {
                @Override
                public void isReady() {
                    Toast.makeText(CashfreePaymentActivity.this, "Ready", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void isNotReady() {
                    Toast.makeText(CashfreePaymentActivity.this, "Not Ready", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        /*
         * token can be generated from your backend by calling cashfree servers. Please
         * check the documentation for details on generating the token.
         * READ THIS TO GENERATE TOKEN: https://bit.ly/2RGV3Pp
         */


        /*
         * appId will be available to you at CashFree Dashboard. This is a unique
         * identifier for your app. Please replace this appId with your appId.
         * Also, as explained below you will need to change your appId to prod
         * credentials before publishing your app.
         */
        //  String appId = "13148ca567c8dac426fc6ec2784131";   // Test APP ID
        String appId = "415285b38e1f9a9414dca2ab682514";     //  Live APP ID
        String orderId = order_id;
        String orderAmount = amount;
        String orderNote = "Prod Order";
        String customerName = name;
        String customerPhone = number;
        String customerEmail = email;

        Map<String, String> params = new HashMap<>();

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, orderId);
        params.put(PARAM_ORDER_AMOUNT, orderAmount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);


        for (Map.Entry entry : params.entrySet()) {
            Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
        }

        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);
        switch (view.getId()) {

            /***
             * This method handles the payment gateway invocation (web flow).
             *
             * @param context Android context of the calling activity
             * @param params HashMap containing all the parameters required for creating a payment order
             * @param token Provide the token for the transaction
             * @param stage Identifies if test or production service needs to be invoked. Possible values:
             *              PROD for production, TEST for testing.
             * @param color1 Background color of the toolbar
             * @param color2 text color and icon color of toolbar
             * @param hideOrderId If true hides order Id from the toolbar
             */
            case R.id.web: {
                cfPaymentService.doPayment(CashfreePaymentActivity.this, params, payment_token, stage, "#784BD2", "#FFFFFF", false);
//                 cfPaymentService.doPayment(CashfreePaymentActivity.this, params, token, stage);
                break;
            }
            /***
             * Same for all payment modes below.
             *
             * @param context Android context of the calling activity
             * @param params HashMap containing all the parameters required for creating a payment order
             * @param token Provide the token for the transaction
             * @param stage Identifies if test or production service needs to be invoked. Possible values:
             *              PROD for production, TEST for testing.
             */
            case R.id.upi: {
//                                cfPaymentService.selectUpiClient("com.google.android.apps.nbu.paisa.user");
                cfPaymentService.upiPayment(CashfreePaymentActivity.this, params, payment_token, stage);
                break;
            }
            case R.id.amazon: {
                cfPaymentService.doAmazonPayment(CashfreePaymentActivity.this, params, payment_token, stage);
                break;
            }
            case R.id.gpay: {
                cfPaymentService.gPayPayment(CashfreePaymentActivity.this, params, payment_token, stage);
                break;
            }
            case R.id.phonePe: {
                cfPaymentService.phonePePayment(CashfreePaymentActivity.this, params, payment_token, stage);
                break;
            }
        }
    }
}
