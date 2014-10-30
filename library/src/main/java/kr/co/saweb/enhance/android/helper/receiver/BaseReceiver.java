package kr.co.saweb.enhance.android.helper.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/**
 * Created by OKS on 2014-10-30.
 */
public abstract class BaseReceiver extends BroadcastReceiver {
    protected Activity activity;

    public BaseReceiver(Activity activity) {
        this.activity = activity;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getClass().getName());
        return intentFilter;
    }
}
