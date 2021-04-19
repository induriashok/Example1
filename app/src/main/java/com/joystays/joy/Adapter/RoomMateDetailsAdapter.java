package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.pojos.RoomMateDetailsPojo;

public class RoomMateDetailsAdapter extends RecyclerView.Adapter<RoomMateDetailsAdapter.ViewHolder> {
    private Context context;
    private RoomMateDetailsPojo roomMateDetailsPojo;

    public RoomMateDetailsAdapter(Context context, RoomMateDetailsPojo roomDetailsPojo) {
        this.context = context;
        this.roomMateDetailsPojo = roomDetailsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_roommate_dertails, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_age.setText(roomMateDetailsPojo.getResponse().get(i).getAge());
        viewHolder.tv_name.setText("JOY"+roomMateDetailsPojo.getResponse().get(i).getId());
        viewHolder.bed_name.setText(roomMateDetailsPojo.getResponse().get(i).getBed_name());
        viewHolder.tv_working.setText(roomMateDetailsPojo.getResponse().get(i).getJob_details());
        viewHolder.tv_lang.setText(roomMateDetailsPojo.getResponse().get(i).getLanguages());
        viewHolder.tv_state.setText(roomMateDetailsPojo.getResponse().get(i).getState());
        viewHolder.tv_room.setText(roomMateDetailsPojo.getResponse().get(i).getRoom_name());
    }

    @Override
    public int getItemCount() {
        return roomMateDetailsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_room, tv_name, bed_name, tv_working, tv_lang, tv_state, tv_age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_age = itemView.findViewById(R.id.tv_age);
            tv_room = itemView.findViewById(R.id.tv_room);
            tv_name = itemView.findViewById(R.id.tv_name);
            bed_name = itemView.findViewById(R.id.bed_name);
            tv_working = itemView.findViewById(R.id.tv_working);
            tv_lang = itemView.findViewById(R.id.tv_lang);
            tv_state = itemView.findViewById(R.id.tv_state);
        }
    }
}
