package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhhu
 * @date 2020/11/6
 * @description
 */
public class JsonTest {
    public static void main(String[] args) {
        String value=null;
//        System.out.println(JsonUtils.fromJson(value, List.class));
        List<String> list=new ArrayList<>();
        if (value!=null) {
            list.add(value);
        }
        System.out.println(list);
    }
}
