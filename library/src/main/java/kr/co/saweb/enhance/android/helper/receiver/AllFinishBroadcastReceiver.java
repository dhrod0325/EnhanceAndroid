package kr.co.saweb.enhance.android.helper.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by OKS on 2014-10-30.
 */
public class AllFinishBroadcastReceiver extends BaseReceiver {
    public AllFinishBroadcastReceiver(Activity activity) {
        super(activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.finish();
    }
}
