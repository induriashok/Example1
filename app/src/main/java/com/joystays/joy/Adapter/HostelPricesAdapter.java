package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.pojos.HostelPricesPojo;

public class HostelPricesAdapter extends RecyclerView.Adapter<HostelPricesAdapter.ViewHolder> {
    private Context context;
    private HostelPricesPojo hostelPricesPojo;


    public HostelPricesAdapter(Context context, HostelPricesPojo hostelPricesPojo) {
        this.context = context;
        this.hostelPricesPojo = hostelPricesPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_prices, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String share = "";
        viewHolder.tv_sn.setText(i+1 + "");
        viewHolder.tv_advance.setText(hostelPricesPojo.getResponse().get(i).getAdvance());
        viewHolder.tv_owner.setText(hostelPricesPojo.getResponse().get(i).getOwner());
        viewHolder.tv_sharing.setText(hostelPricesPojo.getResponse().get(i).getShare_alpha());
        viewHolder.tv_joy_price.setText(hostelPricesPojo.getResponse().get(i).getJoy_price());

    }

    @Override
    public int getItemCount() {
        return hostelPricesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sn, tv_sharing, tv_owner, tv_advance, tv_joy_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sn = itemView.findViewById(R.id.tv_sn);
            tv_sharing = itemView.findViewById(R.id.tv_sharing);
            tv_owner = itemView.findViewById(R.id.tv_owner);
            tv_advance = itemView.findViewById(R.id.tv_advance);
            tv_joy_price = itemView.findViewById(R.id.tv_joy_price);
        }
    }
}
