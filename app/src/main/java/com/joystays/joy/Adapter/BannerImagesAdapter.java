package com.joystays.joy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joystays.joy.R;
import com.joystays.joy.activities.BannerImagesActivity;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.BedsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerImagesAdapter extends RecyclerView.Adapter<BannerImagesAdapter.ViewHolder>{
    Context context;
    private BedsPojo bedsPojo;
    List<String> images;

    public BannerImagesAdapter(BannerImagesActivity bannerImagesActivity, List<String> images) {
        this.images = images;
        this.context = bannerImagesActivity;


    }

    @NonNull
    @Override
    public BannerImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.home_edoitorslideview, parent, false);
        return new BannerImagesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerImagesAdapter.ViewHolder holder, int position) {

           Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + images.get(position))
                            .error(R.drawable.no_image)
                            .into(holder.iv_slide);


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_slide;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_slide = itemView.findViewById(R.id.iv_slide);
        }
    }
}
