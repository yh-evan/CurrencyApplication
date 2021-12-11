package yh.evanz.currencyapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import yh.evanz.currencyapplication.Model.Exchanges;

@Dao
public interface ExchangeDao {

    @Insert
    void insert(Exchanges exchanges);

    @Delete
    void delete(Exchanges exchanges);

    @Query("Select * from Exchanges")
    List<Exchanges> getAllExchanges();

    @Query("Select * from Exchanges where baseCode = :baseCode")
    List<Exchanges> getExchangesByBase(String baseCode);
}
