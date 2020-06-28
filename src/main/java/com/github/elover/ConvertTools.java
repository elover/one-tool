package com.github.elover;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author nanwei
 */
public class ConvertTools {

    /**
     * 工具类 禁止new
     */
    private ConvertTools() {
    }

    public static <K, V> Map<K, List<V>> listToMap(List<V> list) {
        return null;
    }

    public static <K, V> List<K> mapToList(Map<K, V> map) {
        return null;
    }

    public static <T, V> List<T> convertList(List<V> list, Function<T, V> function) {
        return null;
    }

}
