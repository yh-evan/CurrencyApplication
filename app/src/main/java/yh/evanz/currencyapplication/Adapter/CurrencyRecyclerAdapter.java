package yh.evanz.currencyapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import yh.evanz.currencyapplication.Model.CurrencyWithRate;
import yh.evanz.currencyapplication.R;
import yh.evanz.currencyapplication.SelectCurrencyActivity;

public class CurrencyRecyclerAdapter  extends RecyclerView.Adapter<CurrencyRecyclerAdapter.ViewHolder>{

    private ArrayList<CurrencyWithRate> listOfCurrency;
    private Context mContext;
    private String baseCurrency;

    @SuppressLint("ResourceType")
    public CurrencyRecyclerAdapter(Context mContext, ArrayList<CurrencyWithRate> listOfCurrency, String baseCurrency ) {
        this.listOfCurrency = listOfCurrency;
        this.mContext = mContext;
        this.baseCurrency = baseCurrency;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_of_currency, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.codeText.setText(listOfCurrency.get(position).getCode());
        holder.rateText.setText(Double.toString(listOfCurrency.get(position).getRate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, listOfCurrency.get(position).getCode() + " : " + listOfCurrency.get(position).getRate(), Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(mContext, SelectCurrencyActivity.class);
                intent.putExtra("currency_code_base", baseCurrency);
                intent.putExtra("currency_code_target", listOfCurrency.get(position).getCode());
                intent.putExtra("currency_rate_target", listOfCurrency.get(position).getRate());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfCurrency.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView codeText;
        TextView rateText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codeText = itemView.findViewById(R.id.code);
            rateText = itemView.findViewById(R.id.rate);


        }
    }


}
