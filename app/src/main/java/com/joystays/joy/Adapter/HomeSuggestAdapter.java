package com.joystays.joy.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joystays.joy.activities.HostelDetailsActivity;
import com.joystays.joy.R;

public class HomeSuggestAdapter extends RecyclerView.Adapter<HomeSuggestAdapter.MyViewHolder> {
    Context context;

    public HomeSuggestAdapter(Context foodActivity) {
        this.context = foodActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_suggest_adapter, viewGroup, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Intent i1 = new Intent(context, HostelDetailsActivity.class);
                    context.startActivity(i1);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return 10;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }


}
