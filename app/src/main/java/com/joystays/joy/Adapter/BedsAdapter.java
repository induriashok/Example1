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
import com.joystays.joy.pojos.BedsPojo;
import com.joystays.joy.utils.Variables;

public class BedsAdapter extends RecyclerView.Adapter<BedsAdapter.ViewHolder> {
    private Context context;
    private BedsPojo bedsPojo;
    public static int selected_bed_count = -1;


    public BedsAdapter(BedsPojo bedsPojo, Context context) {
        this.bedsPojo = bedsPojo;
        this.context = context;
      //  selected_bed_count = -1;
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_bed, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bed_name.setText(bedsPojo.getResponse().get(i).getBed_name());
        viewHolder.tv_lang.setText(bedsPojo.getResponse().get(i).getLanguages());
        String staus = bedsPojo.getResponse().get(i).getDays_left();

        viewHolder.days_left.setVisibility(View.GONE);




        if (bedsPojo.getResponse().get(i).getStatus().equalsIgnoreCase("available")) {
            viewHolder.cv_mate_details.setVisibility(View.GONE);
            viewHolder.iv_bed.setBackgroundResource(R.drawable.bed_white);

            if(Variables.bed_id.equalsIgnoreCase(bedsPojo.getResponse().get(i).getId())){
                viewHolder.iv_bed.setBackgroundResource(R.drawable.bed_green);

            }else{
                viewHolder.iv_bed.setBackgroundResource(R.drawable.bed_white);

            }
           /* if (i == selected_bed_count) {
                viewHolder.iv_bed.setBackgroundResource(R.drawable.bed_green);
            }*/
        } else {
            viewHolder.iv_bed.setBackgroundResource(R.drawable.bed_gray);
            viewHolder.cv_mate_details.setVisibility(View.VISIBLE);
            viewHolder.tv_mate_name.setText("UID : JOY"+bedsPojo.getResponse().get(i).getUser_id());
            viewHolder.tv_lang.setText(bedsPojo.getResponse().get(i).getLanguages());
            viewHolder.bed_number.setText(bedsPojo.getResponse().get(i).getBed_name());

            if(staus.isEmpty()){
                viewHolder.days_left.setVisibility(View.GONE);
            }else{

                viewHolder.days_left.setVisibility(View.VISIBLE);
                viewHolder.days_left.setText("Days Left : "+staus);

            }
        }
    }

    @Override
    public int getItemCount() {
        return bedsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_bed;
        TextView tv_mate_name, tv_lang, bed_number,bed_name,days_left;
        LinearLayout cv_mate_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bed_number = itemView.findViewById(R.id.bed_number);
            iv_bed = itemView.findViewById(R.id.iv_bed);
            tv_mate_name = itemView.findViewById(R.id.tv_mate_name);
            tv_lang = itemView.findViewById(R.id.tv_lang);
            cv_mate_details = itemView.findViewById(R.id.cv_mate_details);
            bed_name = itemView.findViewById(R.id.bed_name);
            days_left = itemView.findViewById(R.id.days_left);
            iv_bed.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
