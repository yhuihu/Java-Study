package adapter.objectadapter;

import adapter.tools.Adaptee;
import adapter.tools.Target;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see adapter.objectadapter
 **/
public class Adapter implements Target {
    // 适配者是对象适配器的一个属性
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
