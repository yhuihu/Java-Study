package proxy.staticproxy;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see proxy.staticproxy
 **/
public class ProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buyHouse();
    }
}
