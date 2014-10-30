package kr.co.saweb.enhance.android.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.core.BusProvider;

/**
 * Created by macpro on 2014. 10. 18..
 */
public class BaseFragment extends Fragment {
    private List<Object> eventHandlerList = new ArrayList<Object>();

    protected BusProvider bus = BusProvider.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        onAddEventHandler(eventHandlerList);
        bus.registerAll(eventHandlerList);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unRegisterAll(eventHandlerList);
    }

    public void onAddEventHandler(List<Object> eventHandlerList) {
    }
}
