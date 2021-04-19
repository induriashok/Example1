package com.joystays.joy.network;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.joystays.joy.network.NetworkConstants.RequestCode.BOOKING_DETAILS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.CANCEL_BOOKING;
import static com.joystays.joy.network.NetworkConstants.RequestCode.CHANGE_ROOM;
import static com.joystays.joy.network.NetworkConstants.RequestCode.CHECKOUT_CALCULATIONS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.CHECK_SHARE_PRICES;
import static com.joystays.joy.network.NetworkConstants.RequestCode.EXTEND_TRIP;
import static com.joystays.joy.network.NetworkConstants.RequestCode.EXTEND_VACATE_REQUEST;
import static com.joystays.joy.network.NetworkConstants.RequestCode.FAV_HOSTELS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.FORGOT_PASSWORD;
import static com.joystays.joy.network.NetworkConstants.RequestCode.GENERATE_PAYMENT_TOKEN;
import static com.joystays.joy.network.NetworkConstants.RequestCode.GET_FOOD_POLL;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_BEDS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_DETAILS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_FLOORS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_MENU;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_PRICES;
import static com.joystays.joy.network.NetworkConstants.RequestCode.HOSTEL_ROOMS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.LOCK_DETAILS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.GET_BANNERS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.LOCK_ROOM;
import static com.joystays.joy.network.NetworkConstants.RequestCode.MY_BOOKINGS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.MY_TRIPS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.MY_VACANT_REQUEST;
import static com.joystays.joy.network.NetworkConstants.RequestCode.MY_WALLET;
import static com.joystays.joy.network.NetworkConstants.RequestCode.NEAR_HOSTELS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.NOTIFICATIONS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.PLACE_ORDER;
import static com.joystays.joy.network.NetworkConstants.RequestCode.RATING_CHECK;
import static com.joystays.joy.network.NetworkConstants.RequestCode.RENEW_BOOKING;
import static com.joystays.joy.network.NetworkConstants.RequestCode.RESET_PASSWORD;
import static com.joystays.joy.network.NetworkConstants.RequestCode.ROOMMATE_DETAILS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.SEND_POLL_LIKE;
import static com.joystays.joy.network.NetworkConstants.RequestCode.SET_FAV;
import static com.joystays.joy.network.NetworkConstants.RequestCode.SUBMIT_POLLING;
import static com.joystays.joy.network.NetworkConstants.RequestCode.SUBMIT_RATING;
import static com.joystays.joy.network.NetworkConstants.RequestCode.TRANSACTIONS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.TRIP_REQUEST;
import static com.joystays.joy.network.NetworkConstants.RequestCode.UPDATE_PROFILE;
import static com.joystays.joy.network.NetworkConstants.RequestCode.UPDATE_TOKEN;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_BOOKING_DETAILS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_CHATS;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_COMPLAINT;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_LOGIN;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_PROFILE;
import static com.joystays.joy.network.NetworkConstants.RequestCode.USER_SIGNUP;
import static com.joystays.joy.network.NetworkConstants.RequestCode.VACATE_REQUEST;


public interface NetworkConstants {
    String PAGE = "?page=";
    String LIMIT = "&limit=";

    interface Headers {
        String X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String BASIC_AUTH_TOKEN = "Authorization";
    }

    /**
     * This is the network request to all apis.
     */
    interface URL {

//        String Imagepath_URL = "http://appilabz.com/hostel/";
//        String BASE_URL = "http://appilabz.com/hostel/app/Ws/";

/*
        String Imagepath_URL = "http://appilabz.com/joyhostel/";
        String BASE_URL = "http://appilabz.com/joyhostel/app/ws/";
*/


        String Imagepath_URL = "http://joystays.in/";
        String BASE_URL = "http://joystays.in/app/ws/";


        String LOGIN = BASE_URL + "user_login";
        String SIGNUP = BASE_URL + "register_user";
        String USER_COMPLAINT = BASE_URL + "submit_complaint";
        String SUBMIT_RATING = BASE_URL + "submit_ratings";
        String FORGOT_PASSWORD = BASE_URL + "forgot_password";
        String RESET_PASSWORD = BASE_URL + "update_password";
        String NEAR_HOSTELS = BASE_URL + "near_by_hostels";
        String HOSTEL_DETAILS = BASE_URL + "hostel_details";
        String HOSTEL_PRICES = BASE_URL + "hostel_prices";
        String HOSTEL_BEDS = BASE_URL + "hostel_beds";
        String HOSTEL_ROOMS = BASE_URL + "hostel_rooms";
        String SET_FAV = BASE_URL + "submit_as_favourite_hostel";
        String ROOMMATE_DETAILS = BASE_URL + "roommate_details";
        String FAV_HOSTELS = BASE_URL + "favourite_hostels";
        String MY_BOOKINGS = BASE_URL + "my_bookings";
        String NOTIFICATIONS = BASE_URL + "notifications";
        String USER_PROFILE = BASE_URL + "user_profile";
        String UPDATE_PROFILE = BASE_URL + "update_profile";
        String CHECKOUT_CALCULATIONS = BASE_URL + "user_amount_calculations";
        String PLACE_ORDER = BASE_URL + "place_order";
        String TRANSACTIONS = BASE_URL + "user_payments";
        String BOOKING_DETAILS = BASE_URL + "booking_details";
        String CANCEL_BOOKING = BASE_URL + "user_cancel_booking";
        String MY_WALLET = BASE_URL + "my_wallet";
        String HOSTEL_MENU = BASE_URL + "hostel_menu";
        String LOCK_DETAILS = BASE_URL + "roommate_details_locks";
        String LOCK_ROOM = BASE_URL + "lock_room";
        String VACATE_REQUEST = BASE_URL + "submit_vacant_request";
        String TRIP_REQUEST = BASE_URL + "submit_intrip_request";
        String MY_TRIPS = BASE_URL + "my_trips";
        String EXTEND_TRIP = BASE_URL + "extend_trip_request";
        String MY_VACANT_REQUEST = BASE_URL + "my_vacant_request";
        String EXTEND_VACATE_REQUEST = BASE_URL + "extend_vacant_request";
        String USER_BOOKING_DETAILS = BASE_URL + "user_booking_details";

        String HOSTEL_FLOORS = BASE_URL + "hostel_floors";
        String SUBMIT_POLLING = BASE_URL + "submit_pollings";
        String CHANGE_ROOM = BASE_URL + "submit_room_change_request";
        String RENEW_BOOKING = BASE_URL + "renew_booking";
        String GET_FOOD_POLL = BASE_URL + "poll_notifications";
        String SEND_POLL_LIKE = BASE_URL + "submit_poll_notifications_response";
        String UPDATE_TOKEN = BASE_URL + "update_device_token";
        String CHECK_SHARE_PRICES = BASE_URL + "check_hostel_prices";
        String USER_CHATS = BASE_URL + "user_messages";
        String GENERATE_PAYMENT_TOKEN = BASE_URL + "payment_process";
        String RATING_CHECK = BASE_URL + "open_popup";
        String GET_BANNERS = BASE_URL + "get_banners";

    }

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef(value = {
            USER_LOGIN, CHANGE_ROOM,RENEW_BOOKING,GET_FOOD_POLL, SUBMIT_POLLING, MY_VACANT_REQUEST, USER_BOOKING_DETAILS, EXTEND_TRIP, EXTEND_VACATE_REQUEST, USER_SIGNUP, MY_TRIPS, VACATE_REQUEST, TRIP_REQUEST, MY_WALLET, LOCK_ROOM, LOCK_DETAILS, TRANSACTIONS, UPDATE_PROFILE, CANCEL_BOOKING, HOSTEL_MENU, BOOKING_DETAILS, PLACE_ORDER, USER_PROFILE, MY_BOOKINGS, NOTIFICATIONS, SET_FAV, ROOMMATE_DETAILS, FAV_HOSTELS, CHECKOUT_CALCULATIONS, HOSTEL_ROOMS, HOSTEL_BEDS, USER_COMPLAINT, SUBMIT_RATING, FORGOT_PASSWORD, RESET_PASSWORD, NEAR_HOSTELS, HOSTEL_DETAILS, HOSTEL_PRICES,
            HOSTEL_FLOORS,SEND_POLL_LIKE,UPDATE_TOKEN,CHECK_SHARE_PRICES,USER_CHATS,GENERATE_PAYMENT_TOKEN,RATING_CHECK,GET_BANNERS
    })
    @interface RequestCode {
        int SESSION_EXPIRE = 1017;
        int USER_LOGIN = 100;
        int USER_SIGNUP = 101;
        int USER_COMPLAINT = 102;
        int SUBMIT_RATING = 103;
        int FORGOT_PASSWORD = 104;
        int RESET_PASSWORD = 105;
        int NEAR_HOSTELS = 106;
        int HOSTEL_DETAILS = 107;
        int HOSTEL_PRICES = 108;
        int HOSTEL_BEDS = 109;
        int HOSTEL_ROOMS = 110;
        int SET_FAV = 111;
        int ROOMMATE_DETAILS = 112;
        int FAV_HOSTELS = 113;
        int MY_BOOKINGS = 114;
        int NOTIFICATIONS = 115;
        int USER_PROFILE = 116;
        int UPDATE_PROFILE = 117;
        int CHECKOUT_CALCULATIONS = 118;
        int PLACE_ORDER = 119;
        int TRANSACTIONS = 120;
        int BOOKING_DETAILS = 121;
        int CANCEL_BOOKING = 122;
        int MY_WALLET = 123;
        int HOSTEL_MENU = 124;
        int LOCK_DETAILS = 125;
        int LOCK_ROOM = 126;
        int VACATE_REQUEST = 127;
        int TRIP_REQUEST = 128;
        int MY_TRIPS = 129;
        int EXTEND_TRIP = 130;
        int MY_VACANT_REQUEST = 132;
        int EXTEND_VACATE_REQUEST = 133;
        int USER_BOOKING_DETAILS = 134;
        int HOSTEL_FLOORS = 135;

        int CHANGE_ROOM = 136;
        int SUBMIT_POLLING = 137;
        int RENEW_BOOKING = 138;
        int GET_FOOD_POLL = 139;
        int SEND_POLL_LIKE = 140;
        int UPDATE_TOKEN = 141;
        int CHECK_SHARE_PRICES = 142;
        int USER_CHATS = 143;
        int GENERATE_PAYMENT_TOKEN = 144;
        int RATING_CHECK = 145;
        int GET_BANNERS = 146;

    }
}
