package kr.co.saweb.enhance.android.util.version;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URLConnection;

/**
 * Created by OKS on 2014-12-17.
 */
public class VersionCheckerAsyncTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        return null;
    }
}
