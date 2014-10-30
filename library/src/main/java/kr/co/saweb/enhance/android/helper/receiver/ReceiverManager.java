package kr.co.saweb.enhance.android.helper.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OKS on 2014-10-30.
 */
public class ReceiverManager {
    private static ReceiverManager instance = new ReceiverManager();

    private List<BaseReceiver> receiverList = new ArrayList<BaseReceiver>();

    public void addReceiver(BaseReceiver receiver) {
        receiverList.add(receiver);
    }

    public static ReceiverManager getInstance() {
        return instance;
    }

    public void register(Activity activity) {
        for (BaseReceiver receiver : receiverList) {
            activity.registerReceiver(receiver, receiver.getIntentFilter());
        }
    }

    public void unRegister(Activity activity) {
        for (BaseReceiver receiver : receiverList) {
            activity.unregisterReceiver(receiver);
        }
    }

    public void publish(Activity activity, Class clazz) {
        Intent intent = new Intent(clazz.getName());
        activity.sendBroadcast(intent);
    }

    public void registerReceiver(BaseReceiver receiver) {
        receiverList.add(receiver);
    }
}