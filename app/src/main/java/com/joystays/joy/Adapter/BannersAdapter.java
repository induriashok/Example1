package com.joystays.joy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.joystays.joy.R;
import com.joystays.joy.activities.BannerImagesActivity;
import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.controller.ApplicationController;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.HostelDetailsPojo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannersAdapter extends PagerAdapter {

    private Context context;

    LayoutInflater layoutInflater;
    HostelDetailsPojo hostelDetailsPojo;
    List<HostelDetailsPojo.ResponseBean.HostelBannersBean> hostel_banners;

    ArrayList  imageslist;





    public BannersAdapter(HostelDetailsActivity context, List<HostelDetailsPojo.ResponseBean.HostelBannersBean> hostel_banners) {
        this.context = context;
        this.hostel_banners=hostel_banners;
        imageslist=new ArrayList();


    }

    @Override
    public int getCount() {
       return hostel_banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.home_edoitorslideview,container,false);

        final ImageView slide_imageview = (ImageView) view.findViewById(R.id.iv_slide);
        final ProgressBar progress_bar=view.findViewById(R.id.progress_bar);

        try {


            if(! hostel_banners.get(position).getBanner().isEmpty()) {
                System.out.println("image copies"+ NetworkConstants.URL.Imagepath_URL +  hostel_banners.get(position).getBanner());
                progress_bar.setVisibility(View.VISIBLE);
                Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL +  hostel_banners.get(position).getBanner())
                        .into(slide_imageview, new Callback() {
                            @Override
                            public void onSuccess() {
                                System.out.println("image copies dghfd"+NetworkConstants.URL.Imagepath_URL +  hostel_banners.get(position).getBanner());
                                progress_bar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {
                                progress_bar.setVisibility(View.GONE);
                                slide_imageview.setImageResource(R.drawable.no_banner);
                            }
                        });
            }else {
                slide_imageview.setImageResource(R.drawable.no_image);

            }



            //  Util.getInstance().setGlideImage(context, bannerImageView, departmentPOJO.getBanners().get(position).getImage(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }


        slide_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hostel_banners.size() < 1) {

                } else {

                    imageslist.clear();

                    for (int k = 0; k < hostel_banners.size(); k++) {
                        imageslist.add(hostel_banners.get(k).getBanner());
                    }
                }

                Bundle b = new Bundle();
                b.putStringArrayList("imageslist",  imageslist);
                Intent i = new Intent(context,BannerImagesActivity.class);
                i.putExtras(b);
                context.startActivity(i);



            }
        });







        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((CardView)object);
    }











}
