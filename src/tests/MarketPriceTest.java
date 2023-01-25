package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.MarketPriceSubscriber;
import code.Price;
import code.PriceRESTEndpoint;

public class MarketPriceTest {

    @Test
    public void testOnMessage() {
    	
    	PriceRESTEndpoint endpoint = new PriceRESTEndpoint();
        MarketPriceSubscriber subscriber = new MarketPriceSubscriber(endpoint);
        subscriber.onMessage("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");

        Price price = subscriber.getPrice("EUR/USD");
        assertEquals(106, price.id);
        assertEquals("EUR/USD", price.instrument);
        assertEquals(1.09, price.bid, 0.001);
        assertEquals(1.21, price.ask, 0.001);
        assertEquals("01-06-2020 12:01:01:001", price.timestamp);
    }

    @Test
    public void testInvalidPriceData() {
        PriceRESTEndpoint endpoint = new PriceRESTEndpoint();
        MarketPriceSubscriber subscriber = new MarketPriceSubscriber(endpoint);
        subscriber.onMessage("106, EUR/USD, 1.1000,1.2000");

        Price price = subscriber.getPrice("EUR/USD");
        assertEquals(null, price);
    }

    @Test
    public void testInvalidPriceValues() {
        PriceRESTEndpoint endpoint = new PriceRESTEndpoint();
        MarketPriceSubscriber subscriber = new MarketPriceSubscriber(endpoint);
        subscriber.onMessage("106, EUR/USD, 1.1000,not_a_number,01-06-2020 12:01:01:001");

        Price price = subscriber.getPrice("EUR/USD");
        assertEquals(null, price);
    }
}
