package com.joystays.joy.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    // @Headers("Content-Type" "application/json","Authorization" :" Basic aW5jZWVudTppbmNlZW51QDIwMTg="})
    @Headers({
            "Authorization: Basic aW5jZWVudTppbmNlZW51QDIwMTg=",
            "Content-Type: application/json"
    })
        @POST(NetworkConstants.URL.LOGIN)
        Call<Object> login(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.SIGNUP)
    Call<Object> signUp(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.USER_COMPLAINT)
    Call<Object> complaint(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.SUBMIT_RATING)
    Call<Object> submitRating(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.RATING_CHECK)
    Call<Object> openPopUpRatings(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.FORGOT_PASSWORD)
    Call<Object> forgotPassword(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.GET_FOOD_POLL)
    Call<Object> getPoll(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.NOTIFICATIONS)
    Call<Object> notifications(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.RESET_PASSWORD)
    Call<Object> reset_password(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.NEAR_HOSTELS)
    Call<Object> nearHostels(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.FAV_HOSTELS)
    Call<Object> favHostels(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.SET_FAV)
    Call<Object> setFavourite(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.USER_BOOKING_DETAILS)
    Call<Object> user_booking_details(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.SUBMIT_POLLING)
    Call<Object> submit_polling(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.ROOMMATE_DETAILS)
    Call<Object> roommateDetails(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_DETAILS)
    Call<Object> hostelDetails(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_PRICES)
    Call<Object> hostelPrices(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_BEDS)
    Call<Object> getBeds(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_ROOMS)
    Call<Object> getRooms(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.MY_BOOKINGS)
    Call<Object> myBookings(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.MY_WALLET)
    Call<Object> myWallet(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.USER_PROFILE)
    Call<Object> userProfile(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.MY_TRIPS)
    Call<Object> my_trips(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.EXTEND_TRIP)
    Call<Object> extend_trip_request(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.MY_VACANT_REQUEST)
    Call<Object> my_vacant_request(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.EXTEND_VACATE_REQUEST)
    Call<Object> extend_vacant_request(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.UPDATE_PROFILE)
    Call<Object> updateProfile(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.CHECKOUT_CALCULATIONS)
    Call<Object> checkout_calcations(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.BOOKING_DETAILS)
    Call<Object> bookingDetails(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.CANCEL_BOOKING)
    Call<Object> cancelBooking(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.PLACE_ORDER)
    Call<Object> placeOrder(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.RENEW_BOOKING)
    Call<Object> renewBooking(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.GENERATE_PAYMENT_TOKEN)
    Call<Object> generatePaymentToken(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_FLOORS)
    Call<Object> hostel_floors(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.TRANSACTIONS)
    Call<Object> transactions(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.HOSTEL_MENU)
    Call<Object> hostelMenu(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.LOCK_DETAILS)
    Call<Object> lockDetails(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.LOCK_ROOM)
    Call<Object> lockRoom(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.VACATE_REQUEST)
    Call<Object> vacateRequest(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.TRIP_REQUEST)
    Call<Object> triprequest(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.CHANGE_ROOM)
    Call<Object> changeroom(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.SEND_POLL_LIKE)
    Call<Object> send_polls(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.UPDATE_TOKEN)
    Call<Object> update_device_token(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.CHECK_SHARE_PRICES)
    Call<Object> check_hostel_prices(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.USER_CHATS)
    Call<Object> user_chats(@Body JsonObject jsonObject);

    @POST(NetworkConstants.URL.GET_BANNERS)
    Call<Object> get_banners(@Body JsonObject jsonObject);



}
