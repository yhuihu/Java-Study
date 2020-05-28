package proxy.cglib;

import proxy.staticproxy.BuyHouse;
import proxy.staticproxy.BuyHouseImpl;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see proxy.cglib
 **/
public class CglibTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        CglibProxy cglibProxy = new CglibProxy();
        BuyHouseImpl buyHouse1 = (BuyHouseImpl) cglibProxy.getInstance(buyHouse);
        buyHouse1.buyHouse();
    }
}
