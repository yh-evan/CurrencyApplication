package yh.evanz.currencyapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import yh.evanz.currencyapplication.Model.Exchanges;
import yh.evanz.currencyapplication.R;

public class HistoryListAdapter extends BaseAdapter {

    ArrayList<Exchanges> listOfExchanges;
    Context history_activity_context;

    public HistoryListAdapter(ArrayList<Exchanges> listOfExchanges, Context history_activity_context) {
        this.listOfExchanges = listOfExchanges;
        this.history_activity_context = history_activity_context;
    }

    @Override
    public int getCount() {
        return listOfExchanges.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView base_code;
        TextView target_code;
        TextView target_rate;
        TextView sum;
        view = LayoutInflater.from(history_activity_context).inflate(R.layout.history_item, null);
        base_code = view.findViewById(R.id.history_base_code);
        target_code = view.findViewById(R.id.history_target_code);
        target_rate = view.findViewById(R.id.history_target_rate);
        sum = view.findViewById(R.id.history_sum);

        base_code.setText(listOfExchanges.get(i).baseCode);
        target_code.setText(listOfExchanges.get(i).targetCode);
        target_rate.setText(Double.toString(listOfExchanges.get(i).targetRate));


        sum.setText(Double.toString(listOfExchanges.get(i).baseAmount)+" "+listOfExchanges.get(i).baseCode+" = "
                + Double.toString(listOfExchanges.get(i).baseAmount * listOfExchanges.get(i).targetRate)
                + " " + listOfExchanges.get(i).targetCode);
        return view;
    }
}
