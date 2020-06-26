package com.github.elover;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nanwei
 */
public class BeanTools {

    /**
     * Note:字段数据类型、名称需一致才可以完成拷贝，如Integer、Long之间无法完成拷贝
     *
     * @param source           源对象
     * @param target           目标对象
     * @param ignoreProperties 需要忽略的属性名
     * @return T
     */
    public static <T> T copyBean(Object source, T target, String... ignoreProperties) {
        if (source != null) {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        }
        return null;
    }

    public static <T> List<T> copyBeanList(List<?> objects, Class<T> class1, String... ignoreProperties) {
        try {
            if (objects == null || objects.isEmpty()) {
                return new ArrayList<T>();
            }
            List<T> res = new ArrayList<T>();
            for (Object s : objects) {
                T t = class1.newInstance();
                t = copyBean(s, t, ignoreProperties);
                res.add(t);
            }
            return res;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T copyBeanDeep(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        } else {
            String sourceString = JSON.toJSONString(source);
            return JSON.parseObject(sourceString, targetClass);
        }
    }

    public static <T> List<T> copyBeanListDeep(List<?> objects, Class<T> targetClass) {
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
