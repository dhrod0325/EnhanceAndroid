package kr.co.saweb.enhance.android.annotations.core;

import com.squareup.otto.Bus;

import java.util.List;

public class BusProvider extends Bus {
    private static BusProvider instance = new BusProvider();

    public static BusProvider getInstance() {
        return instance;
    }

    public void registerAll(List eventList) {
        for (Object o : eventList) {
            register(o);
        }
    }

    public void unRegisterAll(List eventList) {
        for (Object o : eventList) {
            unregister(o);
        }

        eventList.clear();
    }

    @Override
    public void post(Object event) {
        super.post(event);
    }
}
