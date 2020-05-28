package proxy.jdkproxy;

import proxy.staticproxy.BuyHouse;
import proxy.staticproxy.BuyHouseImpl;

import java.lang.reflect.Proxy;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see proxy.jdkproxy
 **/
public class ProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(),
                new Class[]{BuyHouse.class}, new ProxyMethod(buyHouse));
        proxyBuyHouse.buyHouse();
    }
}
