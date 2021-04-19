package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ChangeRoomActContract;
import com.joystays.joy.mvp.presenter.activity.ChangeRoomImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeRoomActivity extends BaseAbstractActivity<ChangeRoomImpl> implements ChangeRoomActContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow;
    private LinearLayout linear_save,bed_ll,room_ll,floor_ll;
    private Spinner floor_spinner,room_spinner,bed_spinner;
    private TextView tv_mesg,note_user_tv;

    List<String> hostefloors = new ArrayList();
    List<String> hostelfloorname = new ArrayList();

    List<String> hosteroomid = new ArrayList();
    List<String> hostelroomname = new ArrayList();
    List<String> hostelshare = new ArrayList();
    List<String> shareprice = new ArrayList();
    List<String> amount_type = new ArrayList();
    List<String> room_typelist = new ArrayList();

    List<String> hostebedid = new ArrayList();
    List<String> hostelbedname = new ArrayList();

    String check_in;

    RadioButton rb_ac,rb_nonac;


    String str_hostefloor = "",str_room_id,str_bed_id,str_msg,to_share,share_price,amout_status,to_room_type,ac_status="no";
    String str_hostelfloorname = "",str_room_name,str_bed_name,user_id,token,hostel_id,floor,room_id,bed_id,frm_share,owner_id;
    UserSession userSession;

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
        linear_save = findViewById(R.id.linear_save);

        room_spinner = findViewById(R.id.room_spinner);
        floor_spinner = findViewById(R.id.floor_spinner);
        bed_spinner = findViewById(R.id.bed_spinner);
        tv_mesg = findViewById(R.id.tv_mesg);
        note_user_tv = findViewById(R.id.note_user_tv);
        bed_ll = findViewById(R.id.bed_ll);
        room_ll = findViewById(R.id.room_ll);
        floor_ll = findViewById(R.id.floor_ll);
        rb_ac = findViewById(R.id.rb_ac);
        rb_nonac = findViewById(R.id.rb_nonac);



        userSession = new UserSession(ChangeRoomActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        floor = userSession.getUserDetails().get("floor");
        room_id = userSession.getUserDetails().get("room_id");
        bed_id = userSession.getUserDetails().get("bed_id");
        frm_share = userSession.getUserDetails().get("share");
        owner_id = userSession.getUserDetails().get("owner_id");

        gethostelfloorsWBS();


        rb_nonac.setChecked(true);
        rb_ac.setChecked(false);

        rb_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac_status = "yes";
                rb_nonac.setChecked(false);
                rb_ac.setChecked(true);
                gethostelfloorsWBS();


            }
        });


        rb_nonac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac_status = "no";
                rb_nonac.setChecked(true);
                rb_ac.setChecked(false);
                gethostelfloorsWBS();


            }
        });

        floor_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                str_hostefloor = hostefloors.get(position);
                str_hostelfloorname = hostelfloorname.get(position);
                System.out.println("str_hostefloor"+str_hostefloor);

                gethostelroomsWBS();


                //  Log.e(TAG, "str_hostellistid: "+str_hostellistid );
                //  Log.e(TAG, "str_hostellistname: "+str_hostellistname );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        room_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                str_room_id = hosteroomid.get(position);
                str_room_name = hostelroomname.get(position);
                to_share = hostelshare.get(position);
                share_price = shareprice.get(position);
                amout_status = amount_type.get(position);
                to_room_type = room_typelist.get(position);

                if(amout_status.equalsIgnoreCase("add")){
                    note_user_tv.setText("You have to Pay extra money for this change");

                }else{
                    note_user_tv.setText("Amount will be added to your Joy Wallet");

                }


                System.out.println("str_share"+to_share);


                Calendar c = Calendar.getInstance();
                final int day = c.get(Calendar.DAY_OF_MONTH);
                final int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                int current_day = day;
                int current_month = month;
                int current_year = year;


                check_in = day + "-" + (month + 1) + "-" + year;
                System.out.println("check_in"+check_in);
                gethostelbedsWBS();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        bed_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                str_bed_id = hostebedid.get(position);
                str_bed_name = hostelbedname.get(position);
                System.out.println("str_bed_id"+str_bed_id);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        linear_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_msg = tv_mesg.getText().toString();
                if(str_msg.isEmpty()) {
                    Util.getInstance().cusToast(getApplicationContext(), "Please Add Message");
                }else if(str_hostefloor.isEmpty() ){
                    Util.getInstance().cusToast(getApplicationContext(), "Please Select Floor ");

                } else if(str_room_id.isEmpty()) {
                    Util.getInstance().cusToast(getApplicationContext(), "Please Select Room");

                }else if(str_bed_id.isEmpty()){
                    Util.getInstance().cusToast(getApplicationContext(), "Please Select Bed");

                }else if(floor.equalsIgnoreCase(str_hostefloor) && room_id.equalsIgnoreCase(str_room_id)&& bed_id.equalsIgnoreCase(str_bed_id)){
                    Util.getInstance().cusToast(getApplicationContext(), "This is your Current Bed");

                }else{
                    sendrequestWBS();

                }
               // startActivity(new Intent(ChangeRoomActivity.this, HomeActivity.class));
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void sendrequestWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        //requestBody.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjhkMTI2ZDc1MjNiZTZhMTlmODM3NzFlZGNmMjgxOGE2In0.I_vBnFWfPbLigCBCWCO4XVH0UxUR2Ndxqy_SeZn8WXA");
        requestBody.put("user_id", user_id);
        requestBody.put("owner_id",owner_id );
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("floor", str_hostefloor);
        requestBody.put("room_id", str_room_id);
        requestBody.put("bed_id", str_bed_id);
        requestBody.put("from_bed_id", bed_id);
        requestBody.put("from_share", frm_share);
        requestBody.put("to_share", to_share);
        requestBody.put("message", str_msg);
        requestBody.put("to_room_type", to_room_type);
        presenter.changRoom(this, this, requestBody);
        System.out.println("yttuhu"+requestBody);




     /*   {
            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
                "user_id":"1",
                "owner_id":"1",
                "hostel_id":"1",
                "floor":"0",
                "room_id":"1",
                "bed_id":"1",
                "from_bed_id":"1",
                "from_share":"2",
                "to_share":"1",
                "message":"Test Message"
        }*/


    }

    private void gethostelfloorsWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
       // requestBody.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjhkMTI2ZDc1MjNiZTZhMTlmODM3NzFlZGNmMjgxOGE2In0.I_vBnFWfPbLigCBCWCO4XVH0UxUR2Ndxqy_SeZn8WXA");
        requestBody.put("hostel_id", hostel_id);
        presenter.hostel_floors(this, this, requestBody);


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_change_room, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter() {
        presenter = new ChangeRoomImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.HOSTEL_FLOORS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    floor_ll.setVisibility(View.VISIBLE);
                    room_ll.setVisibility(View.VISIBLE);
                    bed_ll.setVisibility(View.VISIBLE);


                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    hostefloors = new ArrayList();
                    hostelfloorname = new ArrayList();


                    //adding server data to spinner
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        hostefloors.add(jsonObject1.getString("floor"));
                        hostelfloorname.add(jsonObject1.getString("floorname"));
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hostelfloorname);

                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    floor_spinner.setAdapter(dataAdapter);

                 /*   for (int i = 0; i < hostelfloorname.size(); i++) {
                        if (!hostel_id.equalsIgnoreCase("null")) {
                            if (floor.equalsIgnoreCase(hostefloors.get(i))) {
                                floor_spinner.setSelection(i);
                            }
                        }
                    }*/

                    //  gethostelroomsWBS();

                } else {
                    floor_ll.setVisibility(View.GONE);
                    room_ll.setVisibility(View.GONE);
                    bed_ll.setVisibility(View.GONE);
                    Util.getInstance().cusToast(getApplicationContext(), jsonObject.optString("message"));

                }

            }else if (NetworkConstants.RequestCode.HOSTEL_ROOMS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    floor_ll.setVisibility(View.VISIBLE);
                    room_ll.setVisibility(View.VISIBLE);
                    bed_ll.setVisibility(View.VISIBLE);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    hostelroomname = new ArrayList();
                    hosteroomid = new ArrayList();
                    hostelshare = new ArrayList();
                    shareprice = new ArrayList();
                    amount_type = new ArrayList();
                    room_typelist = new ArrayList();

                  /*  hostelroomname.add("Select Room");
                    hosteroomid.add("-1");
                    hostelshare.add("-1");*/

                    //adding server data to spinner
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        float price = Float.parseFloat(jsonObject1.getString("price"));
                        int value = Math.round(price);
                        String type = jsonObject1.getString("ac");
                        String room_type = "";
                        String new_room_type = "";
                        if(type.equalsIgnoreCase("no")){
                            room_type="NON_AC Room";
                            new_room_type="non_ac";
                        }else{
                            room_type="AC Room";
                            new_room_type="ac";

                        }

                       // hostelroomname.add(jsonObject1.getString("room_name")+" ("+jsonObject1.getString("share") +"  Share "+room_type+")"+ " (Amount: "+value+")");
                        hostelroomname.add(jsonObject1.getString("room_name")+" ("+jsonObject1.getString("share")+"  Share)" +" (Amount: "+value+")");
                        hosteroomid.add(jsonObject1.getString("id"));
                        hostelshare.add(jsonObject1.getString("share"));
                        shareprice.add(jsonObject1.getString("price"));
                        amount_type.add(jsonObject1.getString("amount_type"));
                        room_typelist.add(new_room_type);
                    }
                    ArrayAdapter<String> roomsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hostelroomname);

                    // Drop down layout style - list view with radio button
                    roomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    room_spinner.setAdapter(roomsAdapter);

                   /* for (int i = 0; i < hostelroomname.size(); i++) {
                        if (!str_hostefloor.equalsIgnoreCase("null")) {
                            if (room_id.equalsIgnoreCase(hosteroomid.get(i))) {
                                room_spinner.setSelection(i);
                            }
                        }
                    }*/

                    //  gethostelbedsWBS();

                } else {

                    str_room_id = "";

                    floor_ll.setVisibility(View.VISIBLE);
                    room_ll.setVisibility(View.GONE);
                    bed_ll.setVisibility(View.GONE);
                    Util.getInstance().cusToast(getApplicationContext(), jsonObject.optString("message"));

                }

            }else if (NetworkConstants.RequestCode.HOSTEL_BEDS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    floor_ll.setVisibility(View.VISIBLE);
                    room_ll.setVisibility(View.VISIBLE);
                    bed_ll.setVisibility(View.VISIBLE);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    hostelbedname = new ArrayList();
                    hostebedid = new ArrayList();

/*
                    hostelbedname.add("Select Bed");
                    hostebedid.add("-1");
*/
                    //adding server data to spinner
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String status = jsonObject1.getString("status");
                        if(status.equalsIgnoreCase("available")){
                            hostelbedname.add(jsonObject1.getString("bed_name"));
                            hostebedid.add(jsonObject1.getString("id"));
                        }else{

                        }



                    }
                    ArrayAdapter<String> bedsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hostelbedname);
                    // Drop down layout style - list view with radio button
                    bedsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    bed_spinner.setAdapter(bedsAdapter);

                    if(hostelbedname.isEmpty()){
                        str_bed_id = "";
                        bed_ll.setVisibility(View.GONE);
                        Util.getInstance().cusToast(getApplicationContext(), "Currently Beds are not Available");


                    }else{
                        bed_ll.setVisibility(View.VISIBLE);

                    }

                   /* for (int i = 0; i < hostelbedname.size(); i++) {
                        if (!str_room_id.equalsIgnoreCase("null")) {
                            if (bed_id.equalsIgnoreCase(hostebedid.get(i))) {
                                bed_spinner.setSelection(i);
                            }
                        }
                    }*/
                } else {

                    floor_ll.setVisibility(View.VISIBLE);
                    room_ll.setVisibility(View.VISIBLE);
                    bed_ll.setVisibility(View.GONE);
                    //  Util.getInstance().cusToast(getApplicationContext(), jsonObject.optString("message"));
                    Util.getInstance().cusToast(getApplicationContext(), "Currently Beds are not Available");
                }
            }else if (NetworkConstants.RequestCode.CHANGE_ROOM == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    Util.getInstance().cusToast(getApplicationContext(), jsonObject.optString("message"));

                    Intent i = new Intent(ChangeRoomActivity.this,HomeActivity.class);
                    i.putExtra("to", "home");
                    startActivity(i);
                    finish();

                } else {
                    Util.getInstance().cusToast(getApplicationContext(), jsonObject.optString("message"));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void gethostelbedsWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("room_id", str_room_id);
        requestBody.put("check_in", check_in);

        presenter.hostel_beds(context, this, requestBody);

    }

    private void gethostelroomsWBS() {

       /* {
            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
                "hostel_id":"1",
                "floor":"0"
        }
*/
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("floor", str_hostefloor);
        requestBody.put("ac", ac_status);

        presenter.hostel_rooms(this, this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
