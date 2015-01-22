package kr.co.saweb.enhance.android.helper.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kr.co.saweb.enhance.android.util.common.FileUtil;
import kr.co.saweb.enhance.android.util.file.SDCard;
import kr.co.saweb.enhance.android.util.ui.UiUtils;

/**
 * Created by OKS on 2014-11-28.
 */
public class UpdateApp extends AsyncTask<String, Void, Void> {
    private Context context;

    public UpdateApp(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(arg0[0]);

            InputStream is = client.execute(get).getEntity().getContent();

            String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/inmoa/update/";
            String FILE_NAME = "update.apk";

            File file = new File(PATH);
            file.mkdirs();

            File outputFile = new File(file, FILE_NAME);

            if (outputFile.exists()) {
                outputFile.delete();
            }

            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }

            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(PATH + FILE_NAME)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("UpdateAPP", "Update error! " + e.getMessage());
        }
        return null;
    }
}