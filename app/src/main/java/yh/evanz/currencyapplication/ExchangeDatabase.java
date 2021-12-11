package yh.evanz.currencyapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import yh.evanz.currencyapplication.Model.Exchanges;

@Database(entities = {Exchanges.class}, version = 1)
public abstract class ExchangeDatabase extends RoomDatabase {
    public abstract ExchangeDao getExchangeDao();
}
