package code;


public class Price {
    public int id;
    public String instrument;
    public double bid;
    public double ask;
    public String timestamp;

    public Price(int id, String instrument, double bid, double ask, String timestamp) {
        this.id = id;
        this.instrument = instrument;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", instrument='" + instrument + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

