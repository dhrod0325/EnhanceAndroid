package kr.co.saweb.enhance.android.helper.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by OKS on 2014-10-30.
 */
public class MyReceiver extends BaseReceiver {
    public MyReceiver(Activity activity) {
        super(activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Zxcvzxcv", "test");
    }
}