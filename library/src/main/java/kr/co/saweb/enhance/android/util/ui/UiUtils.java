package kr.co.saweb.enhance.android.util.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.net.URLEncoder;

/**
 * Created by OKS on 2014-10-10.
 */
public class UiUtils {
    public static int dpToPx(int dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static int pxToDp(int px, Context context) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static Display getScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        return display;
    }

    public static void keyboardHide(View view, Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void keyboardShow(View view, Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean isNetworkEnable(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static String convertToHtml(String str, String style) {
        try {
            String html = str + "";
            String content =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                            "<html><head>" +
                            "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />" +
                            "<head><style>" + style + "</style><body>";
            content += html + "</body></html>";

            return URLEncoder.encode(content, "UTF-8").replaceAll("\\+", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";
    }
}
