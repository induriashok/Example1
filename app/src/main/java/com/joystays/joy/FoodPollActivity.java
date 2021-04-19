package com.joystays.joy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joystays.joy.Adapter.FoodPollingAdapter;
import com.joystays.joy.activities.HomeActivity;
import com.joystays.joy.base.AdapterCallback;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.FoodPollContract;
import com.joystays.joy.mvp.presenter.activity.FoodPollImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.FoodPollingPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodPollActivity extends BaseAbstractActivity<FoodPollImpl> implements FoodPollContract.IView, APIResponseCallback, AdapterView.OnItemSelectedListener, AdapterCallback {

    private FoodPollingPojo foodPollingPojo;
    ImageView back_arrow;
    private UserSession userSession;
    private String user_id, token, hostel_id, owner_id,foodtime;
    FoodPollingAdapter foodPollingAdapter;
    RecyclerView breakfast_rv,lunch_rv,dinner_rv;
    int new_position;
    private Spinner itemtype_spinner;
    String[] types = {"BreakFast", "Lunch", "Dinner"};
    List<String> fooditems = new ArrayList();
    List<String> status = new ArrayList();
    TextView tv_nodata;
    AdapterCallback adapterCallback;
    String superviser_id;

    public static String poll_status = "no",str_namre;

    AlertDialog.Builder builder;







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
        userSession = new UserSession(FoodPollActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        owner_id = userSession.getUserDetails().get("owner_id");
        hostel_id = userSession.getUserDetails().get("hostel_id");
        hostel_id = userSession.getUserDetails().get("hostel_id");

        adapterCallback = this;


        breakfast_rv = findViewById(R.id.breakfast_rv);
        tv_nodata = findViewById(R.id.tv_nodata);
        itemtype_spinner = findViewById(R.id.itemtype_spinner);
        back_arrow = findViewById(R.id.back_arrow);

        /*lunch_rv = findViewById(R.id.lunch_rv);
        dinner_rv = findViewById(R.id.dinner_rv);
*/


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FoodPollActivity.this,HomeActivity.class);
                i.putExtra("to", "home");
                startActivity(i);
                finish();
            }
        });

        itemtype_spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemtype_spinner.setAdapter(aa);


      //  callGetFoodPollingWs();
    }

    private void callGetFoodPollingWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("owner_id", owner_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("foodtime", foodtime);


        presenter.getPoll(this, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_food_poll, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new FoodPollImpl(this, this);
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
            } else if (NetworkConstants.RequestCode.GET_FOOD_POLL == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    tv_nodata.setVisibility(View.GONE);

                    fooditems = new ArrayList();
                    status = new ArrayList();


                    JSONObject jsonObject1 = jsonObject.getJSONObject("response");

                    superviser_id = jsonObject1.optString("supervisor_id");


                    JSONArray jsonArray = jsonObject1.getJSONArray("items");

                    for(int i = 0; i< jsonArray.length();i++){
                        String item_name = jsonArray.getString(i);
                        fooditems.add(item_name);
                        status.add(poll_status);
                    }



                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    breakfast_rv.setLayoutManager(mLayoutManager);
                    foodPollingAdapter = new FoodPollingAdapter(this,fooditems,adapterCallback,status);
                    breakfast_rv.setAdapter(foodPollingAdapter);



                }else{
                    tv_nodata.setVisibility(View.VISIBLE);
                    breakfast_rv.setVisibility(View.GONE);

                }
            } else if (NetworkConstants.RequestCode.SEND_POLL_LIKE == requestId) {
            if (jsonObject.optBoolean("status") == true) {

               String message = jsonObject.optString("message");
               Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(FoodPollActivity.this, HomeActivity.class);
                i.putExtra("to", "home");
                startActivity(i);
                finish();


             //   foodPollingAdapter.notifyDataSetChanged();

               /* if(poll_status.equalsIgnoreCase("unlike")){

               }else{

               }*/




            }else{
                String message = jsonObject.optString("message");
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            foodtime = "breakfast";
        } else if (position == 1) {
            foodtime = "lunch";

        } else if (position == 2) {
            foodtime = "dinner";

        }

        callGetFoodPollingWs();


        System.out.println("positiontujyityi" + foodtime);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void clickevent(ImageView grenn_like, int position, String name) {


        if(grenn_like.getId()==R.id.grenn_like){
             str_namre = name;

            poll_status="yes";



           /* {
                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
                    "poll_id":"1",
                    "owner_id":"1",
                    "supervisor_id":"1",
                    "hostel_id":"1",
                    "foodtime":"breakfast",
                    "item":"Idly"
            }*/


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle("Alert")
                    .setMessage("Are you sure want to Send Poll?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {

                                callpoll_submission();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();














        }
    }

    private void callpoll_submission() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("poll_id", "like");
        requestBody.put("owner_id", owner_id);
        requestBody.put("supervisor_id", superviser_id);
        requestBody.put("hostel_id", hostel_id);
        requestBody.put("foodtime", foodtime);
        requestBody.put("item", str_namre);

        presenter.send_polls(this, this, requestBody);

    }
}
