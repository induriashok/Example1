package com.joystays.joy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joystays.joy.R;
import com.joystays.joy.pojos.BedsPojo;
import com.joystays.joy.pojos.ChatsPojo;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private Context context;
    private ChatsPojo chatsPojo;
    public static int selected_bed_count = -1;



    public ChatsAdapter(Context context, ChatsPojo chatsPojo) {
        this.context = context;
        this.chatsPojo = chatsPojo;

    }

    @NonNull
    @Override
    public ChatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chat_adapter_layout, parent, false);
        return new ChatsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapter.ViewHolder holder, int position) {

        holder.text.setText(chatsPojo.getResponse().get(position).getMessage());
       String date = String.valueOf(chatsPojo.getResponse().get(position).getCreated_on());
        holder.tv_time.setText(date);


    }

    @Override
    public int getItemCount() {
        return chatsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text, tv_time, bed_number,bed_name,days_left;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            tv_time = itemView.findViewById(R.id.tv_time);



        }
    }
}
