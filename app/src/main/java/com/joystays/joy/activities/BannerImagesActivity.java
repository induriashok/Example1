package com.joystays.joy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joystays.joy.Adapter.BannerImagesAdapter;
import com.joystays.joy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerImagesActivity extends AppCompatActivity {

    private List<String> images = new ArrayList<>();

    RecyclerView rview_hostel_images;

    BannerImagesAdapter bannerImagesAdapter;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_images);


        Intent intent = getIntent();
        images = intent.getStringArrayListExtra("imageslist");


        rview_hostel_images = findViewById(R.id.rview_hostel_images);



        rview_hostel_images.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(BannerImagesActivity.this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (images.size() > 0 & rview_hostel_images != null) {

            bannerImagesAdapter = new BannerImagesAdapter(BannerImagesActivity.this, images);
            rview_hostel_images.setAdapter(bannerImagesAdapter);
        }
        rview_hostel_images.setLayoutManager(MyLayoutManager);










    }
}


