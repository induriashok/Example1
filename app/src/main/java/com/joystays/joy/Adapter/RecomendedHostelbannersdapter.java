package com.joystays.joy.Adapter;

import android.app.ActivityOptions;
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
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.joystays.joy.R;
import com.joystays.joy.activities.BannerImagesActivity;
import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.HostelDetailsPojo;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecomendedHostelbannersdapter extends PagerAdapter {

    private Context context;

    LayoutInflater layoutInflater;
    NearByHostelsPojo nearByHostelsPojo;
    List<NearByHostelsPojo.RecommendedHostelsBean> recommended_hostels;
    ArrayList imageslist;
    String gender;

    public RecomendedHostelbannersdapter(FragmentActivity activity, List<NearByHostelsPojo.RecommendedHostelsBean> recommended_hostels, String gender_type) {

        this.context = activity;
        this.recommended_hostels=recommended_hostels;
        imageslist=new ArrayList();
        this.gender = gender_type;

    }


    @Override
    public int getCount() {
        return recommended_hostels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.home_new_banners_recomd,container,false);

        final ImageView slide_imageview = (ImageView) view.findViewById(R.id.iv_slide);
        final ProgressBar progress_bar=view.findViewById(R.id.progress_bar);

        try {


            if(! recommended_hostels.get(position).getImage().isEmpty()) {
                System.out.println("image copies"+ NetworkConstants.URL.Imagepath_URL +  recommended_hostels.get(position).getImage());
                progress_bar.setVisibility(View.VISIBLE);
                Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL +  recommended_hostels.get(position).getImage())
                        .into(slide_imageview, new Callback() {
                            @Override
                            public void onSuccess() {
                                System.out.println("image copies dghfd"+NetworkConstants.URL.Imagepath_URL + recommended_hostels.get(position).getImage());
                                progress_bar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {
                                progress_bar.setVisibility(View.GONE);
                                slide_imageview.setImageResource(R.drawable.no_image);
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


                Intent intent = new Intent(context, HostelDetailsActivity.class);
                intent.putExtra("hostel_id", recommended_hostels.get(position).getId());
                intent.putExtra("ac_status", recommended_hostels.get(position).getAc());
                intent.putExtra("non_ac_status", recommended_hostels.get(position).getNon_ac());
                intent.putExtra("gender_type", gender);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_left, R.anim.slide_out_right);
                context.startActivity(intent, options.toBundle());








            /*    Bundle b = new Bundle();
                b.putStringArrayList("imageslist",  imageslist);
                Intent i = new Intent(context, BannerImagesActivity.class);
                i.putExtras(b);
                context.startActivity(i);
*/


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
