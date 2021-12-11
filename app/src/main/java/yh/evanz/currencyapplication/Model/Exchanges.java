package yh.evanz.currencyapplication.Model;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exchanges {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String baseCode;
    public double baseAmount;

    public String targetCode;
    public double targetRate;
    public double targetAmount;

    public Exchanges(String baseCode, double baseAmount, String targetCode, double targetRate, double targetAmount) {
        this.baseCode = baseCode;
        this.baseAmount = baseAmount;
        this.targetCode = targetCode;
        this.targetRate = targetRate;
        this.targetAmount = targetAmount;
    }


}
