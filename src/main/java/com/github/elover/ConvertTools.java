package com.github.elover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author nanwei
 */
public class ConvertTools {

    /**
     * 工具类 禁止new
     */
    private ConvertTools() {
    }

    /**
     * 将list转换成map<K,List<item>>
     *
     * @param list
     * @param keyExtractor
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, List<V>> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (Objects.isNull(list)) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> map = new HashMap<>(16);
        list.forEach(item -> {
            K key = keyExtractor.apply(item);
            if (Objects.isNull(key)) {
                return;
            }
            List<V> result = map.get(key);
            if (Objects.isNull(result)) {
                result = new ArrayList<>();
                result.add(item);
                map.put(key, result);
            } else {
                result.add(item);
            }
        });
        return map;
    }

    /**
     * list 转换成map<key,Set(T)> ,key和val可以自定义取值
     *
     * @param list
     * @param keyExtractor
     * @param valExtractor
     * @param <K>
     * @param <V>
     * @param <T>
     * @return
     */
    public static <K, V, T> Map<K, Set<T>> listToMap(List<V> list, Function<V, K> keyExtractor,
            Function<V, T> valExtractor) {
        if (Objects.isNull(list)) {
            return Collections.emptyMap();
        }
        Map<K, Set<T>> map = new HashMap<>(16);
        list.forEach(item -> {
            K key = keyExtractor.apply(item);
            if (Objects.isNull(key)) {
                return;
            }
            Set<T> result = map.get(key);
            if (Objects.isNull(result)) {
                result = new HashSet<T>();
                T val = valExtractor.apply(item);
                result.add(val);
                map.put(key, result);
            } else {
                T val = valExtractor.apply(item);
                result.add(val);
            }
        });
        return map;
    }

    /**
     * map 中的value 中某一项转化成set
     *
     * @param map
     * @param keyExtractor
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Set<K> mapToSet(Map<K, V> map, Function<V, K> keyExtractor) {
        if (Objects.isNull(map)) {
            return Collections.emptySet();
        }
        return map.values().stream().map(keyExtractor).filter(k -> !Objects.isNull(k))
                .collect(Collectors.toSet());
    }

    /**
     * 一个list转化成另一个list
     *
     * @param list
     * @param keyExtractor
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<T> convertList(List<V> list, Function<V, T> keyExtractor) {
        if (Objects.isNull(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(keyExtractor).collect(Collectors.toList());
    }

}
