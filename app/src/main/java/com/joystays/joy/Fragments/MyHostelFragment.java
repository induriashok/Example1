package com.joystays.joy.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.joystays.joy.Adapter.LockDetaillsAdapter;
import com.joystays.joy.Adapter.RecomendedHostelbannersdapter;
import com.joystays.joy.R;
import com.joystays.joy.activities.OffersViewpagerAdapter;
import com.joystays.joy.base.BaseAbstractFragment;
import com.joystays.joy.mvp.contract.fragment.MyHostelContract;
import com.joystays.joy.mvp.presenter.fragment.MyHostelFragImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.HostelMenuPojo;
import com.joystays.joy.pojos.LockDetailsPojo;
import com.joystays.joy.pojos.LockRoomPojo;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.joystays.joy.pojos.OffersBnaersPOjo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MyHostelFragment extends BaseAbstractFragment<MyHostelFragImpl> implements MyHostelContract.IView, APIResponseCallback {

    private TextView tv_breakfast_items, tv_lunch_items, tv_dinner_items, tv_owner_name, tv_languages;
    private UserSession userSession;
    private String token, user_id;
    private HostelMenuPojo hostelMenuPojo;
    private RecyclerView rview_lock;
    private LockDetailsPojo lockDetailsPojo;
    private LockDetaillsAdapter lockDetaillsAdapter;
    private AlertDialog alertDialog;
    private LockRoomPojo lockRoomPojo;
    LinearLayout ll_my_hostel;
    private TextView tv_no_hostel, tv_text_breakfast, tv_text_lunch, tv_text_dinner;
    private String isApproved = "", hostel_id, room_id;
    private ImageView iv_owner_pic, iv_call;
    private String onwer_name, owner_number, owner_languages, owner_pic, name;
    private LinearLayout ll_dinner, ll_lunch, ll_break_fast;
    private Dialog dialog2;
    LinearLayout ll_bfast, ll_polllunch, ll_polldinner, ll_like, ll_chance, ll_no, ll_box, id_poll_send;
    private TextView tv_confrmtxt, tv_lunch, tv_dinner, tv_bfast;
    private String str_foodtype = "", str_option = "seen";
    private ImageView iv_swipe, iv_box, iv_like, iv_no, iv_chance;
    CardView cv_room_status;
    LinearLayout food_menu_ll;
    TextView no_lock_data,no_food_data;
    ViewPager homefrag_viewpager;
    OffersBnaersPOjo offersBnaersPOjo;
    OffersViewpagerAdapter offersViewpagerAdapter;
    int currentPage = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        userSession = new UserSession(getActivity());

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        name = userSession.getUserDetails().get("name");
        isApproved = userSession.getUserDetails().get("approved");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        room_id = userSession.getUserDetails().get("room_id");
        onwer_name = userSession.getUserDetails().get("owner_name");
        owner_number = userSession.getUserDetails().get("owner_number");
        owner_languages = userSession.getUserDetails().get("owner_languages");
        owner_pic = userSession.getUserDetails().get("owner_pic");

        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.VISIBLE);
        tv_breakfast_items = view.findViewById(R.id.tv_breakfast_items);
        tv_lunch_items = view.findViewById(R.id.tv_lunch_items);
        tv_dinner_items = view.findViewById(R.id.tv_dinner_items);
        rview_lock = view.findViewById(R.id.rview_lock);
        ll_my_hostel = view.findViewById(R.id.ll_my_hostel);
        tv_no_hostel = view.findViewById(R.id.tv_no_hostel);
        tv_owner_name = view.findViewById(R.id.tv_owner_name);
        tv_languages = view.findViewById(R.id.tv_languages);
        iv_call = view.findViewById(R.id.iv_call);
        iv_owner_pic = view.findViewById(R.id.iv_owner_pic);
        ll_dinner = view.findViewById(R.id.ll_dinner);
        ll_lunch = view.findViewById(R.id.ll_lunch);
        ll_break_fast = view.findViewById(R.id.ll_break_fast);
        tv_text_breakfast = view.findViewById(R.id.tv_text_breakfast);
        tv_text_lunch = view.findViewById(R.id.tv_text_lunch);
        tv_text_dinner = view.findViewById(R.id.tv_text_dinner);

        cv_room_status = view.findViewById(R.id.cv_room_status);
        no_lock_data = view.findViewById(R.id.no_lock_data);
        food_menu_ll = view.findViewById(R.id.food_menu_ll);
        no_food_data = view.findViewById(R.id.no_food_data);
        homefrag_viewpager = view.findViewById(R.id.homefrag_viewpager);


        ll_bfast = view.findViewById(R.id.ll_bfast);
        ll_polllunch = view.findViewById(R.id.ll_polllunch);
        ll_polldinner = view.findViewById(R.id.ll_polldinner);
        ll_like = view.findViewById(R.id.ll_like);
        ll_chance = view.findViewById(R.id.ll_chance);
        ll_no = view.findViewById(R.id.ll_no);
        ll_box = view.findViewById(R.id.ll_box);
        id_poll_send = view.findViewById(R.id.id_poll_send);
        tv_lunch = view.findViewById(R.id.tv_lunch);
        tv_dinner = view.findViewById(R.id.tv_dinner);
        tv_bfast = view.findViewById(R.id.tv_bfast);
        iv_chance = view.findViewById(R.id.iv_chance);
        iv_box = view.findViewById(R.id.iv_box);
        iv_like = view.findViewById(R.id.iv_like);
        iv_no = view.findViewById(R.id.iv_no);


        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();

        if (hour > 7 && hour < 11) {
            ll_break_fast.setVisibility(View.VISIBLE);
            ll_dinner.setVisibility(View.GONE);
            ll_lunch.setVisibility(View.GONE);
        } else if (hour > 11 && hour < 15) {
            ll_break_fast.setVisibility(View.GONE);
            ll_dinner.setVisibility(View.GONE);
            ll_lunch.setVisibility(View.VISIBLE);
        } else if (hour > 18 && hour < 22) {
            ll_break_fast.setVisibility(View.GONE);
            ll_dinner.setVisibility(View.VISIBLE);
            ll_lunch.setVisibility(View.GONE);
        } else {
            ll_break_fast.setVisibility(View.GONE);
            ll_dinner.setVisibility(View.GONE);
            ll_lunch.setVisibility(View.GONE);
        }
        //Toast.makeText(getActivity(), hour+"", Toast.LENGTH_SHORT).show();

        Picasso.with(getActivity()).load(NetworkConstants.URL.Imagepath_URL + owner_pic).into(iv_owner_pic);
        tv_owner_name.setText("Owner Name:" + onwer_name);
        tv_languages.setText(owner_languages);
        tv_text_breakfast.setText("Good Morning " + name + " , Chef was prepared delicious Breakfast for you so go and have your breakfast");
        tv_text_lunch.setText("Good Afternoon " + name + " , Chef was prepared delicious Lunch for you so go and have your lunch");
        tv_text_dinner.setText("Good Evening " + name + " , Chef was prepared delicious Dinner for you so go and have your dinner");


        callLockDetailsPojo();
        getMenuWS();
        getbancners();

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + owner_number));
                startActivity(dialIntent);
            }
        });


        ll_bfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_foodtype = "breakfast";
                ll_bfast.setBackgroundResource(R.drawable.floor_text_green);
                tv_bfast.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

                ll_polllunch.setBackgroundResource(R.drawable.floor_text_gray);
                tv_lunch.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                ll_polldinner.setBackgroundResource(R.drawable.floor_text_gray);
                tv_dinner.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_gray);


            }
        });

        ll_polllunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_foodtype = "lunch";

                ll_bfast.setBackgroundResource(R.drawable.floor_text_gray);
                tv_bfast.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                ll_polllunch.setBackgroundResource(R.drawable.floor_text_green);
                tv_lunch.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

                ll_polldinner.setBackgroundResource(R.drawable.floor_text_gray);
                tv_dinner.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_gray);
            }
        });

        ll_polldinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_foodtype = "dinner";

                ll_bfast.setBackgroundResource(R.drawable.floor_text_gray);
                tv_bfast.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                ll_polllunch.setBackgroundResource(R.drawable.floor_text_gray);
                tv_lunch.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                ll_polldinner.setBackgroundResource(R.drawable.floor_text_green);
                tv_dinner.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_gray);
            }
        });

//  "options":"Seen" (or) “Yes” (or) “No” (or) “Chance” (or) “Box”


        ll_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_option = "Yes";
                iv_like.setImageResource(R.drawable.like_green);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_gray);

            }
        });


        ll_chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_option = "Chance";

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_orange);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_gray);

            }
        });


        ll_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_option = "No";

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.dislikered);
                iv_box.setImageResource(R.drawable.box_gray);

            }
        });


        ll_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_option = "Box";

                iv_like.setImageResource(R.drawable.likegrey);
                iv_chance.setImageResource(R.drawable.fifty_gray);
                iv_no.setImageResource(R.drawable.like_gray);
                iv_box.setImageResource(R.drawable.box_blue);

            }
        });


        id_poll_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str_foodtype.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select FoodType", Toast.LENGTH_SHORT).show();

                } else if (str_option.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select Anyone Option", Toast.LENGTH_SHORT).show();

                } else {
                    sendPollWBS();
                }

            }
        });


    }

    private void getbancners() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        presenter.get_banners(getActivity(), this, requestBody);

    }

    private void sendPollWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("foodtime", str_foodtype);
        requestBody.put("options", str_option);
        presenter.submit_polling(getActivity(), this, requestBody);


    }

    private void callUserBookingDetailsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.user_booking_details(getActivity(), this, requestBody);
    }

    private void getMenuWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        //  requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        presenter.hostelMenu(getActivity(), this, requestBody);
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.search_fragment, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new MyHostelFragImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.HOSTEL_MENU == requestId) {
                if (jsonObject.optBoolean("status")) {

                    food_menu_ll.setVisibility(View.VISIBLE);
                    no_food_data.setVisibility(View.GONE);
                    hostelMenuPojo = new Gson().fromJson(responseString, HostelMenuPojo.class);
                    String bfast = hostelMenuPojo.getResponse().get(0).getBreakfast();
                    String lunch = hostelMenuPojo.getResponse().get(0).getLunch();
                    String dinner = hostelMenuPojo.getResponse().get(0).getDinner();
                    tv_breakfast_items.setText(bfast);
                    tv_lunch_items.setText(lunch);
                    tv_dinner_items.setText(dinner);
                }else{
                    food_menu_ll.setVisibility(View.GONE);
                    no_food_data.setVisibility(View.VISIBLE);
                }

            } else if (NetworkConstants.RequestCode.GET_BANNERS == requestId) {
                if (jsonObject.optBoolean("status")) {
                    offersBnaersPOjo = new Gson().fromJson(responseString, OffersBnaersPOjo.class);

                    homefrag_viewpager.setVisibility(View.VISIBLE);
                    offersViewpagerAdapter = new OffersViewpagerAdapter(getActivity(), offersBnaersPOjo);
                    homefrag_viewpager.setAdapter(offersViewpagerAdapter);
                    homefrag_viewpager.setFocusable(true);
                    homefrag_viewpager.setClipToPadding(false);
                    homefrag_viewpager.setPadding(0, 0, 200, 0);
                    homefrag_viewpager.setPageMargin(10);

                    final Handler handler = new Handler();
                    TimerTask timer = new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    int numPages = homefrag_viewpager.getAdapter().getCount();
                                    currentPage = (currentPage + 1) % numPages;
                                    homefrag_viewpager.setCurrentItem(currentPage);
                                }
                            });
                        }
                    };


                    Timer time = new Timer();
                    time.schedule(timer, 0, 5000);


                }else{
                    homefrag_viewpager.setVisibility(View.GONE);
                }
            } else if (NetworkConstants.RequestCode.LOCK_DETAILS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    no_lock_data.setVisibility(View.GONE);
                    cv_room_status.setVisibility(View.VISIBLE);

                    lockDetailsPojo = new Gson().fromJson(responseString, LockDetailsPojo.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rview_lock.setLayoutManager(mLayoutManager);
                    lockDetaillsAdapter = new LockDetaillsAdapter(getActivity(), lockDetailsPojo);
                    rview_lock.setAdapter(lockDetaillsAdapter);
                    lockDetaillsAdapter.setOnItemClickListener(new LockDetaillsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.iv_call:
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + lockDetailsPojo.getResponse().get(position).getMobile()));
                                    startActivity(intent);
                                    break;
                                case R.id.iv_lock_one:
                                    if (lockDetailsPojo.getResponse().get(position).getRoom_mode().equalsIgnoreCase("locked")) {
                                        if (user_id.equalsIgnoreCase(lockDetailsPojo.getResponse().get(position).getUser_id())) {
                                            openDialogforNotification("unlocked");
                                        } else {
                                            Toast.makeText(getActivity(), "You are allowed to lock", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        if (user_id.equalsIgnoreCase(lockDetailsPojo.getResponse().get(position).getUser_id())) {

                                            openDialogforNotification("locked");
                                        } else {
                                            Toast.makeText(getActivity(), "You are allowed to unlock", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    break;
                            }
                        }
                    });
                } else {
                    no_lock_data.setVisibility(View.VISIBLE);
                    cv_room_status.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                }
            } else if (NetworkConstants.RequestCode.LOCK_ROOM == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Toast.makeText(getActivity(), jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                    lockRoomPojo = new Gson().fromJson(responseString, LockRoomPojo.class);
                    lockDetailsPojo.setResponse(lockRoomPojo.getResponse());
                    lockDetaillsAdapter.notifyDataSetChanged();


                }
            } else if (NetworkConstants.RequestCode.SUBMIT_POLLING == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Toast.makeText(getActivity(), jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                    iv_like.setImageResource(R.drawable.likegrey);
                    iv_chance.setImageResource(R.drawable.fifty_gray);
                    iv_no.setImageResource(R.drawable.like_gray);
                    iv_box.setImageResource(R.drawable.box_gray);

                    ll_bfast.setBackgroundResource(R.drawable.floor_text_gray);
                    tv_bfast.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                    ll_polllunch.setBackgroundResource(R.drawable.floor_text_gray);
                    tv_lunch.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                    ll_polldinner.setBackgroundResource(R.drawable.floor_text_gray);
                    tv_dinner.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

                    str_option = "";
                    str_foodtype = "";


                }else{
                    str_option = "";
                    str_foodtype = "";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDialogforNotification(final String lock_status) {

        dialog2 = new Dialog(getActivity());
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_notification);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        window.setGravity(Gravity.CENTER | Gravity.CENTER);


        TextView tv_yes = dialog2.findViewById(R.id.tv_yes);
        TextView tv_no = dialog2.findViewById(R.id.tv_no);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSendNotificationsWs("Yes", lock_status);
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSendNotificationsWs("No", lock_status);
            }
        });
        dialog2.show();

//        alertDialog = new AlertDialog.Builder(getActivity())
////set icon
//                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                .setTitle("Send notifications?")
////set message
//                .setMessage("Are you sure want to send notification to room mates")
////set positive button
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //set what would happen when positive button is clicked
//                        callSendNotificationsWs("Yes", lock_status);
//                    }
//                })
////set negative button
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //set what should happen when negative button is clicked
//                        callSendNotificationsWs("No", lock_status);
//                    }
//                })
//                .show();


    }

    private void callSendNotificationsWs(String notification_status, String lock_status) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("room_id", room_id);
        requestBody.put("room_mode", lock_status);
        requestBody.put("notifications", notification_status);
        presenter.lockRoom(getActivity(), this, requestBody);
    }

    private void callLockDetailsPojo() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("room_id", room_id);
        presenter.lockDetails(getActivity(), this, requestBody);
        Log.d("Request parameters", requestBody.toString());
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
