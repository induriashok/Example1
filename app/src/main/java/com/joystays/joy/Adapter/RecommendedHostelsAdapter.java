package com.joystays.joy.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.NearByHostelsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendedHostelsAdapter extends RecyclerView.Adapter<RecommendedHostelsAdapter.ViewHolder> {
    private Context context;
    private List<NearByHostelsPojo.RecommendedHostelsBean> recommended_hostels;

    public RecommendedHostelsAdapter(FragmentActivity activity, List<NearByHostelsPojo.RecommendedHostelsBean> recommended_hostels) {
        this.context = activity;
        this.recommended_hostels = recommended_hostels;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }


    @NonNull
    @Override
    public RecommendedHostelsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_suggest_adapter, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedHostelsAdapter.ViewHolder viewHolder, int i) {
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + recommended_hostels.get(i).getImage()
        ).into(viewHolder.slide_image);
    }

    @Override
    public int getItemCount() {
        return recommended_hostels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView slide_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slide_image = itemView.findViewById(R.id.slide_image);
            slide_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
