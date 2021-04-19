package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.joystays.joy.R;


public class HomeSlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public HomeSlideAdapter(Context context) {
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.dummy_image,
            R.drawable.dummy_image,
            R.drawable.dummy_image,
    };

    @Override
    public int getCount() {
        return slide_images.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_home, container, false);

        ImageView slide_imageview = (ImageView) view.findViewById(R.id.slide_image);
        slide_imageview.setImageResource(slide_images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
