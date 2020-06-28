package com.github.elover;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nanwei
 */
public class BeanTools {

    private BeanTools() {

    }

    /**
     * 复制bean（浅拷贝）
     *
     * @param source
     * @param target
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, T target, String... ignoreProperties) {
        if (source != null) {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        }
        return null;
    }

    /**
     * 复制bean list（浅拷贝）
     *
     * @param objects
     * @param class1
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> objects, Class<T> class1, String... ignoreProperties) {
        if (objects == null || objects.isEmpty()) {
            return new ArrayList<T>();
        }
        List<T> res = new ArrayList<T>();
        for (Object s : objects) {
            T t;
            try {
                t = class1.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            t = copy(s, t, ignoreProperties);
            res.add(t);
        }
        return res;
    }

    /**
     * 复制bean（深拷贝）,性能不如copy，多层级结构和性能要求不高场景使用
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T copyDeep(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        } else {
            String sourceString = JSON.toJSONString(source);
            return JSON.parseObject(sourceString, targetClass);
        }
    }

    /**
     * 复制bean list（深拷贝）
     *
     * @param objects
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyListDeep(List<?> objects, Class<T> targetClass) {
        if (objects == null || objects.isEmpty()) {
            return new ArrayList<T>();
        }
        List<T> res = JSON.parseArray(JSON.toJSONString(objects), targetClass);
        if (null == res) {
            return new ArrayList<T>();
        }
        return res;
    }

}
