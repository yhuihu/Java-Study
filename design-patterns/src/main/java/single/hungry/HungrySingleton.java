package single.hungry;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.hungry
 **/
public class HungrySingleton {
    private String name;

    private HungrySingleton() {
    }

    private static final HungrySingleton SINGLE = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return SINGLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        hungrySingleton.setName("hello");
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        hungrySingleton1.setName("world");
        System.out.println("hungrySingleton:" + hungrySingleton.getName());
        System.out.println("hungrySingleton1:" + hungrySingleton1.getName());
    }
}
