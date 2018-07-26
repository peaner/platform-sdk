package peaner.platform.sdk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description: gson工具类
 * @Date: Created in 18-7-2 下午3:43
 **/
public class GsonUtils {

    private static Gson gson = null;
    private static Type type = new TypeToken<Map<String, String>>() {
    }.getType();

    public static Gson buildGson() {
        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            /*gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);*/
            return gb.create();
        }
        return gson;
    }

    /**
     * object to json
     * @param object 对象
     * @return 返回值
     */
    public static String toJson(Object object) {
        gson = buildGson();
        return gson.toJson(object);
    }

    /**
     * list to json
     * @param list list串
     * @return 返回值
     */
    public static String listToJson(List<Object> list) {
        gson = buildGson();
        return gson.toJson(list);
    }

    /**
     * map to json
     * @param map 参数
     * @return 返回值
     */
    public static String mapToJson(Map<String, String> map) {
        gson = buildGson();
        return gson.toJson(map);
    }

    /**
     * json to map
     * @param string 字符串
     * @return 返回值
     */
    public static Map<String, Object> jsonToMap(String string) {
        gson = buildGson();
        return gson.fromJson(string, type);
    }

}
