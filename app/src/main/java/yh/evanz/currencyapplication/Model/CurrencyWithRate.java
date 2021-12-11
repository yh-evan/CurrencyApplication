package yh.evanz.currencyapplication.Model;



public class CurrencyWithRate  {
    String code;
    double rate;

    public CurrencyWithRate(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
