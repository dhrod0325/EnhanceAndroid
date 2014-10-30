package kr.co.saweb.enhance.android.ui.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.core.BusProvider;

/**
 * Created by OKS on 2014-10-30.
 */
public class BaseActivity extends Activity {
    private List<Object> eventHandlerList = new ArrayList<Object>();

    protected BusProvider bus = BusProvider.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        bus.unRegisterAll(eventHandlerList);
        onAddEventHandler(eventHandlerList);
        bus.registerAll(eventHandlerList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unRegisterAll(eventHandlerList);
    }

    protected void onAddEventHandler(List<Object> eventHandlerList) {
    }
}
