package kr.co.saweb.enhance.android.ui.activity;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.core.BusProvider;

public class BaseFragmentActivity extends FragmentActivity {
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
