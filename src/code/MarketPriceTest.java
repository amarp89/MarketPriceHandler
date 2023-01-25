package code;

public class MarketPriceTest {
	public static void main(String[] args) {
		PriceRESTEndpoint endpoint = new PriceRESTEndpoint();
		MarketPriceSubscriber subscriber = new MarketPriceSubscriber(endpoint);
		subscriber.onMessage("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
	}
}