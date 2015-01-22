package kr.co.saweb.enhance.android.util.ui;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by OKS on 2015-01-20.
 */
public class IntentUtils {
    public static Map<String, Object> extrasToMap(Activity activity) {
        Map<String, Object> result = new HashMap<String, Object>();
        Bundle bundle = activity.getIntent().getExtras();

        if (bundle != null) {
            Iterator<String> keys = bundle.keySet().iterator();

            while (keys.hasNext()) {
                String key = keys.next();
                Object value = bundle.get(key);

                result.put(key, value);
            }
        }

        return result;
    }
}
