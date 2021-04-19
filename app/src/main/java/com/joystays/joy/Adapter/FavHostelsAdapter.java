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
import com.joystays.joy.pojos.FavHostelsPojo;
import com.squareup.picasso.Picasso;

public class FavHostelsAdapter extends RecyclerView.Adapter<FavHostelsAdapter.ViewHolder> {

    private Context context;
    private FavHostelsPojo favHostelsPojo;

    public FavHostelsAdapter(FragmentActivity activity, FavHostelsPojo favHostelsPojo) {
        this.context = activity;
        this.favHostelsPojo = favHostelsPojo;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_hostel_adapter, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_hostel_name.setText(favHostelsPojo.getResponse().get(i).getName());
        viewHolder.tv_hostel_address.setText(favHostelsPojo.getResponse().get(i).getAddress());
        viewHolder.tv_hostel_code.setText("JOYH" + favHostelsPojo.getResponse().get(i).getOwner_id());
        viewHolder.tv_distance.setText(favHostelsPojo.getResponse().get(i).getExact_distance());
        viewHolder.tv_daily_amount.setText(favHostelsPojo.getResponse().get(i).getDaily_basis());
        viewHolder.tv_month_anount.setText(favHostelsPojo.getResponse().get(i).getMonthly_basis());
        viewHolder.iv_fav.setBackgroundResource(R.drawable.favorite_red_icon);



        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL +favHostelsPojo.getResponse().get(i).getImage())
                .error(R.drawable.dummy_image)
                .into(viewHolder.iv_hostel_image);


    }

    @Override
    public int getItemCount() {
        return favHostelsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_hostel_name, tv_hostel_address, tv_hostel_code, tv_distance, tv_daily_amount, tv_month_anount, tv_availability, tv_rating, tv_rating_members;
        ImageView iv_fav, iv_star,iv_hostel_image;
        LinearLayout ll_hostel_item,ll_direction;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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
            iv_fav = itemView.findViewById(R.id.iv_fav);
            iv_star = itemView.findViewById(R.id.iv_star);
            ll_direction = itemView.findViewById(R.id.ll_direction);
            iv_hostel_image = itemView.findViewById(R.id.iv_hostel_image);

            ll_hostel_item.setOnClickListener(this);
            tv_availability.setOnClickListener(this);
            ll_direction.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
