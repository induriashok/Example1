package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.joystays.joy.R;

public class DateActivity extends AppCompatActivity {
    private ImageView back_arrow;
    private LinearLayout linar_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }

        setContentView(R.layout.activity_date);


        linar_apply = findViewById(R.id.linar_apply);
        back_arrow = findViewById(R.id.back_arrow);


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linar_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DateActivity.this, HostelDetailsActivity.class));
            }
        });


    }

}
