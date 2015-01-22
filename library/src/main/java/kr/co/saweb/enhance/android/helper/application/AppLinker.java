package kr.co.saweb.enhance.android.helper.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import kr.co.saweb.enhance.android.helper.packmngr.PackageManager;

/**
 * Created by OKS on 2014-12-02.
 */
public class AppLinker {
    public static void openApplicationInGoogleStore(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        context.startActivity(intent);
    }
}