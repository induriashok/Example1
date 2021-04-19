package com.joystays.joy.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joystays.joy.activities.HomeActivity;
import com.joystays.joy.R;
import com.joystays.joy.activities.PaymentMethodActivity;
import com.joystays.joy.base.BaseAbstractFragment;
import com.joystays.joy.mvp.contract.fragment.MyWalletFragContract;
import com.joystays.joy.mvp.presenter.fragment.MyWalletFagImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.MyWalletPojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WalletFragment extends BaseAbstractFragment<MyWalletFagImpl> implements MyWalletFragContract.IView, APIResponseCallback {
    private ImageView back_arrow, iv_profile;

    private UserSession userSession;
    private String token, user_id;
    private MyWalletPojo myWalletPojo;
    private TextView tv_joy_amount, tv_applied_joy_amount, tv_gift_voucher, tv_total_amount, tv_name, tv_uid_no, tv_bed_no, tv_paid_date, tv_bill_generated, tv_room_no, tv_share_type, tv_due_amount;
    private SimpleDateFormat dateFormatprev;
    private Date d;
    private SimpleDateFormat dateFormat;
    private TextView effect_amount, tv_refund_amnt, tv_package, refund_tv;
    private TextView tv_effect_text, nodata_tv, tv_referal_amount;
    private LinearLayout ll_effect_amount, ll_joy_applied_amount, linear_confirm, dta_ll, data_ll, ll_refund;
    float effective_final_amount = 0;
    int wallet_deductions = 0;
    private String joy_amount_used = "0", room_change_id = "", ubid = "", isApproved,refreal_amount ="";
    private CardView data_cv;
    private int total_amount=0;
    String booking_id="",owner_id="",floor_id="",room_id="",bed_id="",share_type="",advance_amount="",refund_amount="";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();


        userSession = new UserSession(getActivity());

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        isApproved = userSession.getUserDetails().get("approved");


        CardView card_v = (CardView) ((Activity) getActivity()).findViewById(R.id.card_v);
        card_v.setVisibility(View.GONE);
        back_arrow = view.findViewById(R.id.back_arrow);
        tv_joy_amount = view.findViewById(R.id.tv_joy_amount);
        tv_referal_amount = view.findViewById(R.id.tv_referal_amount);
        tv_gift_voucher = view.findViewById(R.id.tv_gift_voucher);
        tv_total_amount = view.findViewById(R.id.tv_total_amount);
        tv_name = view.findViewById(R.id.tv_name);
        tv_uid_no = view.findViewById(R.id.tv_uid_no);
        tv_bed_no = view.findViewById(R.id.tv_bed_no);
        tv_paid_date = view.findViewById(R.id.tv_paid_date);
        tv_bill_generated = view.findViewById(R.id.tv_bill_generated);
        tv_room_no = view.findViewById(R.id.tv_room_no);
        tv_share_type = view.findViewById(R.id.tv_share_type);
        tv_due_amount = view.findViewById(R.id.tv_due_amount);
        iv_profile = view.findViewById(R.id.iv_profile);
        effect_amount = view.findViewById(R.id.effect_amount);
        tv_effect_text = view.findViewById(R.id.tv_effect_text);
        ll_effect_amount = view.findViewById(R.id.ll_effect_amount);
        ll_joy_applied_amount = view.findViewById(R.id.ll_joy_applied_amount);
        tv_applied_joy_amount = view.findViewById(R.id.tv_applied_joy_amount);
        linear_confirm = view.findViewById(R.id.linear_confirm);
        data_cv = view.findViewById(R.id.data_cv);
        tv_refund_amnt = view.findViewById(R.id.tv_refund_amnt);
        tv_package = view.findViewById(R.id.tv_package);
        dta_ll = view.findViewById(R.id.dta_ll);
        data_ll = view.findViewById(R.id.data_ll);
        ll_refund = view.findViewById(R.id.ll_refund);
        refund_tv = view.findViewById(R.id.refund_tv);
        nodata_tv = view.findViewById(R.id.nodata_tv);
        linear_confirm.setVisibility(View.GONE);
        dateFormatprev = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat = new SimpleDateFormat("dd MMM yyyy");


     /*   if(isApproved.equalsIgnoreCase("yes")){
            linear_confirm.setVisibility(View.VISIBLE);

        }else{
            linear_confirm.setVisibility(View.GONE);

        }*/

        linear_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(getActivity(), PaymentMethodActivity.class);

                intent1.putExtra("joy_wallet_used_amount", joy_amount_used);
                intent1.putExtra("room_change_amount_id", room_change_id);
                intent1.putExtra("from", "wallet");
                intent1.putExtra("UBID", ubid);
                intent1.putExtra("hostel_id", myWalletPojo.getResponse().getHostel_id());
                intent1.putExtra("room_type", myWalletPojo.getResponse().getAc());
                intent1.putExtra("package_type", myWalletPojo.getResponse().getPackage_type());
                intent1.putExtra("refreal_amount", refreal_amount);
                intent1.putExtra("booking_id", myWalletPojo.getResponse().getBooking_id());
                intent1.putExtra("owner_id", myWalletPojo.getResponse().getOwner_id());
                intent1.putExtra("floor_id", myWalletPojo.getResponse().getFloor());
                intent1.putExtra("room_id", myWalletPojo.getResponse().getRoom_id());
                intent1.putExtra("bed_id", myWalletPojo.getResponse().getBed_id());
                intent1.putExtra("share_type", myWalletPojo.getResponse().getShare());
                intent1.putExtra("advance_amount", myWalletPojo.getResponse().getAdvance_amount());
                intent1.putExtra("refundable_amount", myWalletPojo.getResponse().getRefundable_amount());


                startActivity(intent1);
                System.out.println("joy_amount_used"+joy_amount_used+"room_change_id"+room_change_id);


               /* Intent intent1 = new Intent(getActivity(), PaymentMethodActivity.class);
                intent1.putExtra("from", "wallet");
                startActivity(intent1);
*/
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("to", "home");
                startActivity(intent);
            }
        });
        callMyWalletWs();
    }

    private void callMyWalletWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        //requestBody.put("user_id", "1");
        presenter.myWallet(getActivity(), this, requestBody);

    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.wallet_fragment, null);
        return view;
    }


    @Override
    public void setPresenter() {
        presenter = new MyWalletFagImpl(this, getActivity());
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, getActivity());
            } else if (NetworkConstants.RequestCode.MY_WALLET == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    data_cv.setVisibility(View.VISIBLE);
                    linear_confirm.setVisibility(View.VISIBLE);
                    ll_refund.setVisibility(View.VISIBLE);

                    myWalletPojo = new Gson().fromJson(responseString, MyWalletPojo.class);
                    tv_bed_no.setText(myWalletPojo.getResponse().getBed_name());
                    tv_name.setText(myWalletPojo.getResponse().getName());
                    ubid = myWalletPojo.getResponse().getUBID();
                    tv_uid_no.setText(ubid);
                  //  tv_refund_amnt.setText(myWalletPojo.getResponse().getRefundable_amount());
                  //  tv_refund_amnt.setText("0");
                    if(myWalletPojo.getResponse().getRefundable_amount()==null){
                        refund_tv.setText("0");
                        tv_refund_amnt.setText("0");
                    }else{
                        refund_tv.setText(myWalletPojo.getResponse().getRefundable_amount());
                        tv_refund_amnt.setText("Rs "+myWalletPojo.getResponse().getRefundable_amount());
                    }

                    tv_package.setText(myWalletPojo.getResponse().getPackage_type());


                    if (jsonObject.getJSONObject("response").length() == 0||myWalletPojo.getResponse()==null) {
                        linear_confirm.setVisibility(View.GONE);
                        data_cv.setVisibility(View.GONE);
                    } else {

                        total_amount=total_amount+Integer.parseInt(myWalletPojo.getResponse().getRefundable_amount());
                        if (myWalletPojo.getResponse().getPackage_type().equalsIgnoreCase("daily")) {

                            if (myWalletPojo.getResponse().getBooking_status().equalsIgnoreCase("approved")) {
                                data_cv.setVisibility(View.VISIBLE);
                                linear_confirm.setVisibility(View.GONE);

                                String deliveryDate = myWalletPojo.getResponse().getNext_due_date().substring(0, 10);
                                d = dateFormatprev.parse(deliveryDate);
                                String changedDate = dateFormat.format(d);
                                tv_bill_generated.setText(changedDate);

                            } else if (myWalletPojo.getResponse().getBooking_status().equalsIgnoreCase("pending")) {
                                linear_confirm.setVisibility(View.VISIBLE);
                                data_cv.setVisibility(View.GONE);


                            } else {
                                linear_confirm.setVisibility(View.GONE);
                                data_ll.setVisibility(View.GONE);
                                nodata_tv.setVisibility(View.VISIBLE);

                            }
                            data_cv.setVisibility(View.GONE);

                        } else {

                            String deliveryDate = myWalletPojo.getResponse().getNext_due_date().substring(0, 10);
                            d = dateFormatprev.parse(deliveryDate);
                            String changedDate = dateFormat.format(d);
                            tv_bill_generated.setText(changedDate);

                            if (myWalletPojo.getResponse().getBooking_status().equalsIgnoreCase("approved")) {
                                data_cv.setVisibility(View.VISIBLE);

                                if (myWalletPojo.getResponse().getCurrent_month_status().equalsIgnoreCase("paid")) {
                                    linear_confirm.setVisibility(View.GONE);

                                } else {
                                    linear_confirm.setVisibility(View.VISIBLE);
                                }

                            } else if (myWalletPojo.getResponse().getBooking_status().equalsIgnoreCase("pending")) {

                                linear_confirm.setVisibility(View.GONE);

                            } else {
                                linear_confirm.setVisibility(View.GONE);
                                data_ll.setVisibility(View.GONE);
                                nodata_tv.setVisibility(View.VISIBLE);


                            }

                        }

                        effective_final_amount = Integer.parseInt(myWalletPojo.getResponse().getNext_month_amount());

                        String last_paid = myWalletPojo.getResponse().getLast_payment_date().substring(0, 10);

                        d = dateFormatprev.parse(last_paid);

                        String last_paid_date = dateFormat.format(d);
                        tv_paid_date.setText(last_paid_date);

                    }








                   /* Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate1 = df1.format(c);
                    String formattedDatedue = df1.format(deliveryDate);
                    System.out.println("formattedDate" + formattedDate1);


*/




                    if (myWalletPojo.getJoy_rupees() ==null) {
                       tv_due_amount.setText("Rs "+myWalletPojo.getResponse().getAmount());
                     //   tv_due_amount.setText("Rs "+myWalletPojo.getResponse().getNext_month_amount());

                    } else {

                        if (Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_left()) >= Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_per_transaction())) {
                            joy_amount_used = myWalletPojo.getJoy_rupees().getAmount_per_transaction();
                            effective_final_amount = effective_final_amount - Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_per_transaction());
                            tv_applied_joy_amount.setText(Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_per_transaction()) + "");
                            total_amount= total_amount+Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount());
                        } else {
                            tv_applied_joy_amount.setText(Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_left()) + "");
                            wallet_deductions = Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_left());
                            effective_final_amount = effective_final_amount - Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_left());
                            joy_amount_used = myWalletPojo.getJoy_rupees().getAmount_left();
                            total_amount= total_amount+Integer.parseInt(myWalletPojo.getJoy_rupees().getAmount_left());
                        }
                    }


//  tv_gift_voucher.setText(myWalletPojo.getResponse());



                    if (myWalletPojo.getJoy_rupees() == null) {
                        tv_joy_amount.setText("0");
                    } else {

                        tv_joy_amount.setText(myWalletPojo.getJoy_rupees().getAmount_left());
                    }

                    Picasso.with(getActivity()).load(NetworkConstants.URL.Imagepath_URL + myWalletPojo.getResponse().getProfile_pic()).into(iv_profile);

                    if (myWalletPojo.getRoom_change_amounts() != null && myWalletPojo.getRoom_change_amounts().size() > 0) {
                        room_change_id = myWalletPojo.getRoom_change_amounts().get(0).getId();

                        if (myWalletPojo.getRoom_change_amounts().get(0).getAmount_type().equalsIgnoreCase("add")) {
                            ll_effect_amount.setVisibility(View.VISIBLE);
                            int pending_amount = Integer.parseInt(myWalletPojo.getResponse().getAmount());
                            int charges = Integer.parseInt(myWalletPojo.getRoom_change_amounts().get(0).getPrice());
                            effective_final_amount = effective_final_amount + charges;

                            tv_effect_text.setText("Due to changing bed, extra amount to be paid ");
                            effect_amount.setText(myWalletPojo.getRoom_change_amounts().get(0).getPrice());
                        } else {
                            ll_effect_amount.setVisibility(View.VISIBLE);
                            int pending_amount = Integer.parseInt(myWalletPojo.getResponse().getAmount());
                            int charges = Integer.parseInt(myWalletPojo.getRoom_change_amounts().get(0).getPrice());
                            effective_final_amount = pending_amount - charges;
                            tv_effect_text.setText("Due to changing bed,  amount to be deducted ");
                            effect_amount.setText(myWalletPojo.getRoom_change_amounts().get(0).getPrice());
                        }

                    } else {
                        ll_effect_amount.setVisibility(View.GONE);
                    }

                    if (myWalletPojo.getReferral_amount() != null) {
                      //  tv_referal_amount.setText(myWalletPojo.getReferral_amount());
                        total_amount=total_amount+ Integer.parseInt(myWalletPojo.getReferral_amount());
                        float remaing_referal_amount = 0;

                        if (myWalletPojo.getReferral_amounts_used() != null) {
                            float refreanl_amount = Integer.parseInt(myWalletPojo.getReferral_amount());
                            float refreanl_amount_used = Float.parseFloat(myWalletPojo.getReferral_amounts_used());
                            remaing_referal_amount = refreanl_amount-refreanl_amount_used;
                            tv_referal_amount.setText(String.valueOf(remaing_referal_amount));
                            float referral = Float.parseFloat(myWalletPojo.getReferral_amounts_used());
                            effective_final_amount = effective_final_amount - remaing_referal_amount;

                            if(remaing_referal_amount>250){
                                refreal_amount = "250";
                            }else{
                                refreal_amount = String.valueOf(remaing_referal_amount);

                            }
                        }else{
                            remaing_referal_amount = Float.parseFloat(myWalletPojo.getReferral_amount());
                            tv_referal_amount.setText(myWalletPojo.getReferral_amount());
                            if(remaing_referal_amount>250){
                                refreal_amount = "250";
                            }else{
                                refreal_amount = String.valueOf(remaing_referal_amount);

                            }

                        }

                    } else {
                        tv_referal_amount.setText("0");
                        refreal_amount = "0";
                    }

                    tv_due_amount.setText(effective_final_amount + "");

                 //   tv_due_amount.setText("Rs "+myWalletPojo.getResponse().getNext_month_amount());


                    tv_total_amount.setText(total_amount+"");

                } else {
                    room_change_id = "";
                    linear_confirm.setVisibility(View.GONE);
                    data_cv.setVisibility(View.GONE);
                    ll_refund.setVisibility(View.GONE);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
