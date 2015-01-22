package kr.co.saweb.enhance.android.ui.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EncodingUtils;

import java.util.Map;

import kr.co.saweb.enhance.android.util.common.HttpUtils;

/**
 * Created by OKS on 2015-01-20.
 */
public class PostAbleWebview extends WebView {
    public PostAbleWebview(Context context) {
        super(context);

        init();
    }

    public PostAbleWebview(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public void init() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSavePassword(false);
        getSettings().setAppCacheEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    public void post(String url, Map param) {
        String parameter = StringUtils.replace(HttpUtils.mapConvertToGetParameter(param), "?", "");
        postUrl(url, EncodingUtils.getBytes(parameter, "BASE64"));
    }
}
