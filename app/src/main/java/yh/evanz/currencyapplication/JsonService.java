package yh.evanz.currencyapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import yh.evanz.currencyapplication.Model.CurrencyWithRate;

public class JsonService {
    public ArrayList<CurrencyWithRate> getCurrencyFromJSON(String json){
        ArrayList<CurrencyWithRate> listOfCurrency = new ArrayList<>();
        try {
            JSONObject json_currency = new JSONObject(json).getJSONObject("data");
            for (Iterator<String> it = json_currency.keys(); it.hasNext(); ) {
                try{
                    String code = it.next();
                    Object rate = json_currency.get(code);
                    listOfCurrency.add(new CurrencyWithRate(code, (Double) rate));
                }catch (ClassCastException e){
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfCurrency;
    }
}
