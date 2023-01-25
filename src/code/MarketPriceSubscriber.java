package code;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MarketPriceSubscriber {

    private final PriceRESTEndpoint priceRESTEndpoint;
    private final Map<String, Price> prices = new ConcurrentHashMap<>();

    public MarketPriceSubscriber(PriceRESTEndpoint priceRESTEndpoint) {
        this.priceRESTEndpoint = priceRESTEndpoint;
    }

    public void onMessage(String message) {
        String[] price = message.split(",");
        if(price.length != 5) {
            System.out.println("Invalid price data. Expected format is: id, instrument name, bid, ask, timestamp");
            return;
        }
        try {
            int id = Integer.parseInt(price[0]);
            String instrument = price[1];
            double bid = Double.parseDouble(price[2]) - (Double.parseDouble(price[2]) * 0.1);
            double ask = Double.parseDouble(price[3]) + (Double.parseDouble(price[3]) * 0.1);
            String timestamp = price[4];
            Price newPrice = new Price(id, instrument, bid, ask, timestamp);
            prices.put(instrument, newPrice);
            priceRESTEndpoint.publishPrice(newPrice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price data. id, bid, and ask must be numbers.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing the price data.");
            e.printStackTrace();
        }
    }
    
    public Price getPrice(String instrument) {
        return prices.get(instrument);
    }
}

