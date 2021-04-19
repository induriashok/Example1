package com.joystays.joy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.activities.SelectFloorandRoomActivity;
import com.joystays.joy.pojos.FloorsPojo;

public class FloorsAdapter extends RecyclerView.Adapter<FloorsAdapter.ViewHolder> {
    private Context context;
    private int floors_count;
    public static int floor_position = 0;
    FloorsPojo floorsPojo;



 /*   public  FloorsAdapter(Context applicationContext, int floors_count) {
        this.context = applicationContext;
        this.floors_count = floors_count;
    }*/

    private OnitemClickListener mListner;

    public FloorsAdapter(SelectFloorandRoomActivity applicationContext, FloorsPojo floorsPojo) {
        this.context = applicationContext;
        this.floorsPojo = floorsPojo;
    }

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_floor, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //viewHolder.tv_floor.setText("Floor " + i);
        viewHolder.tv_floor.setText(floorsPojo.getResponse().get(i).getFloorname());

        if (i == floor_position) {
            viewHolder.ll_floor.setBackgroundResource(R.drawable.floor_text_green);
            viewHolder.tv_floor.setTextColor(R.color.white);
        } else {
            viewHolder.ll_floor.setBackgroundResource(R.drawable.floor_text_gray);
            viewHolder.tv_floor.setTextColor(R.color.text_black);
        }


    }

    @Override
    public int getItemCount() {
        return floorsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_floor;
        TextView tv_floor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_floor = itemView.findViewById(R.id.ll_floor);
            tv_floor = itemView.findViewById(R.id.tv_floor);
            ll_floor.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
