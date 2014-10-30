package kr.co.saweb.enhance.android.util.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OKS on 2014-10-06.
 */
public class ObjectUtil {
    public static Map convertObjectToMap(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            Map resultMap = new HashMap();
            for (int i = 0; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                resultMap.put(fields[i].getName(), fields[i].get(obj));
            }
            return resultMap;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
