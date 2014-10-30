package kr.co.saweb.enhance.android.util.common;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by macpro on 14. 10. 9..
 */
public class HttpUtils {
    public static String mapConvertToGetParameter(Map params) {
        if (params == null)
            params = new HashMap();

        String req_url = "";
        Iterator<String> iter = params.keySet().iterator();

        int i = 0;

        while (iter.hasNext()) {
            String key = iter.next();
            String value = String.valueOf(params.get(key));

            if (value == null || "null".equals(value) || value.length() == 0)
                continue;

            if (i == 0) {
                req_url += "?" + key + "=" + value;
            } else {
                req_url += "&" + key + "=" + value;
            }

            i++;
        }

        return req_url;
    }

    public static List<NameValuePair> mapConvertToPostParameter(Map params) {
        List<NameValuePair> result = new ArrayList<NameValuePair>();

        if (params == null)
            params = new HashMap();

        for (String key : (Iterable<String>) params.keySet()) {
            String value = String.valueOf(params.get(key));

            if (value == null || "null".equals(value) || value.length() == 0)
                continue;

            result.add(new BasicNameValuePair(key, value));
        }

        return result;
    }
}
