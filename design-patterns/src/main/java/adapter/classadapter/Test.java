package adapter.classadapter;

import adapter.tools.ConcreteTarget;
import adapter.tools.Target;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see adapter.classadapter
 **/
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
