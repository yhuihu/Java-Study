package factory.methods;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see factory.methods
 **/
public class IPhone implements MyPhone{
    @Override
    public String phoneName() {
        return "IPhone";
    }
}
