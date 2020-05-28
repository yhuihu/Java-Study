package proxy.staticproxy;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see proxy.staticproxy
 **/
public class BuyHouseProxy implements BuyHouse {
    private BuyHouse buyHouse;

    public BuyHouseProxy(final BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前准备");
        buyHouse.buyHouse();
        System.out.println("买完了");
    }
}
