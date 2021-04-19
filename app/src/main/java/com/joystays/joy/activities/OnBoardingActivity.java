package com.joystays.joy.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.Adapter.slideAdapter;
import com.joystays.joy.R;


public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    private LinearLayout vllDost;
    private com.joystays.joy.Adapter.slideAdapter slideAdapter;
    private TextView[] mdots;
    private TextView onboard_login;
    private TextView onboard_signup;
    private TextView onboard_skip;


    public int[] slide_images = {
            R.drawable.onboard_one,
            R.drawable.onboard_two,
            R.drawable.onboard_three,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = findViewById(R.id.onboard_viewpager);
        onboard_skip = findViewById(R.id.onboard_skip);

        onboard_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(OnBoardingActivity.this, WelcomeActivity.class);
                startActivity(i1);
            }
        });


        slideAdapter = new slideAdapter(OnBoardingActivity.this);
        vllDost = findViewById(R.id.vDots);
        setupDotBar(slide_images.length);
        viewPagerButtons();
        viewPager.setAdapter(slideAdapter);

    }

    public void viewPagerButtons() {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (vllDost != null && vllDost.getTag() != null) {
                    ((ImageView) vllDost.getTag()).setImageResource(R.drawable.onboard_circle_lite);
                    ((ImageView) vllDost.getChildAt(position)).setImageResource(R.drawable.onboard_circle_dark);
                    vllDost.setTag(vllDost.getChildAt(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDotBar(final Integer length) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(25, 25);
        param.setMargins(5, 0, 5, 0);
        vllDost.removeAllViews();
        for (int i = 0; i < length; i++) {
            int i2;
            ImageView img = new ImageView(getApplicationContext());
/*LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(5,5);
img.setLayoutParams(ivParams);*/
            if (i == 0) {
                i2 = R.drawable.onboard_circle_lite;
            } else {
                i2 = R.drawable.onboard_circle_dark;
            }
            img.setImageResource(i2);
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            vllDost.addView(img, param);
            if (i == 0) {
                vllDost.setTag(img);
            }
        }
    }


}
