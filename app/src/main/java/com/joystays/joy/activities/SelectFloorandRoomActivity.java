package com.joystays.joy.activities;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.BedsAdapter;
import com.joystays.joy.Adapter.FloorsAdapter;
import com.joystays.joy.Adapter.RoomsAdapter;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.SelectFloorRoomContract;
import com.joystays.joy.mvp.presenter.activity.SelectFloorRoomImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.BedsPojo;
import com.joystays.joy.pojos.FloorsPojo;
import com.joystays.joy.pojos.LoginPojo;
import com.joystays.joy.pojos.RoomDetailsPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SelectFloorandRoomActivity extends BaseAbstractActivity<SelectFloorRoomImpl> implements SelectFloorRoomContract.IView, APIResponseCallback {
    private ImageView back_arrow, close_iv, image_graph, iv_bed_one, iv_bed_two;
    private TextView tv_norooms, tv_bed, tv_nolayout;

    private LinearLayout linear_okay, ll_data;
    String onclick = "1";
    private int floors_count;
    private RecyclerView rview_floors, rview_rooms;
    private FloorsAdapter floorsAdapter;
    private String hostel_id, token, password;
    private UserSession userSession;
    private RoomDetailsPojo roomDetailsPojo;
    private int selected_position;
    private BedsPojo bedsPojo;
    private String owner_id, room_type, package_type, from_date, to_date, user_id, ac_status, owner_pic, number;
    private String share_type = "", room_name = "", str_floor, click_bed_room_id;
    private String bed_name = "", gender_type = "", laguages = "Telugu,English";
    private int selected_floor_id;
    private int new_position;
    FloorsPojo floorsPojo;
    APIResponseCallback apiResponseCallback;

    LoginPojo loginPojo;
    LinearLayout nonac_ll, ac_ll;

    public static String sele_bed_id = "0", image;
    BedsAdapter bedsAdapter;

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

        apiResponseCallback = this;
        image_graph = findViewById(R.id.image_graph);
        back_arrow = findViewById(R.id.back_arrow_one);
        linear_okay = findViewById(R.id.linear_okay);
        rview_floors = findViewById(R.id.rview_floors);
        rview_rooms = findViewById(R.id.rview_rooms);
        tv_norooms = findViewById(R.id.tv_norooms);
        tv_bed = findViewById(R.id.tv_bed);
        ll_data = findViewById(R.id.ll_data);
        tv_nolayout = findViewById(R.id.tv_nolayout);
        nonac_ll = findViewById(R.id.nonac_ll);
        ac_ll = findViewById(R.id.ac_ll);
        userSession = new UserSession(context);
        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        number = userSession.getUserDetails().get("mobileno");
        password = userSession.getUserDetails().get("password");

        Intent intent = getIntent();
        floors_count = intent.getIntExtra("floor_count", 0);
        hostel_id = intent.getStringExtra("hostel_id");
        owner_id = intent.getStringExtra("owner_id");
        room_type = intent.getStringExtra("room_type");
        package_type = intent.getStringExtra("package_type");
        from_date = intent.getStringExtra("from_date");
        to_date = intent.getStringExtra("to_date");
        owner_pic = intent.getStringExtra("owner_pic");
        gender_type = intent.getStringExtra("gender_type");

        if (room_type.equalsIgnoreCase("non_ac")) {
            ac_status = "no";
            nonac_ll.setVisibility(View.VISIBLE);
            ac_ll.setVisibility(View.GONE);
        } else {
            ac_status = "yes";
            nonac_ll.setVisibility(View.GONE);
            ac_ll.setVisibility(View.VISIBLE);
        }

        callLoginWs();


        FloorsAdapter.floor_position = 0;
        BedsAdapter.selected_bed_count = -1;

        getHostelfloorWBS();

       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rview_floors.setLayoutManager(mLayoutManager);

        floorsAdapter = new FloorsAdapter(context, floors_count);
        rview_floors.setAdapter(floorsAdapter);
        Variables.floor_id = "1";
        floorsAdapter.setOnItemClickListener(new FloorsAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selected_floor_id = position;
                switch (view.getId()) {
                    case R.id.ll_floor:
                        floorsAdapter.floor_position = position;
                        floorsAdapter.notifyDataSetChanged();
                        Variables.floor_id = String.valueOf(position + 1);
                        callRoomDetailsWs(position);
                        break;
                }
            }
        });*/


        // callRoomDetailsWs(0);

        image_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomDetailsPojo.getFloor_layout() == null || roomDetailsPojo.getFloor_layout().size() == 0) {
                    // if (roomDetailsPojo.getFloor_layout().get(0).getImage().isEmpty() || roomDetailsPojo.getFloor_layout().get(0).getImage() == null) {
                    Toast.makeText(context, "No Floor layout available", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent1 = new Intent(SelectFloorandRoomActivity.this, ZoomImageActivity.class);
                    intent1.putExtra("image", roomDetailsPojo.getFloor_layout().get(0).getImage());
                    startActivity(intent1);
                }


            }
        });
        linear_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (share_type.isEmpty()) {

                checksharepricesWBS();


            }
        });


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floorsAdapter.floor_position = 0;
                BedsAdapter.selected_bed_count = -1;
                Variables.bed_id = "";

                onBackPressed();

/*
                Intent i = new Intent(SelectFloorandRoomActivity.this,HostelDetailsActivity.class);
                i.putExtra("hostel_id", hostel_id);
                i.putExtra("ac_status", ac_status);
                i.putExtra("gender_type", gender_type);
                startActivity(i);
                finish();*/
            }
        });
    }

    private void checksharepricesWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("owner_id", owner_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("share", share_type);
        requestBody.put("room_type", room_type);
        requestBody.put("token", token);
        requestBody.put("package_type", package_type);
        presenter.check_hostel_prices(SelectFloorandRoomActivity.this, apiResponseCallback, requestBody);

    }

    private void callLoginWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile", number);
        requestBody.put("password", password);
        presenter.userLogin(SelectFloorandRoomActivity.this, apiResponseCallback, requestBody);
    }

    private void getHostelfloorWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("hostel_id", hostel_id);
        presenter.hostel_floors(context, this, requestBody);
    }

    //  private void callRoomDetailsWs(int floor) {
    private void callRoomDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("user_id", user_id);
        requestBody.put("ac", ac_status);
        requestBody.put("floor", str_floor);
        requestBody.put("check_in", from_date);
        presenter.getRooms(context, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_select_floorand_room, null);
        return view;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        floorsAdapter.floor_position = 0;
        BedsAdapter.selected_bed_count = -1;
        Variables.bed_id = "";


        finish();

        /*Intent i = new Intent(SelectFloorandRoomActivity.this,HostelDetailsActivity.class);
        i.putExtra("hostel_id", hostel_id);
        i.putExtra("ac_status", ac_status);
        i.putExtra("gender_type", gender_type);
        startActivity(i);
        finish();*/

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // floorsAdapter.floor_position = 0;
        // floorsAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter() {
        presenter = new SelectFloorRoomImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.HOSTEL_ROOMS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    BedsAdapter.selected_bed_count = -1;

                    ll_data.setVisibility(View.VISIBLE);
                    tv_norooms.setVisibility(View.GONE);
                    rview_rooms.setVisibility(View.VISIBLE);
                    tv_bed.setVisibility(View.VISIBLE);
                    tv_nolayout.setVisibility(View.GONE);

                    roomDetailsPojo = new Gson().fromJson(responseString, RoomDetailsPojo.class);

                    rview_rooms.setLayoutManager(new GridLayoutManager(this, 3));
                    RoomsAdapter roomsAdapter = new RoomsAdapter(roomDetailsPojo, context);
                    rview_rooms.setAdapter(roomsAdapter);

                    //  image = roomDetailsPojo.getFloor_layout().get(0).getImage();

                    JSONArray jsonArray = jsonObject.getJSONArray("floor_layout");
                    System.out.println("fsdhgfjghj" + jsonArray.length());


                    if (jsonArray.length() > 0) {
                        image_graph.setVisibility(View.VISIBLE);

                        Picasso.with(context)
                                .load(NetworkConstants.URL.Imagepath_URL + roomDetailsPojo.getFloor_layout().get(selected_position).getImage())
                                .error(R.drawable.no_banner).into(image_graph);
                    } else {

                        image_graph.setVisibility(View.GONE);
                        tv_nolayout.setVisibility(View.VISIBLE);


                    }


                    roomsAdapter.setOnItemClickListener(new RoomsAdapter.OnitemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {
                            BedsAdapter.selected_bed_count = -1;

                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.bed_booking:
                                    Variables.room_id = roomDetailsPojo.getResponse().get(position).getId();
                                    share_type = roomDetailsPojo.getResponse().get(position).getShare();
                                    room_name = roomDetailsPojo.getResponse().get(position).getRoom_name();
                                    callBedsWs();
                                    break;
                            }
                        }
                    });









/*

                    if (roomDetailsPojo.getFloor_layout()!=null && roomDetailsPojo.getFloor_layout().size() > 0) {
                        image_graph.setVisibility(View.VISIBLE);

                        Picasso.with(context)
                                .load(NetworkConstants.URL.Imagepath_URL + roomDetailsPojo.getFloor_layout().get(selected_position).getImage())
                                .error(R.drawable.no_banner).into(image_graph);
                    } else {

                        image_graph.setVisibility(View.GONE);
                        tv_nolayout.setVisibility(View.VISIBLE);



                    }
*/


                } else {
                    tv_norooms.setVisibility(View.VISIBLE);
                    tv_nolayout.setVisibility(View.VISIBLE);
                    rview_rooms.setVisibility(View.GONE);
                    tv_bed.setVisibility(View.GONE);
                    image_graph.setVisibility(View.GONE);
                    ll_data.setVisibility(View.GONE);


                }
            } else if (NetworkConstants.RequestCode.HOSTEL_BEDS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                   /* if(Variables.room_id.equalsIgnoreCase(click_bed_room_id)){

                    }else{
                        BedsAdapter.selected_bed_count=-1;

                    }*/

                    bedsPojo = new Gson().fromJson(responseString, BedsPojo.class);

                    openBedsLayoutDailog();
                } else {
                    opennoBedsLayoutDailog();

                }
            } else if (NetworkConstants.RequestCode.HOSTEL_FLOORS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    BedsAdapter.selected_bed_count = -1;
                    floorsPojo = new Gson().fromJson(responseString, FloorsPojo.class);
                    str_floor = floorsPojo.getResponse().get(0).getFloor();



                 /*   RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    rview_floors.setLayoutManager(mLayoutManager);

                    floorsAdapter = new FloorsAdapter(context, floors_count);
                    rview_floors.setAdapter(floorsAdapter);
                    Variables.floor_id = "1";
                    floorsAdapter.setOnItemClickListener(new FloorsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_floor_id = position;
                            switch (view.getId()) {
                                case R.id.ll_floor:
                                    floorsAdapter.floor_position = position;
                                    floorsAdapter.notifyDataSetChanged();
                                    Variables.floor_id = String.valueOf(position + 1);
                                    callRoomDetailsWs(position);
                                    break;
                            }
                        }
                    });
*/


                    if (floorsPojo.getResponse().size() > 0) {
                        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        rview_floors.setLayoutManager(linearLayoutManager);
                        floorsAdapter = new FloorsAdapter(this, floorsPojo);
                        rview_floors.setAdapter(floorsAdapter);
                        Variables.floor_id = floorsPojo.getResponse().get(0).getFloor();


                        floorsAdapter.setOnItemClickListener(new FloorsAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                new_position = position;
                                switch (view.getId()) {
                                    case R.id.ll_floor:
                                        str_floor = floorsPojo.getResponse().get(position).getFloor();
                                        // Variables.floor_id = String.valueOf(position + 1);
                                        Variables.floor_id = str_floor;


                                        BedsAdapter.selected_bed_count = -1;


                                        if (FloorsAdapter.floor_position == position) {
                                            FloorsAdapter.floor_position = 0;

                                        } else {
                                            FloorsAdapter.floor_position = new_position;
                                            floorsAdapter.notifyDataSetChanged();
                                        }

                                        callRoomDetailsWs();

                                        break;


                                }
                            }
                        });

                        callRoomDetailsWs();


                    } else {

                    }


                } else {
                    opennoBedsLayoutDailog();

                }

            } else if (NetworkConstants.RequestCode.USER_LOGIN == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //  Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                    loginPojo = new Gson().fromJson(responseString, LoginPojo.class);
                    String bed_confromred = loginPojo.getResponse().getBed_confirmed();


                    userSession.StoreUserDetails(loginPojo.getResponse().getId(),
                            loginPojo.getResponse().getName(),
                            loginPojo.getResponse().getMobile(),
                            loginPojo.getResponse().getEmail_id(),
                            loginPojo.getResponse().getBed_confirmed(),
                            loginPojo.getToken(), loginPojo.getResponse().getProfile_pic(), password);

                    if (bed_confromred.equalsIgnoreCase("no")) {


                    } else {

                        if (loginPojo.getResponse().getBooking().equalsIgnoreCase("done")) {

                            if (loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages() != null) {
                                laguages = loginPojo.getResponse().getBooking_details().getOwner_details().getLanguages();

                            }
                            if (loginPojo.getResponse().getBooking_details().getStatus().equalsIgnoreCase("approved") || loginPojo.getResponse().getBooking_details().getStatus().equalsIgnoreCase("approved")) {
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


                            }
                        } else {

                        }


                    }

                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    userSession.logoutUser();
                    Intent i = new Intent(SelectFloorandRoomActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }


            } else if (NetworkConstants.RequestCode.CHECK_SHARE_PRICES == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    //  Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    if (bed_name.isEmpty()) {
                        Toast.makeText(context, "Select Bed", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(SelectFloorandRoomActivity.this, RoomMateDetailsActivity.class);
                        intent.putExtra("hostel_id", hostel_id);
                        intent.putExtra("owner_id", owner_id);
                        intent.putExtra("token", token);
                        intent.putExtra("room_type", room_type);
                        intent.putExtra("package_type", package_type);
                        intent.putExtra("from_date", from_date);
                        intent.putExtra("to_date", to_date);
                        intent.putExtra("share_type", share_type);
                        intent.putExtra("owner_pic", owner_pic);
                        intent.putExtra("book_status", loginPojo.getResponse().getBooking());
                        //intent.putExtra("book_status", loginPojo.getResponse().getBooking());
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                        context.startActivity(intent, options.toBundle());
                        System.out.println("gyghgggj"+ac_status);

                    }

                /*if (loginPojo.getResponse().getBooking().equalsIgnoreCase("done")) {
                    Toast.makeText(context, "You are not allowed for another Booking", Toast.LENGTH_SHORT).show();



                } else {
                    if (bed_name.isEmpty()) {
                        Toast.makeText(context, "Select Bed", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(SelectFloorandRoomActivity.this, RoomMateDetailsActivity.class);
                        intent.putExtra("hostel_id", hostel_id);
                        intent.putExtra("owner_id", owner_id);
                        intent.putExtra("token", token);
                        intent.putExtra("room_type", room_type);
                        intent.putExtra("package_type", package_type);
                        intent.putExtra("from_date", from_date);
                        intent.putExtra("to_date", to_date);
                        intent.putExtra("share_type", share_type);
                        intent.putExtra("owner_pic", owner_pic);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                        context.startActivity(intent, options.toBundle());
                    }
                }
*/


                } else {
                    Toast.makeText(context, "Unable to book this Room Please Contact Admin", Toast.LENGTH_SHORT).show();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void opennoBedsLayoutDailog() {

        final Dialog dialog2 = new Dialog(SelectFloorandRoomActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.room_nodatadialog);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);


        TextView tv_room = dialog2.findViewById(R.id.tv_room);
        TextView tv_nodataroom = dialog2.findViewById(R.id.tv_select);
        ImageView close_iv = dialog2.findViewById(R.id.close_iv);
        tv_room.setText(room_name);


        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();

    }

    private void callBedsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("hostel_id", hostel_id);
        // requestBody.put("hostel_id", "1");
        requestBody.put("room_id", roomDetailsPojo.getResponse().get(selected_position).getId());
        requestBody.put("check_in", from_date);
        presenter.getBeds(context, this, requestBody);

    }

    private void openBedsLayoutDailog() {
        final Dialog dialog2 = new Dialog(SelectFloorandRoomActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.room_dialog);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER | Gravity.CENTER);


        RecyclerView rview_beds = dialog2.findViewById(R.id.rview_beds);
        ImageView close_iv = dialog2.findViewById(R.id.close_iv);
        TextView tv_room = dialog2.findViewById(R.id.tv_room);
        TextView tv_select = dialog2.findViewById(R.id.tv_select);

        tv_room.setText(room_name);
        rview_beds.setLayoutManager(new GridLayoutManager(this, 2));
        bedsAdapter = new BedsAdapter(bedsPojo, context);
        rview_beds.setAdapter(bedsAdapter);

        bedsAdapter.setOnItemClickListener(new BedsAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_bed:

                        click_bed_room_id = bedsPojo.getResponse().get(position).getRoom_id();

                        if (bedsPojo.getResponse().get(position).getStatus().equalsIgnoreCase("available")) {
                            // bedsAdapter.selected_bed_count = position;
                            BedsAdapter.selected_bed_count = position;

                            bedsAdapter.notifyDataSetChanged();
                            bed_name = bedsPojo.getResponse().get(position).getBed_name();
                            Variables.bed_id = bedsPojo.getResponse().get(position).getId();
                            sele_bed_id = bedsPojo.getResponse().get(position).getId();
                        } else {
                            Toast.makeText(SelectFloorandRoomActivity.this, "you can't select this bed ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bed_name.isEmpty()) {
                    Toast.makeText(SelectFloorandRoomActivity.this, "Please select bed", Toast.LENGTH_SHORT).show();
                } else {
                    dialog2.dismiss();

                    Toast.makeText(getApplicationContext(), "Bed Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
