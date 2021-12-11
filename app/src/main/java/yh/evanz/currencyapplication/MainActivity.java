package yh.evanz.currencyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

import yh.evanz.currencyapplication.Adapter.CurrencyRecyclerAdapter;
import yh.evanz.currencyapplication.Model.CurrencyWithRate;

public class MainActivity extends AppCompatActivity implements NetWorkingService.NetWorkingListener {
    NetWorkingService netWorkingManager;
    CurrencyRecyclerAdapter adapter;
    ArrayList<CurrencyWithRate> listOfCurrency = new ArrayList<>();
    RecyclerView list;
    JsonService jsonService;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String currentCurrency = "USD";
    ExchangeDatabaseClient databaseClient;
    ExchangeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        netWorkingManager = ((myApp)getApplication()).getNetWorkingService();
        netWorkingManager.listener = this;
        jsonService = ((myApp)getApplication()).getJsonService();
        autoCompleteTextView = findViewById(R.id.dropdown_items);

        list = (RecyclerView) findViewById(R.id.currencyList);
        netWorkingManager.getCurrencyRate(currentCurrency);

        db = ExchangeDatabaseClient.getDbClient(this);
        databaseClient = (((myApp) getApplication()).getDatabaseManager());
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void dataListener(String jsonString) {
        ArrayList<String> codeList = new ArrayList<>();
        System.out.println(jsonString);
        listOfCurrency = jsonService.getCurrencyFromJSON(jsonString);
        for (CurrencyWithRate c: listOfCurrency) {
            codeList.add(c.getCode());
        }

        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, codeList);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
                netWorkingManager.getCurrencyRate(item);
                currentCurrency = item;
            }
        });

        adapter = new CurrencyRecyclerAdapter(this, listOfCurrency, currentCurrency);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.currency_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.history_activity:
                openHistoryActivity();
                break;
        }
        return true;
    }

    private void openHistoryActivity() {
        Intent toHistoryActivity = new Intent(this, HistoryActivity.class);

        startActivity(toHistoryActivity);
    }
}