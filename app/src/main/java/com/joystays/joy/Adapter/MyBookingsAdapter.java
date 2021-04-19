package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.BookingsPojo;
import com.squareup.picasso.Picasso;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.ViewHolder> {
    private Context context;
    private BookingsPojo bookingsPojo;

    public MyBookingsAdapter(FragmentActivity activity, BookingsPojo bookingsPojo) {
        this.bookingsPojo = bookingsPojo;
        this.context = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bookings, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + bookingsPojo.getResponse().get(i).getImage()).into(viewHolder.iv_img);
        viewHolder.tv_hostel_name.setText(bookingsPojo.getResponse().get(i).getName());
        viewHolder.tv_address.setText(bookingsPojo.getResponse().get(i).getAddress());

        if(bookingsPojo.getResponse().get(i).getRatings()!=null) {
            viewHolder.ll_rating.setVisibility(View.VISIBLE);
            viewHolder.tv_ratings.setText(bookingsPojo.getResponse().get(i).getRatings());
        }
        else {
            viewHolder.ll_rating.setVisibility(View.GONE);
        }
       // viewHolder.tv_booking_date.setText(bookingsPojo.getResponse().get(i).get());
        /*if (bookingsPojo.getResponse().get(i).getFavourite().equalsIgnoreCase("no")) {
            viewHolder.iv_fav.setBackgroundResource(R.drawable.un_fav);
        } else {
            viewHolder.iv_fav.setBackgroundResource(R.drawable.favorite_red_icon);
        }*/
    }

    @Override
    public int getItemCount() {
        return bookingsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img, iv_fav;
        TextView tv_hostel_name, tv_address, tv_ratings, tv_booking_date;
        LinearLayout ll_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_fav = itemView.findViewById(R.id.iv_fav);
            tv_hostel_name = itemView.findViewById(R.id.tv_hostel_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_ratings = itemView.findViewById(R.id.tv_ratings);
            ll_rating = itemView.findViewById(R.id.ll_rating);
            tv_booking_date = itemView.findViewById(R.id.tv_booking_date);
        }
    }
}
