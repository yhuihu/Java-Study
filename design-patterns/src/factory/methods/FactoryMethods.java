package factory.methods;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see factory.methods
 **/
public class FactoryMethods {
    public static MyPhone getPone(String type) {
        if (type.equals("iphone")) {
            return new IPhone();
        } else {
            return new OnePlus();
        }
    }

    public static void main(String[] args) {
        MyPhone myPhone = getPone("iphone");
        myPhone.phoneName();
    }
}
