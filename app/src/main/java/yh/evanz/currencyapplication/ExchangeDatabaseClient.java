package yh.evanz.currencyapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import yh.evanz.currencyapplication.Model.Exchanges;

public class ExchangeDatabaseClient {

    static ExchangeDatabase dbClient;

    public interface DatabaseActionListener {
        public void databaseReturnWithList(List<Exchanges> exchangeList);
    }

    static DatabaseActionListener listener;

    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    static Handler handler = new Handler(Looper.getMainLooper());

    public static void ExchangeDatabaseClient(Context context){
        dbClient = Room.databaseBuilder(context,
                ExchangeDatabase.class, "database-exchanges").build();
    }

    public static ExchangeDatabase getDbClient(Context context){
        if (dbClient == null){
            ExchangeDatabaseClient(context);
        }

        return dbClient;
    }

    public static void insertNewExchanges(Exchanges newExchanges){
         databaseExecutor.execute(new Runnable() {
             @Override
             public void run() {
                 dbClient.getExchangeDao().insert(newExchanges);
             }
         });
    }

    public static void getAllExchanges(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Exchanges> listFromDB = dbClient.getExchangeDao().getAllExchanges();
                //return from back thread
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //return sth. to main thread
                        listener.databaseReturnWithList((List<Exchanges>) listFromDB);
                    }
                });
            }
        });
    }
}
