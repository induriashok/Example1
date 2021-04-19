package com.joystays.joy.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.LockDetailsPojo;
import com.squareup.picasso.Picasso;
import com.suke.widget.SwitchButton;

public class LockDetaillsAdapter extends RecyclerView.Adapter<LockDetaillsAdapter.ViewHolder> {
    private Context context;
    private LockDetailsPojo lockDetailsPojo;


    public LockDetaillsAdapter(FragmentActivity activity, LockDetailsPojo lockDetailsPojo) {
        this.context = activity;
        this.lockDetailsPojo = lockDetailsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_lock_details, viewGroup, false);

        return new ViewHolder(itemView);
    }


    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + lockDetailsPojo.getResponse().get(i).getProfile_pic()).into(viewHolder.iv_profile);
        viewHolder.tv_name.setText(lockDetailsPojo.getResponse().get(i).getName());
        if (lockDetailsPojo.getResponse().get(i).getRoom_mode().equalsIgnoreCase("locked")) {
            if (lockDetailsPojo.getResponse().get(i).getLocked_by_me().equalsIgnoreCase("Yes")) {
                viewHolder.iv_lock_one.setBackgroundResource(R.drawable.lock_red);
            } else {
                viewHolder.iv_lock_one.setBackgroundResource(R.drawable.lock_gray);
            }
        } else {
            if (lockDetailsPojo.getResponse().get(i).getLocked_by_me().equalsIgnoreCase("Yes")) {
                viewHolder.iv_lock_one.setBackgroundResource(R.drawable.lock_green_open);
            } else {
                viewHolder.iv_lock_one.setBackgroundResource(R.drawable.lock_gray_open);
            }
        }
        if (lockDetailsPojo.getResponse().get(i).isNotification_sent()) {
            viewHolder.switch_button_one.setChecked(true);
        } else {
            viewHolder.switch_button_one.setChecked(false);
        }
        //viewHolder.tv_name.setText("Srikanth");
    }

    @Override
    public int getItemCount() {
        return lockDetailsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_profile, iv_lock_one, iv_call;
        TextView tv_name;
        SwitchButton switch_button_one;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_lock_one = itemView.findViewById(R.id.iv_lock_one);
            iv_call = itemView.findViewById(R.id.iv_call);
            tv_name = itemView.findViewById(R.id.tv_name);
            switch_button_one = itemView.findViewById(R.id.switch_button_one);

            iv_call.setOnClickListener(this);
            iv_lock_one.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
