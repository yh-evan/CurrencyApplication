package yh.evanz.currencyapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import yh.evanz.currencyapplication.Model.Exchanges;

public class SelectCurrencyActivity extends AppCompatActivity {
    TextView base_code;
    TextView target_code;
    TextInputEditText base_amount;
    TextInputEditText target_amount;
    String currency_code_base, currency_code_target, editableText_base_amount, editableText_target_amount;
    Double currency_rate_target;
    Button calculateBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_currency);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getIncomingIntent();
        base_code = (TextView) findViewById(R.id.current_currency);
        target_code = (TextView) findViewById(R.id.target_currency);
        base_amount = (TextInputEditText) findViewById(R.id.textInput_current_currency);
        target_amount = (TextInputEditText) findViewById(R.id.textInput_target_currency);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);

        base_code.setText(currency_code_base);
        target_code.setText(currency_code_target);
        base_amount.setText("1");
        target_amount.setText(currency_rate_target.toString());



    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("currency_code_base")
                && getIntent().hasExtra("currency_code_target")
                && getIntent().hasExtra("currency_rate_target")) {
            currency_code_base = getIntent().getStringExtra("currency_code_base");
            currency_code_target = getIntent().getStringExtra("currency_code_target");
            currency_rate_target = getIntent().getDoubleExtra("currency_rate_target", 0);

        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void calculateClicked(View view) {
        editableText_base_amount = String.valueOf(base_amount.getEditableText());
        editableText_target_amount = String.valueOf(target_amount.getEditableText());
        if (editableText_base_amount.equals("")){
            double sum = Double.parseDouble(editableText_target_amount) / currency_rate_target;
            base_amount.setText(String.format("%.5f", sum));
        } else {
            double sum = Double.parseDouble(editableText_base_amount) * currency_rate_target;
            target_amount.setText(String.format("%.5f", sum));
        }
    }

    public void saveClicked(View view) {
        if (editableText_target_amount == null || editableText_target_amount.equals("") || editableText_base_amount == null || editableText_base_amount.equals("")){
            calculateDialog(this);
        } else {
            Exchanges newExchange = new Exchanges(currency_code_base, Double.parseDouble(editableText_base_amount), currency_code_target,currency_rate_target,  Double.parseDouble(editableText_target_amount));
            ExchangeDatabaseClient.insertNewExchanges(newExchange);
            Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public void calculateDialog(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Please calculate first");
        alertDialog.setNegativeButton("OK", null);
        alertDialog.show();

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
