package kr.co.saweb.enhance.android.annotations.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.annotations.core.BusProvider;

/**
 * Created by macpro on 2014. 10. 18..
 */
public class BaseViewGroup extends RelativeLayout {
    private List<Object> eventHandlerList = new ArrayList<Object>();

    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected BusProvider bus = BusProvider.getInstance();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onAddEventHandler(eventHandlerList);
        bus.registerAll(eventHandlerList);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bus.unRegisterAll(eventHandlerList);
    }

    public void onAddEventHandler(List<Object> eventHandlerList) {
    }
}