package com.joystays.joy.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joystays.joy.R;
import com.joystays.joy.customfonts.CustomTextViewNormal;

public class SearchFragment extends Fragment {

    // SwitchButton switch_button_one, switch_button_two, switch_button_three;
    //   ImageView iv_lock_one, iv_lock_two, iv_lock_three;
    String onclick = "1";

   /* RecyclerView search_recycler;
    SearchHostelAdapter searchHostelAdapter;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.offers_layout, container, false);
//        switch_button_one = view.findViewById(R.id.switch_button_one);
//        switch_button_two = view.findViewById(R.id.switch_button_two);
//        switch_button_three = view.findViewById(R.id.switch_button_three);



/*
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        search_recycler.setLayoutManager(mLayoutManager);
        searchHostelAdapter = new SearchHostelAdapter(this.getActivity());
        search_recycler.setAdapter(searchHostelAdapter);*/


       /* LinearLayout linear_address = (LinearLayout) ((Activity) getActivity()).findViewById( R.id.linear_address );
        linear_address.setVisibility( View.GONE );
*/
        ImageView iv_gps = (ImageView) ((Activity) getActivity()).findViewById(R.id.iv_gps);
        iv_gps.setVisibility(View.GONE);

        View view_search = (View) ((Activity) getActivity()).findViewById(R.id.view_search);
        view_search.setVisibility(View.GONE);


        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.GONE);

        ImageView nav_menu_img = (ImageView) ((Activity) getActivity()).findViewById(R.id.nav_menu_img);
        nav_menu_img.setImageResource(R.drawable.user_menu);

        ImageView iv_search = (ImageView) ((Activity) getActivity()).findViewById(R.id.iv_search);
        iv_search.setImageResource(R.drawable.offer_icon_red);

        CustomTextViewNormal tv_search = (CustomTextViewNormal) ((Activity) getActivity()).findViewById(R.id.tv_search);
        tv_search.setText("Offers");


//        switchButton.setChecked(  );
//        switchButton.isChecked();
//        switchButton.toggle();     //switch state
//        switchButton.toggle( false );//switch without animation
//        switchButton.setShadowEffect( true );//disable shadow effect
//        switchButton.setEnabled( false );//disable button
//        switchButton.setEnableEffect( false );//disable the switch animation

//
//        switch_button_one.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//
//                if (isChecked) {
//                    Toast.makeText(getContext().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT).show();
//                } else {
//
//                }
//            }
//        });
//        switch_button_two.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(getContext().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT).show();
//                } else {
//
//                }
//
//            }
//        });
//        switch_button_three.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(getContext().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT).show();
//                } else {
//
//                }
//
//            }
//        });


        return view;
    }

}
