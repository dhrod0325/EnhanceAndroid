package kr.co.saweb.enhance.android.util.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by OKS on 2014-10-02.
 */
public class IOUtils {
    public static String toString(InputStream is) {
        try {
            String str;

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((str = br.readLine()) != null) {
                sb.append(str);
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
