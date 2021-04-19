package com.joystays.joy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class ZoomImageActivity extends AppCompatActivity {
    private ImageView back_arrow, iv_image;
    private String image;
    private String str_image;
    ImageView file_thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }



        setContentView(R.layout.activity_zoom_image);

         //str_image = getIntent().getStringExtra("hostel_image");

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        back_arrow = findViewById(R.id.back_arrow);
        iv_image = findViewById(R.id.iv_image);
        file_thumbnail = findViewById(R.id.file_thumbnail);


      /*  Picasso.with(this)
                .load(NetworkConstants.URL.Imagepath_URL + image)
                .error(R.drawable.no_banner).into(iv_image);*/

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        Picasso.with(ZoomImageActivity.this)
                .load(NetworkConstants.URL.Imagepath_URL + image)
                .into(file_thumbnail);


    }
}
