package kr.co.saweb.enhance.android.helper.packmngr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Map;

/**
 * Created by OKS on 2014-11-12.
 */
public class PackageManager {
    private Context context;

    public static PackageManager instance;

    private PackageManager(Context context) {
        this.context = context;
    }

    public final static String TYPE_PACKAGE = "PACKAGE";
    public final static String TYPE_ACTION_VIEW = "ACTION_VIEW";

    public static PackageManager getInstance(Context context) {
        if (instance == null) {
            instance = new PackageManager(context);
        }

        return instance;
    }

    public void startApplication(String packageName, Map data) {
        Intent i = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(i);
    }

    public void startApplication(String packageName) {
        startApplication(packageName, TYPE_PACKAGE);
    }

    public void startApplication(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(uri);

        context.startActivity(intent);
    }

    public void startApplication(String packageName, String startType) {
        if (TYPE_PACKAGE.equals(startType)) {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        }
    }

    public boolean isInstalledApp(String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, android.content.pm.PackageManager.GET_META_DATA);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
