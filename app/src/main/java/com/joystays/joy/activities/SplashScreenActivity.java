package com.joystays.joy.activities;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.joystays.joy.R;
import com.joystays.joy.sharepref.UserSession;

public class SplashScreenActivity extends AppCompatActivity {
    private UserSession userSession;
    private String user_id, KEY_UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        userSession = new UserSession(this);
        user_id = userSession.getUserDetails().get("id");
        KEY_UserName = userSession.getUserDetails().get("name");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (user_id != null) {
                    if (!KEY_UserName.equalsIgnoreCase("null")) {

                        Intent i1 = new Intent(SplashScreenActivity.this, HomeActivity.class);
                        i1.putExtra("to", "home");
                        startActivity(i1);
                        finish();
                    }
                } else {
                    Intent i1 = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                    startActivity(i1);
                    finish();
                }
            }
        }, 3500);
    }
}
