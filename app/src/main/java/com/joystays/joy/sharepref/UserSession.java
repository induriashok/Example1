package com.joystays.joy.sharepref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by iprismTech on 12/07/2017.
 */

public class UserSession {
    // Shared Preferences reference
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    public static final String PREFER_NAME = "Joy";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USERID = "id";
    public static final String KEY_UserName = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILENO = "mobileno";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SESSION_TYPE = "session_type";
    public static final String KEY_DEVICE_TOKEN = "devicetoken";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_BED_CONFIRMED = "bed_confirmed";
    public static final String KEY_IMAGE = "image";

    public static final String KEY_HOSTEL_ID = "hostel_id";
    public static final String KEY_OWNER_ID = "owner_id";
    public static final String KEY_FLOOR = "floor";
    public static final String KEY_ROOM_ID = "room_id";
    public static final String KEY_BED_ID = "bed_id";
    public static final String KEY_SHARE = "share";
    public static final String KEY_BED_NAME = "bed_name";
    public static final String KEY_ROOM_NAME = "room_name";
    public static final String KEY_APPROVED = "approved";
    public static final String KEY_OWNER_NAME = "owner_name";
    public static final String KEY_OWNER_NUMBER = "owner_number";
    public static final String KEY_OWNER_LANGUAGES = "owner_languages";
    public static final String KEY_OWNER_PIC = "owner_pic";
    public static final String KEY_BOOKING_ID = "booking_id";
    public static final String KEY_PASSWORD = "password";

    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setlanguage(String session_type) {

        editor.putString(KEY_SESSION_TYPE, session_type);
        editor.commit();
    }

    //Create login session
    public void StoreUserDetails(String id, String username, String mobile, String emailId, String bed_confirmed, String token,String image,String password) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USERID, id);
        // Storing name in preferences
        editor.putString(KEY_UserName, username);
        // Storing mobile in preferences
        editor.putString(KEY_MOBILENO, mobile);
        // Storing email in preferences
        editor.putString(KEY_EMAIL, emailId);
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_BED_CONFIRMED, bed_confirmed);
        editor.putString(KEY_IMAGE, image);
        editor.putString(KEY_PASSWORD, password);

        editor.commit();
    }

    public void storeHostel_details(String approved, String hostel_id, String owner_id, String floor, String room_id,
                                    String bed_id, String share, String bed_name, String room_name, String owner_name,
                                    String owner_number, String owner_languages, String owner_pic,String booking_id) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_APPROVED, approved);
        editor.putString(KEY_HOSTEL_ID, hostel_id);
        editor.putString(KEY_BOOKING_ID, booking_id);


        // Storing name in preferences
        editor.putString(KEY_OWNER_ID, owner_id);
        // Storing mobile in preferences
        editor.putString(KEY_FLOOR, floor);
        // Storing email in preferences
        editor.putString(KEY_ROOM_ID, room_id);
        editor.putString(KEY_BED_ID, bed_id);
        editor.putString(KEY_SHARE, share);
        editor.putString(KEY_BED_NAME, bed_name);
        editor.putString(KEY_ROOM_NAME, room_name);
        editor.putString(KEY_OWNER_NAME, owner_name);
        editor.putString(KEY_OWNER_NUMBER, owner_number);
        editor.putString(KEY_OWNER_LANGUAGES, owner_languages);
        editor.putString(KEY_OWNER_PIC, owner_pic);
        editor.commit();
    }
//    public boolean checkLogin() {
//        // Check login status
//        if (!this.isUserLoggedIn()) {
//
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//
//            // Closing all the Activities from stack
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
//
//            return true;
//        }
//        return false;
//    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERID, pref.getString(KEY_USERID, null));
        // getting name from sharedPreferences
        user.put(KEY_UserName, pref.getString(KEY_UserName, null));
        // getting name from sharedPreferences
        user.put(KEY_MOBILENO, pref.getString(KEY_MOBILENO, null));
        // getting name from sharedPreferences
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        user.put(KEY_APPROVED, pref.getString(KEY_APPROVED, ""));
        user.put(KEY_BED_ID, pref.getString(KEY_BED_ID, null));
        user.put(KEY_ROOM_ID, pref.getString(KEY_ROOM_ID, null));
        user.put(KEY_SHARE, pref.getString(KEY_SHARE, null));
        user.put(KEY_OWNER_ID, pref.getString(KEY_OWNER_ID, null));
        user.put(KEY_HOSTEL_ID, pref.getString(KEY_HOSTEL_ID, null));

        user.put(KEY_OWNER_NAME, pref.getString(KEY_OWNER_NAME, ""));
        user.put(KEY_OWNER_NUMBER, pref.getString(KEY_OWNER_NUMBER, ""));
        user.put(KEY_OWNER_LANGUAGES, pref.getString(KEY_OWNER_LANGUAGES, ""));
        user.put(KEY_OWNER_PIC, pref.getString(KEY_OWNER_PIC, ""));
        user.put(KEY_BOOKING_ID, pref.getString(KEY_BOOKING_ID, ""));

        //user.put(NATIONALITY_ID, pref.getString(NATIONALITY_ID, null));

        user.put(KEY_FLOOR, pref.getString(KEY_FLOOR, null));
        user.put(KEY_BED_CONFIRMED, pref.getString(KEY_BED_CONFIRMED, ""));
        user.put(KEY_IMAGE, pref.getString(KEY_IMAGE, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_OWNER_LANGUAGES, pref.getString(KEY_OWNER_LANGUAGES, null));

        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    /**
     * When user is logout then change the login screen details as user logout.
     */
    public void changeUserLoginScreenStatus() {
        editor.putBoolean(IS_USER_LOGIN, false);
        editor.commit();
    }

    // set the status of the user for read and unread here.
    public void setCheckTutorialScreenStatus(String status) {

        editor.putString(KEY_STATUS, status);

        editor.commit();
    }

    public void setDeviceToken(String deviceToken) {

        editor.putString(KEY_DEVICE_TOKEN, deviceToken);

        editor.commit();

    }

    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }


}
