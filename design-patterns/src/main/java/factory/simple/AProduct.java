package factory.simple;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see factory.simple
 **/
public class AProduct extends IProduct{
    @Override
    String goReady() {
        return "A";
    }
}
