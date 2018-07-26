package peaner.platform.sdk.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: lilongzhou
 * @Description: 转map的工具类
 * @Date: Created in 18-7-2 下午6:19
 **/
public class Bean2MapUtils {

    private static ObjectMapper objectMapper;

    public static ObjectMapper build() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static <T> Map<String, Object> buildMap(T t) {
        Map<String, Object> map = build().convertValue(t, new TypeReference<Map<String, Object>>() {
        });
        return map.entrySet().stream()
                .filter(stringObjectEntry -> stringObjectEntry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public static <T> Map<String, String> buildStringMap(T t) {
        Map<String, String> stringMap = build().convertValue(t, new TypeReference<Map<String, Object>>() {
        });
        return stringMap.entrySet().stream()
                .filter(stringEntry -> stringEntry.getValue() != null &&
                        !stringEntry.getValue().equals("null"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
