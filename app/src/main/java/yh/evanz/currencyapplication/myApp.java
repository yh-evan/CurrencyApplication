package yh.evanz.currencyapplication;

import android.app.Application;

public class myApp extends Application {
    private NetWorkingService netWorkingService = new NetWorkingService();
    private JsonService jsonService = new JsonService();

    public JsonService getJsonService() {
        return jsonService;
    }

    public NetWorkingService getNetWorkingService() {
        return netWorkingService;
    }

    private ExchangeDatabaseClient exchangeDatabaseClient = new ExchangeDatabaseClient();
    public ExchangeDatabaseClient getDatabaseManager(){return exchangeDatabaseClient;};
}
