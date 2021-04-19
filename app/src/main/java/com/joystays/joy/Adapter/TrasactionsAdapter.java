package com.joystays.joy.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joystays.joy.R;
import com.joystays.joy.activities.TransactionDetailsActivity;
import com.joystays.joy.pojos.TransactionsPojo;

public class TrasactionsAdapter extends RecyclerView.Adapter<TrasactionsAdapter.ViewHolder> {
    private Context context;
    private TransactionsPojo transactionsPojo;
    String new_txn_id="";

    public TrasactionsAdapter(Context context, TransactionsPojo transactionsPojo) {
        this.context = context;
        this.transactionsPojo = transactionsPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_payments, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_hostel_name.setText(transactionsPojo.getResponse().get(i).getName());
        viewHolder.tv_paid_amount.setText(transactionsPojo.getResponse().get(i).getPaid_amount() + "/-");
        if (transactionsPojo.getResponse().get(i).getPayment_mode().equalsIgnoreCase("cash")) {
            viewHolder.ll_online.setVisibility(View.GONE);
            viewHolder.tv_cash_payment.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ll_online.setVisibility(View.VISIBLE);
            viewHolder.tv_cash_payment.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return transactionsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hostel_name, tv_paid_amount, tv_cash_payment;
        LinearLayout ll_online, transaction_linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hostel_name = itemView.findViewById(R.id.tv_hostel_name);
            tv_paid_amount = itemView.findViewById(R.id.tv_paid_amount);
            tv_cash_payment = itemView.findViewById(R.id.tv_cash_payment);
            ll_online = itemView.findViewById(R.id.ll_online);
            transaction_linear = itemView.findViewById(R.id.transaction_linear);
            transaction_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(transactionsPojo.getResponse().get(getAdapterPosition()).getTransaction_id().equalsIgnoreCase(null)){
                         new_txn_id = "";
                    }else{
                         new_txn_id = transactionsPojo.getResponse().get(getAdapterPosition()).getTransaction_id();

                    }



                    Intent intent = new Intent(context, TransactionDetailsActivity.class);
                    intent.putExtra("paid_amount", transactionsPojo.getResponse().get(getAdapterPosition()).getPaid_amount());
                    intent.putExtra("hostel_name", transactionsPojo.getResponse().get(getAdapterPosition()).getName());
                    intent.putExtra("payment_date", transactionsPojo.getResponse().get(getAdapterPosition()).getCreated_on());
                    intent.putExtra("BID", transactionsPojo.getResponse().get(getAdapterPosition()).getBID());
                    intent.putExtra("txn_id", transactionsPojo.getResponse().get(getAdapterPosition()).getTransaction_id());
                    intent.putExtra("user_name", transactionsPojo.getResponse().get(getAdapterPosition()).getUsers_name());
                    context.startActivity(intent);
                }
            });

        }
    }
}
