package com.joystays.joy.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeHostelAdapter extends RecyclerView.Adapter<HomeHostelAdapter.MyViewHolder> {
    private Context context;
    private List<NearByHostelsPojo.ResponseBean> hostelBean;

    public HomeHostelAdapter(Context foodActivity, List<NearByHostelsPojo.ResponseBean> hostelBean) {
        this.context = foodActivity;
        this.hostelBean = hostelBean;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_hostel_adapter, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_hostel_name.setText(hostelBean.get(i).getName());
        myViewHolder.tv_hostel_address.setText(hostelBean.get(i).getAddress());
        myViewHolder.tv_hostel_code.setText("JOYH" + hostelBean.get(i).getId());
        myViewHolder.tv_distance.setText(hostelBean.get(i).getExact_distance());
        myViewHolder.tv_daily_amount.setText(hostelBean.get(i).getDaily_basis());
        myViewHolder.tv_month_anount.setText(hostelBean.get(i).getMonthly_basis());

        if (hostelBean.get(i).getRatings_count() != null) {
            if(hostelBean.get(i).getRatings_count().equalsIgnoreCase("0")){
                myViewHolder.tv_rating_members.setVisibility(View.GONE);
            }else{
                myViewHolder.tv_rating_members.setVisibility(View.VISIBLE);
                myViewHolder.tv_rating_members.setText("(" + hostelBean.get(i).getRatings_count() + ")");

            }
        }



        if (hostelBean.get(i).getRatings() != null) {
            myViewHolder.tv_rating.setText(hostelBean.get(i).getRatings());
        }else{
            myViewHolder.tv_rating.setText("No Ratings");

        }




        if (hostelBean.get(i).getFavourite().equalsIgnoreCase("no")) {
            myViewHolder.iv_fav.setBackgroundResource(R.drawable.un_fav);
        } else {
            myViewHolder.iv_fav.setBackgroundResource(R.drawable.favorite_red_icon);
        }


        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + hostelBean.get(i).getImage())
                .error(R.drawable.dummy_image).into(myViewHolder.iv_hostel_image);
    }

    @Override
    public int getItemCount() {
        return hostelBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_hostel_name, tv_hostel_address, tv_hostel_code, tv_distance, tv_daily_amount, tv_month_anount, tv_availability, tv_rating, tv_rating_members;
        ImageView iv_fav, iv_star, iv_hostel_image;
        LinearLayout ll_hostel_item, ll_direction;

        public MyViewHolder(View view) {
            super(view);
            tv_hostel_name = itemView.findViewById(R.id.tv_hostel_name);
            tv_hostel_address = itemView.findViewById(R.id.tv_hostel_address);
            tv_hostel_code = itemView.findViewById(R.id.tv_hostel_code);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_daily_amount = itemView.findViewById(R.id.tv_daily_amount);
            tv_month_anount = itemView.findViewById(R.id.tv_month_anount);
            tv_availability = itemView.findViewById(R.id.tv_availability);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_rating_members = itemView.findViewById(R.id.tv_rating_members);
            ll_hostel_item = itemView.findViewById(R.id.ll_hostel_item);
            iv_hostel_image = itemView.findViewById(R.id.iv_hostel_image);
            ll_direction = itemView.findViewById(R.id.ll_direction);


            iv_fav = itemView.findViewById(R.id.iv_fav);
            iv_star = itemView.findViewById(R.id.iv_star);

            ll_hostel_item.setOnClickListener(this);
            ll_direction.setOnClickListener(this);
            iv_fav.setOnClickListener(this);
            tv_availability.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }


}
