package code;

public class PriceRESTEndpoint {
    public void publishPrice(Price price) {
        // Send the price to the REST endpoint
        System.out.println("Price published to REST endpoint: " + price);
    }
}
