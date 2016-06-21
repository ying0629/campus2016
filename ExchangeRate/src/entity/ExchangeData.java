package entity;

/**
 * Created by songshaoying on 16/6/8.
 */
public class ExchangeData {
    private String date;
    private ExchangeDataKind kind;
    private float modelPrice;
    private float rate;

    public ExchangeData(String date,ExchangeDataKind kind , int modelPrice ,float rate){
        this.setDate(date);
        this.setKind(kind);
        this.setModelPrice(modelPrice);
        this.setRate(rate);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ExchangeDataKind getKind() {
        return kind;
    }

    public void setKind(ExchangeDataKind kind) {
        this.kind = kind;
    }

    public float getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(float modelPrice) {
        this.modelPrice = modelPrice;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }


    public static void main(String[] args){
        ExchangeData exchangeData = new ExchangeData("",ExchangeDataKind.DOLLAR,1,0f);
        System.out.println(exchangeData.getKind());
    }
}
