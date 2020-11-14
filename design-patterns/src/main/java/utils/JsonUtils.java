package utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author yhhu
 * @date 2020/11/6
 * @description
 */
public class JsonUtils {
    private static Gson gson;

    static {
        gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ssZ").serializeNulls()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .registerTypeAdapter(BigDecimal.class, new JsonSerializer<BigDecimal>() {
                    @Override
                    public JsonElement serialize(BigDecimal bigDecimal, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(bigDecimal.toString());
                    }
                })
                .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                    @Override
                    public JsonElement serialize(Double aDouble, Type type, JsonSerializationContext jsonSerializationContext) {
                        if (aDouble == aDouble.longValue()) {
                            return new JsonPrimitive(aDouble.longValue());
                        }
                        return new JsonPrimitive(aDouble);
                    }
                })
                .registerTypeAdapter(Integer.class, new JsonSerializer<Integer>() {
                    @Override
                    public JsonElement serialize(Integer integer, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(String.valueOf(integer));
                    }
                }).create();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String toJson(Object src) {
        try {
            return gson.toJson(src);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
