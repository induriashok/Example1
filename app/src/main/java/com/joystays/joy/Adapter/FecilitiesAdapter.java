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
import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.HostelDetailsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FecilitiesAdapter extends RecyclerView.Adapter<FecilitiesAdapter.ViewHolder> {

    private Context context;
    private List<HostelDetailsPojo.ResponseBean.FacilitiesBean> facilities;
    private FecilitiesAdapter.OnitemClickListener mListner;

    public FecilitiesAdapter(HostelDetailsActivity hostelDetailsActivity, List<HostelDetailsPojo.ResponseBean.FacilitiesBean> facilities) {
        this.context = hostelDetailsActivity;
        this.facilities = facilities;
    }
    public void setOnItemClickListener(FecilitiesAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fecilities, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + facilities.get(i).getIcon()).error(R.drawable.no_image).into(viewHolder.iv_feci_img);
        viewHolder.tv_feci_name.setText(facilities.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_feci_img;
        TextView tv_feci_name;
        LinearLayout wifi_linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_feci_img = itemView.findViewById(R.id.iv_feci_img);
            tv_feci_name = itemView.findViewById(R.id.tv_feci_name);
            wifi_linear = itemView.findViewById(R.id.wifi_linear);

            wifi_linear.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());




            }
        }
    }
}
