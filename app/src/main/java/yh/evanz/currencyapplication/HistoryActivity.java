package yh.evanz.currencyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


import yh.evanz.currencyapplication.Adapter.HistoryListAdapter;
import yh.evanz.currencyapplication.Model.Exchanges;

public class HistoryActivity extends AppCompatActivity implements ExchangeDatabaseClient.DatabaseActionListener{

    ListView listOfHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listOfHistory = findViewById(R.id.history_list);

        ExchangeDatabaseClient.listener = this;
        ExchangeDatabaseClient.getAllExchanges();

    }

    @Override
    public void databaseReturnWithList(List<Exchanges> exchangeList) {
        ArrayList<Exchanges> alExchanges = new ArrayList<Exchanges>(exchangeList);
        HistoryListAdapter listAdapter = new HistoryListAdapter(alExchanges, this);
        listOfHistory.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.main_activity:
                openMainActivity();
                break;
        }
        return true;
    }

    private void openMainActivity() {
        Intent toMainActivity = new Intent(this, MainActivity.class);

        startActivity(toMainActivity);
    }
}
