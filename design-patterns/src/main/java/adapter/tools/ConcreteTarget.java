package adapter.tools;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see adapter.tools
 **/
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("concreteTarget目标方法");
    }
}
