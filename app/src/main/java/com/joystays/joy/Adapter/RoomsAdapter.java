package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.pojos.RoomDetailsPojo;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private RoomDetailsPojo roomDetailsPojo;
    private Context context;

    public RoomsAdapter(RoomDetailsPojo roomDetailsPojo, Context context) {
        this.roomDetailsPojo = roomDetailsPojo;
        this.context = context;

    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }
    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_room, viewGroup, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bed_booking.setText(roomDetailsPojo.getResponse().get(i).getRoom_name());
        viewHolder.tv_sharing.setText(roomDetailsPojo.getResponse().get(i).getShare() + " Share");
        int count = roomDetailsPojo.getResponse().get(i).getAvailable_beds();

        if(count == 0){
            viewHolder.tv_beds_count.setText("Nill");
            viewHolder.tv_beds_count.setTextColor(ContextCompat.getColor(context, R.color.text_red));


        }else{
            viewHolder.tv_beds_count.setText(count + " Beds");
            viewHolder.tv_beds_count.setTextColor(ContextCompat.getColor(context, R.color.lite_green));

        }
       // viewHolder.tv_beds_count.setText(roomDetailsPojo.getResponse().get(i).getBeds() + " Beds");

        if(roomDetailsPojo.getResponse().get(i).getAc().equalsIgnoreCase("no")){
            viewHolder.bed_booking.setTextColor(ContextCompat.getColor(context, R.color.nonacbed));
           // viewHolder.tv_beds_count.setTextColor(ContextCompat.getColor(context, R.color.nonacbed));

        }else{
            viewHolder.bed_booking.setTextColor(ContextCompat.getColor(context, R.color.acbed));
          //  viewHolder.tv_beds_count.setTextColor(ContextCompat.getColor(context, R.color.acbed));

        }

    }
    @Override
    public int getItemCount() {
        return roomDetailsPojo.getResponse().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_sharing, bed_booking, tv_beds_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_beds_count = itemView.findViewById(R.id.tv_beds_count);
            tv_sharing = itemView.findViewById(R.id.tv_sharing);
            bed_booking = itemView.findViewById(R.id.bed_booking);
            bed_booking.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
