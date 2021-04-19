package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.BuildConfig;
import com.joystays.joy.R;
import com.joystays.joy.utils.Variables;

public class InviteAndEarnActivity extends AppCompatActivity {
    private ImageView back_arrow;
    private LinearLayout linear_invite;
    TextView tv_des,tv_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        setContentView(R.layout.activity_invite_and_earn);


        linear_invite = findViewById(R.id.linear_invite);
        back_arrow = findViewById(R.id.back_arrow);
        tv_des = findViewById(R.id.tv_des);
        tv_code = findViewById(R.id.tv_code);
        tv_code.setText(Variables.referal_code);
        System.out.println("referal_code"+Variables.referal_code);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linear_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Share and Earn Money";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Joy Hostel App");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
*/



                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Joy Stays");
                    String shareMessage= "\nLet me recommend you this application Use this Referral Code to Earn Money   " +Variables.referal_code+"\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }







            }
        });
    }
}
