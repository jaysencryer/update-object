package com.jaysencryer.objectdifference.util;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectComparisonUtil {
    public static <T> Map<String, Object> findDifference(T object1, T object2) throws IllegalAccessException {
        if (object1 == object2) return Map.of();
        Field[] fields = object1.getClass().getDeclaredFields();
        Map<String, Object> differences = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object originalValue = field.get(object1);
            Object updatedValue = field.get(object2);
            if ((originalValue != null && !originalValue.equals(updatedValue))
                    || (originalValue == null && updatedValue != null)) {
                differences.put(field.getName(), updatedValue);
            }
        }
        return differences;
    }
}
