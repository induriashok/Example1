package com.joystays.joy.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.activities.HostelSearchActivity;
import com.joystays.joy.pojos.NearByHostelsPojo;

import java.util.List;

public class SearchHostelAdapter extends RecyclerView.Adapter<SearchHostelAdapter.MyViewHolder> {
    Context context;
    List<NearByHostelsPojo.ResponseBean> responseBeans;


    public SearchHostelAdapter(HostelSearchActivity hostelSearchActivity, List<NearByHostelsPojo.ResponseBean> responseBeans) {
        this.context = hostelSearchActivity;
        this.responseBeans = responseBeans;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_hostel_adapter, viewGroup, false);

        return new MyViewHolder(itemView);

    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_hostel_name.setText(responseBeans.get(i).getName());
        myViewHolder.tv_adress.setText(responseBeans.get(i).getAddress());
        myViewHolder.tv_distance.setText(responseBeans.get(i).getDistance() + " KMS");
        myViewHolder.tv_type.setText(responseBeans.get(i).getHostel_type());
        myViewHolder.tv_id.setText("JOYH" + responseBeans.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_hostel_name, tv_adress, tv_distance, tv_type, tv_id;
        LinearLayout ll_item;

        public MyViewHolder(View view) {
            super(view);
            tv_hostel_name = view.findViewById(R.id.tv_hostel_name);
            tv_adress = view.findViewById(R.id.tv_adress);
            tv_distance = view.findViewById(R.id.tv_distance);
            tv_type = view.findViewById(R.id.tv_type);
            tv_id = view.findViewById(R.id.tv_id);
            ll_item = view.findViewById(R.id.ll_item);
            ll_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }


}
