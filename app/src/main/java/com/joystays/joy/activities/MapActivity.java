package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joystays.joy.R;

public class MapActivity extends AppCompatActivity {
    private ImageView back_arrow;
    private ImageView map_green;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        setContentView(R.layout.activity_map);

        String hostel = getIntent().getExtras().getString("hostelname");

        map_green = findViewById(R.id.map_green);
        back_arrow = findViewById(R.id.back_arrow);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(hostel);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        map_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, HostelDetailsActivity.class));
            }
        });
    }
}
