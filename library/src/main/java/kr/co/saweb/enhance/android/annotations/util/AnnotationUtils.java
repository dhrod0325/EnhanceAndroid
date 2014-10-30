package kr.co.saweb.enhance.android.annotations.util;

import java.lang.reflect.ParameterizedType;

/**
 * Created by OKS on 2014-10-30.
 */
public class AnnotationUtils {
    public static Class getAnnotationClass(Class clazz) throws ClassNotFoundException {
        return Class.forName(getGenericName(clazz) + "_");
    }

    public static String getGenericName(Class clazz) {
        return getGenericName(clazz, 0);
    }

    public static String getGenericName(Class clazz, int idx) {
        ParameterizedType p = (ParameterizedType) clazz.getSuperclass().getGenericSuperclass();
        String name = p.getActualTypeArguments()[idx].toString().split(" ")[1];

        return name;
    }
}
