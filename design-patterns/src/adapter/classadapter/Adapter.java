package adapter.classadapter;

import adapter.tools.Adaptee;
import adapter.tools.Target;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see adapter.classadapter
 **/
public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        super.adapteeRequest();
    }
}
