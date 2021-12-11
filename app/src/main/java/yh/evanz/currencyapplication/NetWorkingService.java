package yh.evanz.currencyapplication;


import android.os.Looper;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetWorkingService {
    private final String baseCurrencyUrl = "https://freecurrencyapi.net/api/v2/latest?apikey=21448ba0-51f8-11ec-93ff-91ec87f78139&base_currency=";
    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetWorkingListener{
        void dataListener(String jsonString);
    }
    public NetWorkingListener listener;

    public void getCurrencyRate(String code){
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                StringBuilder jsonData = new StringBuilder();
                HttpURLConnection httpURLConnection = null;
                int inputStreamData = 0;
                try {
                    URL urlObj = new URL(baseCurrencyUrl+code);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    while ((inputStreamData = reader.read()) != -1){
                        char current = (char)inputStreamData;

                        jsonData.append(current);

                    }

                    String finalJsonData = jsonData.toString();
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.dataListener(finalJsonData);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
