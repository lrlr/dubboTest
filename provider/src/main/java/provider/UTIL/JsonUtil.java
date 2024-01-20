package provider.UTIL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * json工具类
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将json字符串转换为map对象
     * @param str
     * @return
     */
    public static Map<String,Object> json2map(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(HashMap.class,
                String.class, Object.class);
        Map<String, Object> map = null;
        try {
            map = OBJECT_MAPPER.readValue(str, javaType);
        } catch (IOException ignore) {
            throw new IllegalArgumentException("str=" + str, ignore);
        }

        if (map == null) {
            return Collections.EMPTY_MAP;
        }
        return map;
    }

    /**
     * 将json字符串转换为map对象,kv都为string
     * @param str
     * @return
     */
    public static Map<String,String> json2mapString(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(HashMap.class,
                String.class, String.class);
        Map<String, String> map = null;
        try {
            map = OBJECT_MAPPER.readValue(str, javaType);
        } catch (IOException ignore) {
            throw new IllegalArgumentException("str=" + str, ignore);
        }

        if (map == null) {
            return Collections.EMPTY_MAP;
        }
        return map;
    }

    /**
     * 序列化一个对象为json字符串
     * @param obj 对象
     * @return
     */
    public static String string(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException ignore) {
            throw new IllegalArgumentException("obj=" + obj);
        }
    }

    /**
     * 反序列化json字符串为一个对象
     * @param json json字符串
     * @param clazz 对象的class信息
     * @param <T> 对象类型信息
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("json=" + json, e);
        }
    }

    /**
     * 反序列化map为一个对象
     * @param map
     * @param clazz 对象的class信息
     * @param <T> 对象类型信息
     * @return
     */
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }
    /**
     * 反序列化json字符串为一个对象
     * @param json json字符串
     * @param clazz 对象的class信息
     * @param <T> 对象类型信息
     * @return
     */
    public static <T> T formJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Failed to readValue.",e);
            throw new IllegalArgumentException("json=" + json, e);
        }
    }
    public static <T> T formJson(String str, TypeReference<T> typeReference) throws IOException {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        return (T) (typeReference.getType().equals(String.class) ? str
                : OBJECT_MAPPER.readValue(str, typeReference));
    }
}
