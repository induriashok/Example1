package com.joystays.joy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joystays.joy.R;
import com.joystays.joy.activities.SelectFloorandRoomActivity;

public class HostelFloorAdapter extends RecyclerView.Adapter<HostelFloorAdapter.MyViewHolder> {
    Context context;


    public HostelFloorAdapter(SelectFloorandRoomActivity selectFloorandRoomActivity) {
        this.context = selectFloorandRoomActivity;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_floor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
