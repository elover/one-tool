package com.github.elover;

import java.util.Objects;
import java.util.function.Function;

/**
 * 取值时为空时添加默认值
 *
 * @author nanwei
 */
public class AttrTools {

    private AttrTools() {

    }

    /**
     * @param t
     * @param defaultVal
     * @param <T>
     * @return
     */
    public static <T> T getWithDefault(T t, T defaultVal) {
        if (Objects.nonNull(t)) {
            return t;
        }
        return defaultVal;
    }

    /**
     * @param t
     * @param keyExtractor
     * @param defaultVal
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> V getWithDefault(T t, Function<T, V> keyExtractor, V defaultVal) {
        V v = keyExtractor.apply(t);
        if (Objects.nonNull(v)) {
            return v;
        }
        return defaultVal;
    }
}
