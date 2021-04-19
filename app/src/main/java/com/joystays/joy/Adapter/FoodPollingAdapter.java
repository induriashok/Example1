package com.joystays.joy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joystays.joy.FoodPollActivity;
import com.joystays.joy.R;
import com.joystays.joy.base.AdapterCallback;
import com.joystays.joy.pojos.FoodPollingPojo;
import com.joystays.joy.utils.Util;

import java.util.List;

public class FoodPollingAdapter extends RecyclerView.Adapter<FoodPollingAdapter.MyViewHolder> {

    Context context;
    FoodPollingPojo foodPollingPojo;
    List<String> fooditems;
    AdapterCallback adapterCallback;
    List<String> status;



    public FoodPollingAdapter(FoodPollActivity foodPollActivity, List<String> fooditems, AdapterCallback adapterCallback, List<String> status) {
        this.context = foodPollActivity;
        this.fooditems = fooditems;
        this.status = status;
        this.adapterCallback = adapterCallback;

    }








    @NonNull
    @Override
    public FoodPollingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_poll_adapter, parent, false);
        return new FoodPollingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodPollingAdapter.MyViewHolder holder, final int position) {
       final String name = fooditems.get(position);
       final String status_str = status.get(position);

       holder.tv_item.setText(name);


       if(status_str.equalsIgnoreCase("no")){

           holder.grenn_like.setBackgroundResource(R.drawable.likegrey);
          // holder.iv_dislike.setBackgroundResource(R.drawable.like_gray);

       }else{


           holder.grenn_like.setBackgroundResource(R.drawable.like_green);
         //  holder.iv_dislike.setBackgroundResource(R.drawable.dislike);
       }



       holder.grenn_like.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               if(status_str.equalsIgnoreCase("no")){


                   holder.grenn_like.setBackgroundResource(R.drawable.like_green);
                 //  holder.iv_dislike.setBackgroundResource(R.drawable.like_gray);

                   if (adapterCallback != null) {
                       adapterCallback.clickevent(holder.grenn_like, position, name);
                   } else {
                       Util.getInstance().cusToast(context, "nodata");

                   }

               }else{

                  /* FoodPollActivity.poll_status= "no";

                   holder.grenn_like.setBackgroundResource(R.drawable.dislike);
                   holder.iv_dislike.setBackgroundResource(R.drawable.like_gray);

                   if (adapterCallback != null) {
                       adapterCallback.clickevent(holder.grenn_like, position, name);
                   } else {
                       Util.getInstance().cusToast(context, "nodata");

                   }
*/



               }

           }
       });

    /*   holder.iv_dislike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(status_str.equalsIgnoreCase("no")){

                 //  FoodPollActivity.poll_status= "yes";

                   holder.grenn_like.setBackgroundResource(R.drawable.likegrey);
                   holder.iv_dislike.setBackgroundResource(R.drawable.dislike);

                   if (adapterCallback != null) {
                       adapterCallback.clickevent(holder.grenn_like, position, name);
                   } else {
                       Util.getInstance().cusToast(context, "nodata");

                   }

               }else{

                   *//*FoodPollActivity.poll_status= "no";

                   holder.grenn_like.setBackgroundResource(R.drawable.like_green);
                   holder.iv_dislike.setBackgroundResource(R.drawable.dislike);

                   if (adapterCallback != null) {
                       adapterCallback.clickevent(holder.grenn_like, position, name);
                   } else {
                       Util.getInstance().cusToast(context, "nodata");

                   }
*//*



               }

           }
       });
*/








    }

    @Override
    public int getItemCount() {
        return fooditems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item,tv_like;
        private ImageView grenn_like,iv_dislike;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item = itemView.findViewById(R.id.tv_item);
            grenn_like = itemView.findViewById(R.id.grenn_like);
            iv_dislike = itemView.findViewById(R.id.iv_dislike);
            tv_like = itemView.findViewById(R.id.tv_like);


        }


    }
}
