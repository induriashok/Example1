package com.joystays.joy.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.google.gson.Gson;
import com.joystays.joy.R;


import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Prasad on 6/20/2017.
 */
public class APIHandler implements IDialogCallback {


    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private Context context;
    private int requestId;
    private int methodType;
    private boolean showLoading = false;
    private String loadingText;
    private String url;
    private ProgressDialog pDialog = null;
    private APICallback apiCallback = null;
    private Map<String, String> headers = null;
    private Map<String, String> params = null;
    private boolean showToastOnResponse = true;
    private boolean isCanceledOnTouchOutside = false;
    Call<Object> call;

    public APIHandler(Context context, APICallback apiCallback, int requestId,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, Call<Object> call) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.params = params;
        this.call = call;
    }

    /**
     * Show loading.
     */


  /*  private void showLoading() {
        try {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage(loadingText);
            pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            pDialog.show();
        } catch (Exception e) {
            Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
        }
    }*/




    private void showLoading() {


        try {
// pDialog = new ProgressDialog(context);
// pDialog.setMessage(loadingText);
// pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
// pDialog.show();

            pDialog = new ProgressDialog(context);
// pDialog = new ProgressDialog(context);
            pDialog.getWindow().setBackgroundDrawable(new
                    ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.setContentView(R.layout.progressxml);
            pDialog.setMessage(loadingText);
// pDialog.setIndeterminate(true);
            pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            pDialog.show();


        } catch (Exception e) {
            Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
        }
    }













        /**
         * Hide loading.
         */
    private void hideLoading() {
        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Request API to get response for respective request.
     *
     * @param params
     */
    public void requestAPI(Map params) {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
                //  Util.getInstance().cusToast(context,networkErrorBody);
            } else {
               /* System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));*/

                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                retrofitServiceCall(call, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrofitServiceCall(final Call<Object> call, final Map params) throws IOException {

        call.clone().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                try {
                    if (pDialog != null && pDialog.isShowing())
                        pDialog.dismiss();
                    String serverResponse = new Gson().toJson(response.body());
                    //dismiss dialog once response has been received from the server.
                    if (serverResponse != null && serverResponse.length() > 0) {
                        //send the response the particular model where all data will parse their.
                    }
                    handelResponse(requestId, serverResponse);

                    Log.d("Response>>>",  "  params  " + params+ serverResponse );
                    // Toast.makeText(context,serverResponse,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());
                // Log error here since request failed
                if (pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();
                t.printStackTrace();
            }
        });
        call.execute();
    }


/*
    private void sendRequest(final Map<String, String> params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
                            error.printStackTrace();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> paramsHeader = new HashMap<>();
                    paramsHeader.put("Content-Type", "application/json");
                  //  paramsHeader.put("Authorization", "Basic RW1tc2g6RW1tc2hAMjAxOA==");
                    paramsHeader.put("Authorization", " Basic aW5jZWVudTppbmNlZW51QDIwMTg=");
                    return paramsHeader;
                }
            };
Log.i("info",new JSONObject(params).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setJsonRequestQueue(context,jsonRequest);
        //MyApplication.getInstance().addToRequestQueue(jsonRequest, requestId);
    }
*/

/*
    private void setJsonRequestQueue(Context context, JsonObjectRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
*/

    /**
     * Handel The API response
     *
     * @param requestId
     * @param response
     */
    private void handelResponse(int requestId, String response) {
        Gson gson = new Gson();
        CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);
        if (commonResponse != null) {
            String message = commonResponse.getMessage();
            int status_code = commonResponse.getStatus_code();

            switch (status_code) {
                case NetworkConstants.RequestCode.SESSION_EXPIRE:
                    Utils.showErrorDialog(context, context.getString(R.string.ok_txt), message,
                            true, this, NetworkConstants.RequestCode.SESSION_EXPIRE);
                    break;
                default:
                    try {
                        apiCallback.onAPISuccessResponse(requestId, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    /**
     * If any error occurs during data parsing then all error will handle here only.
     *
     * @param error
     */

    /**
     * Check for error msg from volly
     *
     * @param error
     * @param defaultMessage
     * @return
     */
    private String getMessage(String error, String defaultMessage) {
        String finalMsg = null;
        if (error == null || error.isEmpty()) {
            finalMsg = defaultMessage;
        } else {
            finalMsg = error;
        }

        return finalMsg;
    }

    public boolean isShowToastOnResponse() {
        return showToastOnResponse;
    }

    public void setShowToastOnResponse(boolean showToastOnResponse) {
        this.showToastOnResponse = showToastOnResponse;
    }

    @Override
    public void onPositiveButtonPress(int requestCode) {

        switch (requestCode) {
            case NetworkConstants.RequestCode.SESSION_EXPIRE:
                //TODO call logout logic
                break;
        }

    }

    @Override
    public void onNegativeButtonPress(int requestCode) {

    }
}
