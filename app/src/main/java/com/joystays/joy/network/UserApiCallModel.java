package com.joystays.joy.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joystays.joy.R;


import java.util.Map;

import retrofit2.Call;

public class UserApiCallModel extends BaseApiModel {
    Context context;
    APIResponseCallback apiResponseCallback;
    APIInterface apiInterface;
    APIHandler apiHandler;
    Map params;

    public UserApiCallModel(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        super(context);
        this.apiResponseCallback = apiResponseCallback;
        this.context = context;
        this.params = requestBody;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }


    public void login(int requestId, Map params) {
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put(params);
        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.login(res);
 /* public APIHandler(Context context, APICallback apiCallback, int requestId, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, Map<String, String> params, Call<Object> call) {*/
       /* apiHandler = new APIHandler(context, this, requestId,
                true, false,
                context.getString(R.string.pleasewait), call);
        apiHandler.requestAPI();*/
        commoncall(requestId, call);
    }

    public void signUp(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.signUp(res);
        commoncall(requestId, call);
    }

    public void forgotPassword(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.forgotPassword(res);
        commoncall(requestId, call);
    }

    public void getPoll(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.getPoll(res);
        commoncall(requestId, call);
    }

    public void send_polls(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.send_polls(res);
        commoncall(requestId, call);
    }

    public void update_device_token(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.update_device_token(res);
        commoncall(requestId, call);
    }
    public void check_hostel_prices(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.check_hostel_prices(res);
        commoncall(requestId, call);
    }

    public void user_chats(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.user_chats(res);
        commoncall(requestId, call);
    }

    public void get_banners(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.get_banners(res);
        commoncall(requestId, call);
    }




    public void notifications(int requestId, Map params) {
        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.notifications(res);
        commoncall(requestId, call);

    }

    public void placeOrder(int requestId, Map params) {
        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.placeOrder(res);
        commoncall(requestId, call);

    }
   public void renewBooking(int requestId, Map params) {
        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.renewBooking(res);
        commoncall(requestId, call);

    }

    public void generatePaymentToken(int requestId, Map params) {
        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.generatePaymentToken(res);
        commoncall(requestId, call);

    }

    public void nearhostels(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.nearHostels(res);
        commoncall(requestId, call);
    }

    public void search(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.nearHostels(res);
        commoncall1(requestId, call);
    }

    public void favHostels(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.favHostels(res);
        commoncall(requestId, call);
    }

    public void myBookings(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.myBookings(res);
        commoncall(requestId, call);
    }


    public void hostel_floors(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.hostel_floors(res);
        commoncall(requestId, call);
    }

    public void myWallet(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.myWallet(res);
        commoncall(requestId, call);
    }

    public void roommateDetails(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.roommateDetails(res);
        commoncall(requestId, call);
    }

    public void vacateRequest(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.vacateRequest(res);
        commoncall(requestId, call);
    }

    public void triprequest(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.triprequest(res);
        commoncall(requestId, call);
    }

    public void userProfile(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.userProfile(res);
        commoncall(requestId, call);
    }

    public void my_trips(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.my_trips(res);
        commoncall(requestId, call);
    }

    public void extend_trip_request(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.extend_trip_request(res);
        commoncall(requestId, call);
    }

    public void my_vacant_request(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.my_vacant_request(res);
        commoncall(requestId, call);
    }

    public void extend_vacant_request(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.extend_vacant_request(res);
        commoncall(requestId, call);
    }

    public void updateProfile(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.updateProfile(res);
        commoncall(requestId, call);
    }

    public void bookingDetails(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.bookingDetails(res);
        commoncall(requestId, call);
    }

    public void cancelBooking(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.cancelBooking(res);
        commoncall(requestId, call);
    }

    public void setFavourite(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.setFavourite(res);
        commoncall(requestId, call);
    }

    public void user_booking_details(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.user_booking_details(res);
        commoncall(requestId, call);
    }

    public void submit_polling(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.submit_polling(res);
        commoncall(requestId, call);
    }

    public void hostelMenu(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.hostelMenu(res);
        commoncall(requestId, call);
    }

    public void lockDetails(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.lockDetails(res);
        commoncall(requestId, call);
    }

    public void lockRoom(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.lockRoom(res);
        commoncall(requestId, call);
    }

    public void compaint(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.complaint(res);
        commoncall(requestId, call);
    }

    public void getBeds(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.getBeds(res);
        commoncall(requestId, call);
    }

    public void getRooms(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.getRooms(res);
        commoncall(requestId, call);
    }

    public void hostelDetails(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.hostelDetails(res);
        commoncall(requestId, call);
    }

    public void hostelPrices(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.hostelPrices(res);
        commoncall(requestId, call);
    }

    public void submit_rating(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.submitRating(res);
        commoncall(requestId, call);
    }
  public void openPopUpRatings(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.openPopUpRatings(res);
        commoncall(requestId, call);
    }

    public void reset_password(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.reset_password(res);
        commoncall(requestId, call);
    }

    public void checkout_calcations(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.checkout_calcations(res);
        commoncall(requestId, call);
    }

    public void transactions(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.transactions(res);
        commoncall(requestId, call);
    }

    public void changeroom(int requestId, Map params) {

        Gson gsone = new Gson();
        JsonObject res = gsone.toJsonTree(params).getAsJsonObject();
        Call<Object> call = apiInterface.changeroom(res);
        commoncall(requestId, call);
    }

    //------------------------------------------------------------------------------------------------------
    public void commoncall(int requestId, Call<Object> call) {
        apiHandler = new APIHandler(context, this, requestId,
                true, false,
                context.getString(R.string.pleasewait), call);
        apiHandler.requestAPI(params);
    }

    public void commoncall1(int requestId, Call<Object> call) {
        apiHandler = new APIHandler(context, this, requestId,
                false, false,
                context.getString(R.string.pleasewait), call);
        apiHandler.requestAPI(params);
    }


    @Override
    public void onAPISuccessResponse(int requestId, String responseString) {
        super.onAPISuccessResponse(requestId, responseString);
        Log.e("TAG", "ON Success Responses");
        apiResponseCallback.onSuccessResponse(requestId, responseString, "");
    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        apiResponseCallback.onSuccessResponse(requestId, errorString, "");
    }

}
