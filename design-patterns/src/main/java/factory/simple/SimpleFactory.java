package factory.simple;


/**
 * @author Tiger
 * @date 2020-03-08
 * @see factory.simple
 **/
public class SimpleFactory {
    public static IProduct getFactory(String name) throws IllegalAccessException {
        if (name.equals("A")){
            return new AProduct();
        }else if (name.equals("B")){
            return new BProduct();
        }else{
            throw new IllegalAccessException();
        }
    }
}
