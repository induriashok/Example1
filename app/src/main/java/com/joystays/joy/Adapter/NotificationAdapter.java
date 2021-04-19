package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.activities.NotificationActivity;
import com.joystays.joy.pojos.NotificationsPojo;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    Context context;
    NotificationsPojo notificationsPojo;

    public NotificationAdapter(NotificationActivity notificationActivity, NotificationsPojo notificationsPojo) {
        this.context = notificationActivity;
        this.notificationsPojo = notificationsPojo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_adapter, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_notification.setText(notificationsPojo.getResponse().get(i).getMessage());
        myViewHolder.title.setText(notificationsPojo.getResponse().get(i).getTitle());
        //myViewHolder.tv_time.setText(notificationsPojo.getResponse().get(i).getPosted_on());
        myViewHolder.tv_time.setText(notificationsPojo.getResponse().get(i).getPosted_on());

    }

    @Override
    public int getItemCount() {
        return notificationsPojo.getResponse().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time, tv_notification, title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_notification = itemView.findViewById(R.id.tv_notification);
            title = itemView.findViewById(R.id.title);

        }
    }
}
