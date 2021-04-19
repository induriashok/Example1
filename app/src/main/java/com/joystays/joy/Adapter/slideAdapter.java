package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joystays.joy.R;


public class slideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public slideAdapter(Context context) {
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.onboard_one,
            R.drawable.onboard_two,
            R.drawable.onboard_three,
    };
    public String[] slide_headlines = {
            "Wow..!!  Welcome",
            "Are all the Joy Stay Properties Digitalised!",
            "Are you finding it difficult to search for a Accommodation",
    };
    public String[] slide_descs = {
            "Hey you are in the right place..!!! Simply come with your bag and step-in to your comfort zone.",
            "Yes we are, now you can book your accommodation with exact bed number in a smart way at your finger tips.",
            "Swith to Joy Stays! We are here to find you a smart property which is fully furnished and providing lips - smacking food at a affordable price.",
    };

    @Override
    public int getCount() {
        return slide_headlines.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_onboard, container, false);

        ImageView slide_imageview = (ImageView) view.findViewById(R.id.slide_image);
        TextView slide_headline = (TextView) view.findViewById(R.id.slide_head);
        TextView slide_description = (TextView) view.findViewById(R.id.slide_desc);

        slide_imageview.setImageResource(slide_images[position]);
        slide_headline.setText(slide_headlines[position]);
        slide_description.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
