package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Variables;
import com.squareup.picasso.Picasso;

public class TermsAndConditions extends AppCompatActivity {
    private ImageView back_arrow, iv_call,owner_iv;
    private LinearLayout pay_linear;
    private String total_amount,owner_languges;
    private TextView tv_total_amount, tv_owner_name, tv_owner_languages;
    private CheckBox cb_terms;
    private String owner_id, joy_wallet,room_type, package_type, from_date, to_date, share_type, hostel_id,refundable_amount,owner_pic,advance_amount;
    UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        setContentView(R.layout.activity_terms_and__conditions);


        userSession=new UserSession(TermsAndConditions.this);
        owner_languges = userSession.getUserDetails().get("owner_languages");


        back_arrow = findViewById(R.id.back_arrow);
        pay_linear = findViewById(R.id.pay_linear);
        tv_total_amount = findViewById(R.id.tv_total_amount);
        cb_terms = findViewById(R.id.cb_terms);
        iv_call = findViewById(R.id.iv_call);
        tv_owner_languages = findViewById(R.id.tv_owner_languages);
        tv_owner_name = findViewById(R.id.tv_owner_name);
        owner_iv = findViewById(R.id.owner_iv);

        final Intent intent = getIntent();
        total_amount = intent.getStringExtra("total_amount");

        hostel_id = intent.getStringExtra("hostel_id");
        owner_id = intent.getStringExtra("owner_id");
        room_type = intent.getStringExtra("room_type");
        package_type = intent.getStringExtra("package_type");
        from_date = intent.getStringExtra("from_date");
        to_date = intent.getStringExtra("to_date");
        share_type = intent.getStringExtra("share_type");
        owner_pic = intent.getStringExtra("owner_pic");
        advance_amount = intent.getStringExtra("advance_amount");
        joy_wallet = intent.getStringExtra("joy_wallet");
        refundable_amount = intent.getStringExtra("refundable_amount");


        Picasso.with(this).load(NetworkConstants.URL.Imagepath_URL + owner_pic)
                .error(R.drawable.profile_png_image)
                .into(owner_iv);

        tv_total_amount.setText(total_amount);

        tv_owner_languages.setText(Variables.owner_languages);
       // tv_owner_languages.setText(owner_languges);
        tv_owner_name.setText("Owner Name: " + Variables.owner_name);

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + Variables.owner_number;
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
        pay_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cb_terms.isChecked()) {

                    Intent intent1 = new Intent(TermsAndConditions.this, PaymentMethodActivity.class);

                    intent1.putExtra("hostel_id", hostel_id);
                    intent1.putExtra("owner_id", owner_id);
                    intent1.putExtra("room_type", room_type);
                    intent1.putExtra("package_type", package_type);
                    intent1.putExtra("from_date", from_date);
                    intent1.putExtra("to_date", to_date);
                    intent1.putExtra("share_type", share_type);
                    intent1.putExtra("total_amount", total_amount);
                    intent1.putExtra("owner_pic", owner_pic);
                    intent1.putExtra("from", "terms");
                    intent1.putExtra("advance_amount", advance_amount);
                    intent1.putExtra("refundable_amount", refundable_amount);
                    intent1.putExtra("joy_wallet", joy_wallet);
                    startActivity(intent1);
                    System.out.println("gyghgggj"+room_type);

                } else {
                    Toast.makeText(TermsAndConditions.this, "Please check Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
